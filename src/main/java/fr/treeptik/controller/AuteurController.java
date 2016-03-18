package fr.treeptik.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.pojo.Auteur;
import fr.treeptik.service.AuteurService;
import fr.treeptik.service.exception.ServiceException;
import fr.treeptik.validator.AuteurValidator;

@Controller
@RequestMapping(value = "/auteurs")
public class AuteurController {

	@Autowired
	private AuteurService service;
	
	@Autowired
	private AuteurValidator validator;

	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("auteurs/index");
		List<Auteur> auteurs = service.findAll();
		modelAndView.addObject("auteurs", auteurs);
		return modelAndView;
	}

	@RequestMapping(value = "/create.do", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView("auteurs/create");
		Auteur auteur = new Auteur();
		modelAndView.addObject("auteur", auteur);
		return modelAndView;
	}

	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("auteurs/create");
		Auteur auteur = service.findOne(id);
		modelAndView.addObject("auteur", auteur);
		return modelAndView;
	}

	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public ModelAndView save(@Valid Auteur auteur, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("auteurs/create");
		validator.validate(auteur, result);
		auteur.setRoleUser();
		if (result.hasErrors()) {
			// for (ObjectError objectError : result.getAllErrors()) {
			// LOGGER.info(objectError.getDefaultMessage());
			// }
			modelAndView.addObject("auteur", auteur);
			return modelAndView;
		}
		auteur = service.save(auteur);
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
