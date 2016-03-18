package fr.treeptik.service;

import java.util.List;

import fr.treeptik.pojo.Commentaire;

public interface CommentaireService extends GenericService<Commentaire, Long> {
	
	List<Commentaire> findAllByArticle(Long article_id);

	Commentaire findOneAndFetch(Long id);

}
