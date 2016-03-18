package fr.treeptik.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import fr.treeptik.dao.ArticleDAO;
import fr.treeptik.pojo.Article;

@Component
public class ArticleValidator implements Validator {

	@Autowired
	private ArticleDAO dao;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.getClass().equals(Article.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		// TODO: smth
		
	}
}
