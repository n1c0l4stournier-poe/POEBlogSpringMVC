package fr.treeptik.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.treeptik.dao.ArticleDAO;
import fr.treeptik.dao.AuteurDAO;
import fr.treeptik.dao.CategorieDAO;
import fr.treeptik.dao.CommentaireDAO;
import fr.treeptik.pojo.Article;
import fr.treeptik.pojo.Auteur;
import fr.treeptik.pojo.Categorie;
import fr.treeptik.pojo.Commentaire;
import fr.treeptik.service.CommentaireService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
public class CommentaireServiceTest {
	
	private static Commentaire commentaire1;
	private static Commentaire commentaire2;
	private static Commentaire commentaire3;

	private static Categorie categorie1;
	private static Categorie categorie2;
	private static Categorie categorie3;
	private static Categorie categorie4;

	private static Article article1;
	private static Article article2;
	private static Article article3;

	private static Auteur auteur1;
	private static Auteur auteur2;
	private static Auteur auteur3;
	private static Auteur auteur4;
	private static Auteur auteur5;

	private static ApplicationContext context = new ClassPathXmlApplicationContext("test-context.xml");

	private static AuteurDAO auteurDAO = (AuteurDAO) context.getBean("auteurDAOJPA");
	private static ArticleDAO articleDAO = (ArticleDAO) context.getBean("articleDAOJPA");
	private static CategorieDAO categorieDAO = (CategorieDAO) context.getBean("categorieDAOJPA");
	private static CommentaireDAO commentaireDAO = (CommentaireDAO) context.getBean("commentaireDAOJPA");
	
	private static CommentaireService service = (CommentaireService) context.getBean("commentaireServiceImpl");

	@BeforeClass
	public static void setUp() {

		categorie1 = new Categorie("C1", "");
		categorie2 = new Categorie("C2", "");
		categorie3 = new Categorie("C3", "");
		categorie4 = new Categorie("C4", "");

		article1 = new Article("A1", "", "");
		article2 = new Article("A2", "", "");
		article3 = new Article("A3", "", "");

		auteur1 = new Auteur("Auteur1", "Prenom1", "mail1", "pass1");
		auteur2 = new Auteur("Auteur2", "Prenom2", "mail2", "pass2");
		auteur3 = new Auteur("Auteur3", "Prenom3", "mail3", "pass3");
		auteur4 = new Auteur("Auteur4", "Prenom4", "mail4", "pass4");
		auteur5 = new Auteur("Auteur5", "Prenom5", "mail5", "pass5");

		commentaire1 = new Commentaire("Com1", "mail1", "blabla");
		commentaire2 = new Commentaire("Com2", "mail2", "blabla");
		commentaire3 = new Commentaire("Com3", "mail3", "blabla");

		auteur1 = auteurDAO.save(auteur1);
		auteur2 = auteurDAO.save(auteur2);
		auteur3 = auteurDAO.save(auteur3);
		auteur4 = auteurDAO.save(auteur4);
		auteur5 = auteurDAO.save(auteur5);

		categorie1 = categorieDAO.save(categorie1);
		categorie2 = categorieDAO.save(categorie2);
		categorie3 = categorieDAO.save(categorie3);
		categorie4 = categorieDAO.save(categorie4);

		article1.setCategorie(categorie1);
		article2.setCategorie(categorie2);
		article3.setCategorie(categorie2);

		article1.setAuteur(auteur2);
		article2.setAuteur(auteur4);
		article3.setAuteur(auteur4);

		article1 = articleDAO.save(article1);
		article2 = articleDAO.save(article2);
		article3 = articleDAO.save(article3);

		commentaire1.setArticle(article1);
		commentaire2.setArticle(article1);
		commentaire3.setArticle(article2);
		
		commentaire1 = commentaireDAO.save(commentaire1);
		commentaire2 = commentaireDAO.save(commentaire2);
		commentaire3 = commentaireDAO.save(commentaire3);

	}

	@AfterClass
	public static void setDown() {
		
		commentaireDAO.delete(commentaire1.getId());
		commentaireDAO.delete(commentaire2.getId());
		commentaireDAO.delete(commentaire3.getId());

		articleDAO.delete(article1.getId());
		articleDAO.delete(article2.getId());
		articleDAO.delete(article3.getId());

		categorieDAO.delete(categorie1.getId());
		categorieDAO.delete(categorie2.getId());
		categorieDAO.delete(categorie3.getId());
		categorieDAO.delete(categorie4.getId());

		auteurDAO.delete(auteur1.getId());
		auteurDAO.delete(auteur2.getId());
		auteurDAO.delete(auteur3.getId());
		auteurDAO.delete(auteur4.getId());
		auteurDAO.delete(auteur5.getId());

	}

	@Test
	public void findAllByArticleTest() {

		List<Commentaire> findAllByArticle1 = service.findAllByArticle(article1.getId());
		List<Commentaire> findAllByArticle2 = service.findAllByArticle(article2.getId());
		List<Commentaire> findAllByArticle3 = service.findAllByArticle(article3.getId());

		assertFalse(findAllByArticle1.isEmpty());
		assertFalse(findAllByArticle2.isEmpty());
		assertTrue(findAllByArticle3.isEmpty());
		
		assertTrue(findAllByArticle1.contains(commentaire1));
		assertTrue(findAllByArticle1.contains(commentaire2));
		assertTrue(findAllByArticle2.contains(commentaire3));

	}

}
