package fr.treeptik.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import fr.treeptik.pojo.Commentaire;

@Component
public class CommentaireValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.getClass().equals(Commentaire.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		// TODO: smth
		
	}

}
