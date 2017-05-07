package si.iitech.parser.repository.impl;

import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.GenerateView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import si.iitech.parser.object.impl.Article;

@Component
public class ArticleRepository extends CouchDbRepositorySupport<Article> {
	
	@Autowired
	private SourceRepository sourceRepository;

	protected ArticleRepository(@Qualifier("articles") CouchDbConnector db) {
		super(Article.class, db);
		initStandardDesignDocument();
	}

	@Override
	public void add(Article article) {
		List<Article> retrivedArticles = findByTitle(article.getTitle());
		if(retrivedArticles.isEmpty()) {
			sourceRepository.add(article.getSource());
			super.add(article);
		} else if(retrivedArticles.size() == 1) {
			Article retrivedArticle = retrivedArticles.get(0);
			if(!retrivedArticle.equals(article)) {
				retrivedArticle.mergeArticle(article);
				super.update(retrivedArticle);
			}
		} else {
			new RuntimeException("there should now be more then one article with same title");
		}
	}

	@GenerateView
	public List<Article> findByTitle(String title) {
		return queryView("by_title", title);
	}

	public void addAll(List<Article> articles) {
		for(Article article : articles) {
			add(article);
		}
	}
}
