package si.iitech.parser.task.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import si.iitech.parser.impl.SloTechParser;
import si.iitech.parser.object.impl.Article;
import si.iitech.parser.repository.impl.ArticleRepository;

@Component
public class ArticleParseTask {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private SloTechParser sloTechParser;
	
	@Scheduled(fixedRate = 5000)
	public void parseAndSaveArticles() {
		List<Article> articles = new ArrayList<Article>();
		articles.addAll(sloTechParser.getArticles());
		articleRepository.addAll(articles);
	}
}
