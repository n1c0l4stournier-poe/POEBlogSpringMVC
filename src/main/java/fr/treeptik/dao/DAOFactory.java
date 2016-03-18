package fr.treeptik.dao;

import org.springframework.context.ApplicationContext;

public class DAOFactory {

	private static ApplicationContext context;

	public DAOFactory(ApplicationContext appContext) {
		context = appContext;
	}

	public ApplicationContext getContext() {
		return context;
	}

	public ArticleDAO getArticleDAO() {
		return (ArticleDAO) context.getBean("articleDAOJPA");
	}

	public AuteurDAO getAuteurDAO() {
		return (AuteurDAO) context.getBean("auteurDAOJPA");
	}

	public CategorieDAO getCategorieDAO() {
		return (CategorieDAO) context.getBean("categorieDAOJPA");
	}

	public CommentaireDAO getCommentaireDAO() {
		return (CommentaireDAO) context.getBean("commentaireDAOJPA");
	}

}
