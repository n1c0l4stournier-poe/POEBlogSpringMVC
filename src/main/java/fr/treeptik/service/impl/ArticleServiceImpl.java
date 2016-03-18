package fr.treeptik.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.treeptik.dao.ArticleDAO;
import fr.treeptik.pojo.Article;
import fr.treeptik.service.ArticleService;
import fr.treeptik.service.exception.ServiceException;

@Service
public class ArticleServiceImpl extends GenericServiceImpl<Article, Long> implements ArticleService {

	@Override
	public List<Article> findArticlePublishedDuring(Date after, Date before) throws ServiceException {
		if (before.equals(after)){
			throw new ServiceException();
		}
		if (before.compareTo(after) > 0){
			throw new ServiceException();
		}
		return ((ArticleDAO) dao).findArticlePublishedDuring(after, before);
	}

	@Override
	public List<Article> findArticlesCommentes() {
		return ((ArticleDAO) dao).findArticlesCommentes();
	}

	public Article findOneAndFetch(Long id) {
		return ((ArticleDAO) dao).findOneAndFetch(id);
	}

	@Override
	public List<Article> findArticlesInCategory(Long id) {
		return ((ArticleDAO) dao).findArticlesInCategory(id);
	}

}
