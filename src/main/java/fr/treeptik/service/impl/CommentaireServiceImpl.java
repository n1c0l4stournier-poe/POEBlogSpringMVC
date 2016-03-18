package fr.treeptik.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.treeptik.dao.CommentaireDAO;
import fr.treeptik.pojo.Commentaire;
import fr.treeptik.service.CommentaireService;

@Service
public class CommentaireServiceImpl extends GenericServiceImpl<Commentaire, Long> implements CommentaireService {

	@Override
	public List<Commentaire> findAllByArticle(Long article_id) {
		return ((CommentaireDAO) dao).findAllByArticle(article_id);
	}
	
	@Override
	public Commentaire findOneAndFetch(Long id) {
		return ((CommentaireDAO) dao).findOneAndFetch(id);
	}

}
