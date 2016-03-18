package fr.treeptik.dao.impl.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.treeptik.dao.CategorieDAO;
import fr.treeptik.pojo.Categorie;

@Repository
public class CategorieDAOJPA extends GenericDAOJPA<Categorie, Long> implements CategorieDAO {

	@Override
	public Long countNbArticles(Long id) {
		String jpql = "SELECT COUNT(a.id) FROM Categorie c JOIN c.articles a WHERE c.id = :id";
		return em.createQuery(jpql, Long.class).setParameter("id", id).getSingleResult();
	}

	@Override
	public Categorie findCategortyWithMaxArticles() {
		String jpql = "SELECT c FROM Categorie c JOIN c.articles a GROUP BY c.id ORDER BY COUNT(a.id) DESC";
		return em.createQuery(jpql, Categorie.class).setMaxResults(1).getSingleResult();
	}

	@Override
	public List<Categorie> findCategoryEmpty() {
		String jpql = "SELECT c FROM Categorie c WHERE c.articles IS EMPTY";
		return em.createQuery(jpql, Categorie.class).getResultList();
	}

	@Override
	public Long countByName(Categorie categorie) {
		String jpql = "SELECT COUNT(c.id) FROM Categorie c WHERE c.nom = :nom";
		return em.createQuery(jpql, Long.class).setParameter("nom", categorie.getNom()).getSingleResult();
	}

	@Override
	public Long countByNameAndId(Categorie categorie) {
		String jpql = "SELECT COUNT(c.id) FROM Categorie c WHERE c.id <> :id AND c.nom = :nom";
		return em.createQuery(jpql, Long.class).setParameter("id", categorie.getId()).setParameter("nom", categorie.getNom()).getSingleResult();
	}

}
