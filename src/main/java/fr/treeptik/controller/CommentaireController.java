package fr.treeptik.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.dao.ArticleDAO;
import fr.treeptik.dao.AuteurDAO;
import fr.treeptik.pojo.Article;
import fr.treeptik.pojo.Auteur;
import fr.treeptik.pojo.Commentaire;
import fr.treeptik.service.CommentaireService;
import fr.treeptik.service.exception.ServiceException;
import fr.treeptik.validator.CommentaireValidator;

@Controller
@RequestMapping(value = "/commentaires")
public class CommentaireController {

	@Autowired
	private CommentaireService service;

	@Autowired
	private CommentaireValidator validator;
	
	@Autowired
	private AuteurDAO auteurDao; // TODO: Passer plutot par le service !
	
	@Autowired
	private ArticleDAO articleDao; // TODO: Passer plutot par le service !

	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("commentaires/index");
		List<Commentaire> commentaires = service.findAll();
		modelAndView.addObject("commentaires", commentaires);
		return modelAndView;
	}

	@RequestMapping(value = "/create.do", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView("commentaires/create");
		Commentaire commentaire = new Commentaire();
		modelAndView.addObject("commentaire", commentaire);
		
		List<Auteur> auteurs = auteurDao.findAll();
		modelAndView.addObject("auteurs", auteurs);
		
		List<Article> articles = articleDao.findAll();
		modelAndView.addObject("articles", articles);
		return modelAndView;
	}

	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("commentaires/create");
		Commentaire commentaire = service.findOneAndFetch(id);
		modelAndView.addObject("commentaire", commentaire);
		
		List<Auteur> auteurs = auteurDao.findAll();
		modelAndView.addObject("auteurs", auteurs);
		
		List<Article> articles = articleDao.findAll();
		modelAndView.addObject("articles", articles);
		return modelAndView;
	}

	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public ModelAndView save(@Valid Commentaire commentaire, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("commentaires/create");
		validator.validate(commentaire, result);
		commentaire.setDateCreation(new Date());
		if (result.hasErrors()) {
			// for (ObjectError objectError : result.getAllErrors()) {
			// LOGGER.info(objectError.getDefaultMessage());
			// }
			modelAndView.addObject("commentaire", commentaire);
			return modelAndView;
		}
		commentaire = service.save(commentaire);
		modelAndView = new ModelAndView("redirect:index.do");
		return modelAndView;
	}

	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") Long id)
			throws ServiceException {
		service.delete(id);
		return new ModelAndView("redirect:index.do");
	}

}
