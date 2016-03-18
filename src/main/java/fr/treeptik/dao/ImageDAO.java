package fr.treeptik.dao;

import java.util.List;

import fr.treeptik.pojo.Image;

public interface ImageDAO extends GenericDAO<Image, Long> {
	
	List<Image> findImagesInArticle(Long article_id);

}
