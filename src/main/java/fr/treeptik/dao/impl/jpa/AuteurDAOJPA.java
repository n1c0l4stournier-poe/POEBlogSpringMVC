package fr.treeptik.dao.impl.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.treeptik.dao.AuteurDAO;
import fr.treeptik.pojo.Auteur;

@Repository
public class AuteurDAOJPA extends GenericDAOJPA<Auteur, Long> implements
		AuteurDAO {

	@Override
	public Auteur findAuteurArticleLePlusCommente() {
		String jpql = "SELECT DISTINCT auteur FROM Auteur auteur JOIN auteur.articles a JOIN a.commentaires c GROUP BY a.auteur.id ORDER BY COUNT(c.id) DESC";
		return em.createQuery(jpql, Auteur.class).setMaxResults(1).getSingleResult();
	}

	@Override
	public List<Auteur> listOrderByNombreArticles() {
		String jpql = "SELECT DISTINCT auteur FROM Auteur auteur JOIN auteur.articles a GROUP BY a.auteur.id ORDER BY COUNT(a.id) DESC";// WHERE online=true
		return em.createQuery(jpql, Auteur.class).getResultList();
	}

	@Override
	public Long countByName(Auteur auteur) {
		String jpql = "SELECT COUNT(c.id) FROM Auteur c WHERE c.nom = :nom AND c.prenom = :prenom";
		return em.createQuery(jpql, Long.class).setParameter("nom", auteur.getNom()).setParameter("prenom", auteur.getPrenom()).getSingleResult();
	}

	@Override
	public Long countByNameAndId(Auteur auteur) {
		String jpql = "SELECT COUNT(c.id) FROM Auteur c WHERE c.id <> :id AND c.nom = :nom AND c.prenom = :prenom";
		return em.createQuery(jpql, Long.class).setParameter("id", auteur.getId()).setParameter("prenom", auteur.getPrenom()).setParameter("nom", auteur.getNom()).getSingleResult();
	}

	@Override
	public Auteur findByMail(String mail) {
		String jpql = "SELECT auteur FROM Auteur auteur WHERE auteur.mail= :mail";
		return em.createQuery(jpql, Auteur.class).setParameter("mail", mail).getSingleResult();
	}

}