package fr.treeptik.service;

import java.util.List;

import fr.treeptik.pojo.Categorie;
import fr.treeptik.service.exception.ServiceException;

public interface CategorieService extends GenericService<Categorie, Long> {

	Long countNbArticles(Long id) throws ServiceException;

	Categorie findCategortyWithMaxArticles();

	List<Categorie> findCategoryEmpty();
}