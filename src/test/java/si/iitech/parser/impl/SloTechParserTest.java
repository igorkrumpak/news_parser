package si.iitech.parser.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import si.iitech.AbstractTest;
import si.iitech.parser.object.impl.Article;

public class SloTechParserTest extends AbstractTest {

	@Autowired
	private SloTechParser sloTechParser;

	@Test
	public void testGetArticles() {
		List<Article> articles = sloTechParser.getArticles();
		assertThat(articles).isNotEmpty();
	}
	
	@Test
	public void testGetArticle() {
		
	}

}
