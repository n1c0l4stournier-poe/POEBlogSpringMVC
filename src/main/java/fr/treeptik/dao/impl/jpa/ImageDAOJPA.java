package fr.treeptik.dao.impl.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.treeptik.dao.ImageDAO;
import fr.treeptik.pojo.Image;

@Repository
public class ImageDAOJPA extends GenericDAOJPA<Image, Long> implements ImageDAO {

	@Override
	public List<Image> findImagesInArticle(Long article_id) {
		String jpql = "SELECT a FROM Image a JOIN a.article b WHERE b.id = :id";
		return em.createQuery(jpql, Image.class).setParameter("id", article_id)
				.getResultList();
	}

}