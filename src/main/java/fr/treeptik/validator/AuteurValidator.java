package fr.treeptik.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import fr.treeptik.dao.AuteurDAO;
import fr.treeptik.pojo.Auteur;

@Component
public class AuteurValidator implements Validator {

	@Autowired
	private AuteurDAO dao;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.getClass().equals(Auteur.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Auteur auteur = (Auteur) target;
		Long id = auteur.getId();
		
		if (auteur.getNom() == null)
			errors.rejectValue("nom", "errorCode", "Le nom de l'auteur est vide");
		
		Long count;
		if (id == null) {
			// count by name
			count = dao.countByName(auteur);
		} else {
			// count by name with id <> this.id
			count = dao.countByNameAndId(auteur);
		}

		if (count != 0) {
			errors.rejectValue("nom", "errorCode", "Le nom de l'auteur est déjà pris");
		}
	}
}
