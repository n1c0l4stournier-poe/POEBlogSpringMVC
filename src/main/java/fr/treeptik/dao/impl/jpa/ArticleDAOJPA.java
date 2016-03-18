package fr.treeptik.dao.impl.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import fr.treeptik.dao.ArticleDAO;
import fr.treeptik.pojo.Article;

@Repository
public class ArticleDAOJPA extends GenericDAOJPA<Article, Long> implements ArticleDAO {
	
	@Override
	public List<Article> findArticlePublishedDuring(Date after, Date before) {
		String jpql = "SELECT a FROM Article a WHERE a.dateCreation BETWEEN :after AND :before";
		return em.createQuery(jpql, Article.class)
				.setParameter("before", before)
				.setParameter("after", after)
				.getResultList();
	}

	@Override
	public List<Article> findArticlesCommentes() {
		String jpql = "SELECT DISTINCT a FROM Article a JOIN a.commentaires";
		return em.createQuery(jpql, Article.class)
				.getResultList();
	}

	@Override
	public Article findOneAndFetch(Long id) {
		String jpql = "SELECT a FROM Article a JOIN FETCH a.auteur JOIN FETCH a.categorie WHERE a.id = :id";
		return em.createQuery(jpql, Article.class).setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public List<Article> findArticlesInCategory(Long id) {
		String jpql = "SELECT a FROM Article a JOIN a.categorie c WHERE c.id = :id ORDER BY a.dateCreation DESC";
		return em.createQuery(jpql, Article.class).setParameter("id", id)
				.setMaxResults(10)
				.getResultList();
	}
}