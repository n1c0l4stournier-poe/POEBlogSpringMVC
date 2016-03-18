package fr.treeptik.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import fr.treeptik.dao.ArticleDAO;
import fr.treeptik.dao.CategorieDAO;
import fr.treeptik.pojo.Article;
import fr.treeptik.pojo.Categorie;
import fr.treeptik.service.CategorieService;
import fr.treeptik.service.exception.ServiceException;


@ContextConfiguration(locations={"classpath:test-context.xml"})
public class CategorieServiceTest {

	private static Categorie categorie1;
	private static Categorie categorie2;
	private static Categorie categorie3;
	private static Categorie categorie4;

	private static Article article1;
	private static Article article2;
	private static Article article3;
	
	private static ApplicationContext context = new ClassPathXmlApplicationContext("test-context.xml");
	
	private static ArticleDAO articleDAO = (ArticleDAO) context.getBean("articleDAOJPA");
	private static CategorieDAO categorieDAO = (CategorieDAO) context.getBean("categorieDAOJPA");
	
	private static CategorieService service = (CategorieService) context.getBean("categorieServiceImpl");

	@BeforeClass
	public static void setUp() {

		categorie1 = new Categorie("C1", "");
		categorie2 = new Categorie("C2", "");
		categorie3 = new Categorie("C3", "");
		categorie4 = new Categorie("C4", "");

		article1 = new Article("A1", "", "");
		article2 = new Article("A2", "", "");
		article3 = new Article("A3", "", "");

		categorie1 = categorieDAO.save(categorie1);
		categorie2 = categorieDAO.save(categorie2);
		categorie3 = categorieDAO.save(categorie3);
		categorie4 = categorieDAO.save(categorie4);

		article1.setCategorie(categorie1);
		article2.setCategorie(categorie2);
		article3.setCategorie(categorie2);

		article1 = articleDAO.save(article1);
		article2 = articleDAO.save(article2);
		article3 = articleDAO.save(article3);

	}

	@AfterClass
	public static void setDown() {

		articleDAO.delete(article1.getId());
		articleDAO.delete(article2.getId());
		articleDAO.delete(article3.getId());

		categorieDAO.delete(categorie1.getId());
		categorieDAO.delete(categorie2.getId());
		categorieDAO.delete(categorie3.getId());
		categorieDAO.delete(categorie4.getId());
		
	}

	@Test
	public void countNbArticlesTest() {
		
		boolean thrown = false;
		
		try {
			assertTrue(service.countNbArticles(categorie1.getId()).equals(1L));
			assertTrue(service.countNbArticles(categorie2.getId()).equals(2L));
			assertTrue(service.countNbArticles(categorie3.getId()).equals(0L));
			assertTrue(service.countNbArticles(categorie4.getId()).equals(0L));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		assertFalse(thrown);
		
		try {
			assertFalse(service.countNbArticles(0L).equals(0L));
		} catch (ServiceException e) {
			thrown = true;
		}
		
		assertTrue(thrown);

	}

	@Test
	public void findCategortyWithMaxArticlesTest() {

		Categorie findCategortyWithMaxArticles = service.findCategortyWithMaxArticles();
		System.out.println(findCategortyWithMaxArticles);
		System.out.println(categorie1);
		System.out.println(categorie2);
		System.out.println(categorie3);
		System.out.println(categorie4);
		
		assertFalse(findCategortyWithMaxArticles.equals(categorie1));
		 assertTrue(findCategortyWithMaxArticles.equals(categorie2));
		assertFalse(findCategortyWithMaxArticles.equals(categorie3));
		assertFalse(findCategortyWithMaxArticles.equals(categorie4));

	}

	@Test
	public void findCategoryEmptyTest() {
		
		List<Categorie> findCategoryEmpty = service.findCategoryEmpty();
		assertFalse(findCategoryEmpty.contains(categorie1));
		assertFalse(findCategoryEmpty.contains(categorie2));
		 assertTrue(findCategoryEmpty.contains(categorie3));
		 assertTrue(findCategoryEmpty.contains(categorie4));

	}

}
