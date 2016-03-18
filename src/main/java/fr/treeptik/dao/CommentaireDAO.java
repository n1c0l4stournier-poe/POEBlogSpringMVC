package fr.treeptik.dao;

import java.util.List;

import fr.treeptik.pojo.Commentaire;

public interface CommentaireDAO extends GenericDAO<Commentaire, Long> {
	
	/**
	 * tous les commentaires d'un article 
	 * @param article_id PK d'un article
	 * @return
	 */
	List<Commentaire> findAllByArticle(Long article_id);

	Commentaire findOneAndFetch(Long id);
	
	Long countCommentaireByArticle(Long article_id);

}