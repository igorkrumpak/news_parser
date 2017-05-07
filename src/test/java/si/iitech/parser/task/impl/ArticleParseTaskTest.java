package si.iitech.parser.task.impl;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import si.iitech.AbstractTest;
import si.iitech.parser.object.impl.Article;
import si.iitech.parser.repository.impl.ArticleRepository;
import si.iitech.parser.task.impl.ArticleParseTask;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleParseTaskTest extends AbstractTest {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ArticleParseTask task;

	@Test
	public void testParseAndSaveArticles() {
		task.parseAndSaveArticles();
		List<Article> articles = articleRepository.getAll();
		assertThat(articles).isNotEmpty();
	}

	@Before
	public void setUp() {
		List<Article> retrivedArticles = articleRepository.getAll();
		for (Article articleToRemove : retrivedArticles) {
			articleRepository.remove(articleToRemove);
		}
	}
}
