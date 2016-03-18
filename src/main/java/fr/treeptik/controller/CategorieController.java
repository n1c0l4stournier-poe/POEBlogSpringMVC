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

import fr.treeptik.pojo.Categorie;
import fr.treeptik.service.CategorieService;
import fr.treeptik.service.exception.ServiceException;
import fr.treeptik.validator.CategorieValidator;

@Controller
@RequestMapping(value = "/categories")
public class CategorieController {

	@Autowired
	private CategorieService service;

	@Autowired
	private CategorieValidator validator;

	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("categories/index");
		List<Categorie> categories = service.findAll();
		modelAndView.addObject("categories", categories);
		return modelAndView;
	}

	@RequestMapping(value = "/create.do", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView("categories/create");
		Categorie categorie = new Categorie();
		modelAndView.addObject("categorie", categorie);
		return modelAndView;
	}

	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("categories/create");
		Categorie categorie = service.findOne(id);
		modelAndView.addObject("categorie", categorie);
		return modelAndView;
	}

	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public ModelAndView save(@Valid Categorie categorie, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("categories/create");
		validator.validate(categorie, result);
		if (result.hasErrors()) {
			// for (ObjectError objectError : result.getAllErrors()) {
			// LOGGER.info(objectError.getDefaultMessage());
			// }
			modelAndView.addObject("categorie", categorie);
			return modelAndView;
		}
		categorie = service.save(categorie);
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
