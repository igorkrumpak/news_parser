package si.iitech.parser;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import si.iitech.parser.object.impl.Article;

public abstract class ArticleParser extends WebSiteParser {

	protected static final Logger LOG = Logger.getLogger(ArticleParser.class);
	
	public List<Article> getArticles() {
		List<Article> articles = new ArrayList<Article>();
		try {
			fillArticles(articles);
		} catch (Exception e) {
			LOG.error(e);
		}
		return articles;
	}

	public Article getArticle(String url) {
		Article article = new Article();
		article.setUrl(url);
		try {
			Document document = super.readWebSite(url);
			parseArticle(document, article);
		} catch (Exception e) {
			LOG.error(e);
			return null;
		}
		LOG.info(article);
		return article;
	}

	protected abstract void fillArticles(List<Article> articles);

	protected abstract void parseArticle(Document document, Article article) throws Exception;

}
