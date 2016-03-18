package fr.treeptik.dao;

import java.util.Date;
import java.util.List;

import fr.treeptik.pojo.Article;

public interface ArticleDAO extends GenericDAO<Article, Long> {
	
	/**
	 * les articles parus sur une période de temps 
	 * @param date
	 * @return
	 */
	List<Article> findArticlePublishedDuring(Date before, Date after);
	
	/**
	 * les articles commentés 
	 * @return
	 */
	List<Article> findArticlesCommentes();

	Article findOneAndFetch(Long id);

	List<Article> findArticlesInCategory(Long id);

}
