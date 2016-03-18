package fr.treeptik.dao;

import java.util.List;

import fr.treeptik.pojo.Categorie;

public interface CategorieDAO extends GenericDAO<Categorie, Long> {
	
	/**
	 * nombre d'articles d'une catégorie 
	 * @return
	 */
	Long countNbArticles(Long id);
	
	/**
	 * catégorie ayant le plus d'articles 
	 * @return
	 */
	Categorie findCategortyWithMaxArticles();
	
	/**
	 * catégorie sans article 
	 * @return
	 */
	List<Categorie> findCategoryEmpty();
	
	Long countByName(Categorie categorie);
	
	Long countByNameAndId(Categorie categorie);

}
