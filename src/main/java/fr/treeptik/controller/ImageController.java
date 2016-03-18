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

import fr.treeptik.pojo.Article;
import fr.treeptik.pojo.Image;
import fr.treeptik.service.ArticleService;
import fr.treeptik.service.ImageService;
import fr.treeptik.service.exception.ServiceException;

@Controller
@RequestMapping(value = "/images")
public class ImageController {

	@Autowired
	private ImageService service;
	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "/create.do", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView("images/create");
		Image image = new Image();
		modelAndView.addObject("image", image);
		List<Article> articles = articleService.findAll();
		modelAndView.addObject("articles", articles);
		return modelAndView;
	}

	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public ModelAndView save(@Valid Image image, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("commentaires/create");
//		validator.validate(image, result);
		if (result.hasErrors()) {
			// for (ObjectError objectError : result.getAllErrors()) {
			// LOGGER.info(objectError.getDefaultMessage());
			// }
			modelAndView.addObject("image", image);
			return modelAndView;
		}
		image = service.save(image);
		modelAndView = new ModelAndView("redirect:create.do");
		return modelAndView;
	}

	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") Long id)
			throws ServiceException {
		service.delete(id);
		return new ModelAndView("redirect:create.do");
	}

}
