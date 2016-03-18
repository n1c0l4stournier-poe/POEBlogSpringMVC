package fr.treeptik.dao.impl.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.treeptik.dao.CommentaireDAO;
import fr.treeptik.pojo.Commentaire;

@Repository
public class CommentaireDAOJPA extends GenericDAOJPA<Commentaire, Long> implements CommentaireDAO {

	@Override
	public List<Commentaire> findAllByArticle(Long article_id) {
		String jpql = "SELECT c FROM Commentaire c JOIN FETCH c.auteur WHERE c.article.id = :id";
		return em.createQuery(jpql, Commentaire.class).setParameter("id", article_id).getResultList();
	}

	@Override
	public Commentaire findOneAndFetch(Long id) {
		String jpql = "SELECT a FROM Commentaire a JOIN FETCH a.auteur JOIN FETCH a.article WHERE a.id = :id";
		return em.createQuery(jpql, Commentaire.class).setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public Long countCommentaireByArticle(Long article_id) {
		String jpql = "SELECT COUNT(a.id) FROM Commentaire a JOIN a.article WHERE a.id = :id";
		return em.createQuery(jpql, Long.class).setParameter("id", article_id)
				.getSingleResult();
	}

}
