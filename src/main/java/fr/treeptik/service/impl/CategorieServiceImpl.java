package fr.treeptik.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.treeptik.dao.CategorieDAO;
import fr.treeptik.pojo.Categorie;
import fr.treeptik.service.CategorieService;
import fr.treeptik.service.exception.ServiceException;

@Service
public class CategorieServiceImpl extends GenericServiceImpl<Categorie, Long> implements CategorieService {

	@Override
	public Long countNbArticles(Long id) throws ServiceException {
		if (dao.findOne(id) == null)
			throw new ServiceException();
		return ((CategorieDAO) dao).countNbArticles(id);
	}

	@Override
	public Categorie findCategortyWithMaxArticles() {
		return ((CategorieDAO) dao).findCategortyWithMaxArticles();
	}

	@Override
	public List<Categorie> findCategoryEmpty() {
		return ((CategorieDAO) dao).findCategoryEmpty();
	}

}
