package si.iitech.parser.repository.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import si.iitech.AbstractTest;
import si.iitech.parser.object.impl.Article;
import si.iitech.parser.object.impl.ArticleMedia;
import si.iitech.parser.object.impl.ArticleSource;

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
	}

	private Article newArticle() {
		Article article = new Article();
		article.setTitle(TEST_TITLE);
		article.setAuthors(Arrays.asList("Irena", "Igor"));
		article.setUrl("www.google.com");
		article.setHtml("<html></html>");
		article.setImages(Arrays.asList(new ArticleMedia("title", "www.google.com")));
		article.setPublishDate(new Date().getTime());
		article.setSource(ArticleSource.SLO_TECH);
		article.setSources(Arrays.asList(new ArticleMedia("slo-tech", "www.google.com")));
		article.setVideos(Arrays.asList(new ArticleMedia("video1", "www.google.com")));
		return article;
	}
	
	@After
	public void breakDown() {
		List<Article> retrivedArticles = articleRepository.findByTitle(TEST_TITLE);
		for (Article articleToRemove : retrivedArticles) {
			articleRepository.remove(articleToRemove);
		}
	}
}
