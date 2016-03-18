package fr.treeptik.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.dao.AuteurDAO;
import fr.treeptik.dao.CategorieDAO;
import fr.treeptik.dao.CommentaireDAO;
import fr.treeptik.dao.ImageDAO;
import fr.treeptik.pojo.Article;
import fr.treeptik.pojo.Auteur;
import fr.treeptik.pojo.Categorie;
import fr.treeptik.pojo.Commentaire;
import fr.treeptik.pojo.Image;
import fr.treeptik.service.ArticleService;
import fr.treeptik.service.exception.ServiceException;
import fr.treeptik.validator.ArticleValidator;

@Controller
@RequestMapping(value = "/articles")
public class ArticleController {
	
	@Autowired
	private AuteurDAO auteurDao; // TODO: Passer plutot par le service !
	
	@Autowired
	private CategorieDAO categorieDao; // TODO: Passer plutot par le service !
	
	@Autowired
	private CommentaireDAO commentaireDao; // TODO: Passer plutot par le service !

	@Autowired
	private ImageDAO imageDao;
	
	@Autowired
	private ArticleService service;
	
	@Autowired
	private ArticleValidator validator;

	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index() {
//		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		ModelAndView modelAndView = new ModelAndView("articles/index");
		List<Article> articles = service.findAll();		
		modelAndView.addObject("articles", articles);
		return modelAndView;
	}

	@RequestMapping(value = "/create.do", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView("articles/create");
		
		String mail = SecurityContextHolder.getContext().getAuthentication().getName();
		Auteur auteur = auteurDao.findByMail(mail);
		modelAndView.addObject("auteur", auteur);

		Article article = new Article();
		modelAndView.addObject("article", article);
		article.setAuteur(auteur);
		
		List<Categorie> categories = categorieDao.findAll();
		modelAndView.addObject("categories", categories);
		return modelAndView;
	}

	@RequestMapping(value = "/show.do", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("articles/show");
		
		Article article = service.findOneAndFetch(id);
		modelAndView.addObject("article", article);

		List<Commentaire> lstCommentaire = commentaireDao.findAllByArticle(id);
		modelAndView.addObject("commentaires", lstCommentaire);
		modelAndView.addObject("commentaires_nb", lstCommentaire.size());
		
		List<Article> articles_l = service.findArticlesInCategory(article.getCategorie().getId());
		modelAndView.addObject("articles_l", articles_l);
		
		List<Image> images = imageDao.findImagesInArticle(article.getId());
		article.setImages(images);
		for (Image image : images) {
			image.toString();
		}
		modelAndView.addObject("images", images);
		
		String mail = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Auteur user = auteurDao.findByMail(mail);
//		modelAndView.addObject("auteur", auteur);
		
		Commentaire commentaire = new Commentaire();
		commentaire.setAuteur(user);
		commentaire.setArticle(article);
		commentaire.setMail(user.getMail());
		modelAndView.addObject("commentaire", commentaire);

		return modelAndView;
	}

	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("articles/create");
		Article article = service.findOneAndFetch(id);
		modelAndView.addObject("article", article);
		
		List<Auteur> auteurs = auteurDao.findAll();
		modelAndView.addObject("auteurs", auteurs);
		
		List<Categorie> categories = categorieDao.findAll();
		modelAndView.addObject("categories", categories);
		return modelAndView;
	}

	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public ModelAndView save(@Valid Article article, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("articles/create");
		validator.validate(article, result);
		article.setDateCreation(new Date());
		if (result.hasErrors()) {
			// for (ObjectError objectError : result.getAllErrors()) {
			// LOGGER.info(objectError.getDefaultMessage());
			// }
			modelAndView.addObject("article", article);
			return modelAndView;
		}
		article = service.save(article);
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
