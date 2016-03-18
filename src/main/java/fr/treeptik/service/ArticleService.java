package fr.treeptik.service;

import java.util.Date;
import java.util.List;

import fr.treeptik.pojo.Article;
import fr.treeptik.service.exception.ServiceException;

public interface ArticleService extends GenericService<Article, Long> {
	
	List<Article> findArticlePublishedDuring(Date after, Date before) throws ServiceException;

	List<Article> findArticlesCommentes() throws ServiceException;

	Article findOneAndFetch(Long id);
	
	List<Article> findArticlesInCategory(Long id);

}