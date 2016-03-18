package fr.treeptik.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import fr.treeptik.dao.CategorieDAO;
import fr.treeptik.pojo.Categorie;

@Component
public class CategorieValidator implements Validator {

	@Autowired
	private CategorieDAO dao;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.getClass().equals(Categorie.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Categorie categorie = (Categorie) target;
		Long id = categorie.getId();
		
		if (categorie.getNom() == null)
			errors.rejectValue("nom", "errorCode", "Le nom est vide");
		
		Long count;
		if (id == null) {
			// count by name
			count = dao.countByName(categorie);
		} else {
			// count by name with id <> this.id
			count = dao.countByNameAndId(categorie);
		}

		if (count != 0) {
			errors.rejectValue("nom", "errorCode", "Le nom est déjà pris");
		}
	}
}
