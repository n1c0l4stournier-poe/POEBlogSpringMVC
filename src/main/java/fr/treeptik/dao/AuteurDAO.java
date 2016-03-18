package fr.treeptik.dao;

import java.util.List;

import fr.treeptik.pojo.Auteur;

public interface AuteurDAO extends GenericDAO<Auteur, Long> {
	
	/**
	 * l'auteur avec l'article le plus commenté 
	 * @return
	 */
	Auteur findAuteurArticleLePlusCommente();

	/**
	 * liste par auteur du nombre d'article en ligne 
	 * @return
	 */
	List<Auteur> listOrderByNombreArticles();

	Long countByName(Auteur auteur);

	Long countByNameAndId(Auteur auteur);

	Auteur findByMail(String mail);

}
