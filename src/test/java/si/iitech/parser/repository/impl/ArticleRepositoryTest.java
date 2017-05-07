package si.iitech.parser.repository.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import si.iitech.AbstractTest;
import si.iitech.parser.object.impl.Article;
import si.iitech.parser.object.impl.Media;
import si.iitech.parser.object.impl.Source;

public class ArticleRepositoryTest extends AbstractTest {

	@Autowired
	private ArticleRepository articleRepository;

	private final static String TEST_TITLE = "Test Title";

	@Test
	public void testCreateAndRetriveNewArticle() {
		Article article = newArticle();
		articleRepository.add(article);

		String id = article.getId();
		assertThat(id).isNotNull();

		List<Article> retrivedArticles = articleRepository.findByTitle(TEST_TITLE);
		assertThat(retrivedArticles).isNotEmpty();
		assertThat(retrivedArticles.size()).isEqualTo(1);
		
		Article retrivedArticle = retrivedArticles.get(0);
		assertThat(retrivedArticle.getTitle()).isEqualTo(TEST_TITLE);
	}
	
	@Test
	public void testDuplicateArticle() {
		Article article = newArticle();
		articleRepository.add(article);
		
		Article article2 = newArticle();
		articleRepository.add(article2);
		
		List<Article> retrivedArticles = articleRepository.findByTitle(TEST_TITLE);
		assertThat(retrivedArticles).isNotEmpty();
		assertThat(retrivedArticles.size()).isEqualTo(1);
		Article retrivedArticle = retrivedArticles.get(0);
		assertThat(retrivedArticle).isEqualTo(article);
	}

	private Article newArticle() {
		Article article = new Article();
		article.setTitle(TEST_TITLE);
		article.setAuthors(Arrays.asList("Irena", "Igor"));
		article.setUrl("www.google.com");
		article.setHtml("<html></html>");
		article.setImages(Arrays.asList(new Media("title", "www.google.com")));
		article.setPublishDate(new GregorianCalendar(2000, 1, 1).getTime().getTime());
		article.setSource(Source.SLO_TECH);
		article.setSources(Arrays.asList(new Media("slo-tech", "www.google.com")));
		article.setVideos(Arrays.asList(new Media("video1", "www.google.com")));
		return article;
	}
	
	@Before
	public void setUp() {
		List<Article> retrivedArticles = articleRepository.findByTitle(TEST_TITLE);
		for (Article articleToRemove : retrivedArticles) {
			articleRepository.remove(articleToRemove);
		}
	}
}
