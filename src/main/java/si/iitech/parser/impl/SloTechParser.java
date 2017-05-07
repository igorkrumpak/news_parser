package si.iitech.parser.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import si.iitech.parser.ArticleParser;
import si.iitech.parser.exception.impl.MissingTitleException;
import si.iitech.parser.object.impl.Article;
import si.iitech.parser.object.impl.Media;
import si.iitech.parser.object.impl.Source;

@Component
public class SloTechParser extends ArticleParser {

	private static interface CssQuery {
		static final String DIV = "div";
		static final String IFRAME = "iframe";
		static final String YOUTUBE_PLAYER = ".youtube-player";
		static final String BESEDILO_NOVICE = ".besediloNovice";
		static final String ITEMPROP_AUTHOR = "[itemprop=author]";
		static final String ITEMPROP_NAME = "[itemprop=name]";
		static final String ITEMPROP_DATE_PUBLISHED = "[itemprop=datePublished]";
		static final String HREF_NOVICE = "[href~=novice]";
		static final String FRESH_NEWS = "#fresh_news";
		static final String ARTICLE = "article";
		static final String SOURCE = ".source";
		static final String IMG = "img";
	}

	private static interface HtmlAttribute {
		static final String DATETIME = "datetime";
		static final String HREF = "href";
		static final String SRC = "src";
		static final String ALT = "alt";
	}

	private static interface Strings {
		static final String T = "T";
		static final String _0 = "/0";
		static final String YYYY_MM_DD = "yyyy-MM-dd";
		static final String HTTPS = "https:";
	}

	@Override
	protected boolean useUserAgent() {
		return true;
	}

	private static final String URL = "https://slo-tech.com";

	@Override
	protected void fillArticles(List<Article> articles) {
		Document document = super.readWebSite(URL);
		Elements freshNewsElements = document.select(CssQuery.FRESH_NEWS);
		Elements newsElements = freshNewsElements.select(CssQuery.HREF_NOVICE);
		for (Element element : newsElements) {
			String partialHref = element.attr(HtmlAttribute.HREF);
			String url = URL.concat(partialHref).concat(Strings._0);
			Article article = getArticle(url);
			if (article != null) {
				articles.add(article);
			}
		}
	}

	@Override
	protected void parseArticle(Document document, Article article) throws Exception  {
		Elements elementArticle = document.select(CssQuery.ARTICLE);
		article.setSource(Source.SLO_TECH);
		setTitle(article, elementArticle);
		setPublishedDate(article, elementArticle);
		setAutthors(article, elementArticle);
		setImages(article, elementArticle);
		setVideos(article, elementArticle);

		Elements elementsData = elementArticle.select(CssQuery.BESEDILO_NOVICE);
		elementsData.select(CssQuery.DIV).remove();
		
		setSources(article, elementsData);

		// REMOVE UNWANTED DATA
		elementsData.select(CssQuery.SOURCE).remove();
		elementsData.select(CssQuery.IFRAME).remove();

		// HTML
		setHtml(article, elementsData);
	}

	private void setHtml(Article article, Elements elementsData) {
		String data = elementsData.html();
		article.setHtml(data.substring(2));
	}

	private void setSources(Article article, Elements elementsData) {
		Elements sourceElements = elementsData.select(CssQuery.SOURCE);
		ArrayList<Media> sources = new ArrayList<Media>();
		if (!sourceElements.isEmpty()) {
			Media source = new Media();
			source.setUrl(sourceElements.get(0).attr(HtmlAttribute.HREF));
			source.setTitle(sourceElements.get(0).text());
			sources.add(source);
		}
		article.setSources(sources);
	}

	private void setVideos(Article article, Elements elementArticle) {
		Elements videoElements = elementArticle.select(CssQuery.YOUTUBE_PLAYER);
		List<Media> videos = new ArrayList<Media>();
		if (!videoElements.isEmpty()) {
			for (Element videoElement : videoElements) {
				Media video = new Media();
				video.setUrl(videoElement.attr(HtmlAttribute.SRC));
				videos.add(video);
			}
		}
		article.setVideos(videos);
	}

	private void setImages(Article article, Elements elementArticle) {
		Elements imageElements = elementArticle.select(CssQuery.IMG);
		Media image;
		List<Media> images = new ArrayList<Media>();
		if (!imageElements.isEmpty()) {
			for (Element imageElement : imageElements) {
				image = new Media();
				image.setUrl((Strings.HTTPS).concat(imageElement.attr(HtmlAttribute.SRC)));
				image.setTitle(imageElement.attr(HtmlAttribute.ALT).trim());
				images.add(image);
			}
		}
		article.setImages(images);
	}

	private void setAutthors(Article article, Elements elementArticle) {
		String author = elementArticle.select(CssQuery.ITEMPROP_AUTHOR).text();
		List<String> authors = new ArrayList<String>();
		authors.add(author);
		article.setAuthors(authors);
	}

	private void setTitle(Article article, Elements elementArticle) throws MissingTitleException {
		String title = elementArticle.select(CssQuery.ITEMPROP_NAME).text();
		if (title.isEmpty()) {
			throw new MissingTitleException();
		}
		article.setTitle(title);
	}

	private void setPublishedDate(Article article, Elements elementArticle) throws ParseException {
		String dateTime = elementArticle.select(CssQuery.ITEMPROP_DATE_PUBLISHED).attr(HtmlAttribute.DATETIME);
		SimpleDateFormat sdf = new SimpleDateFormat(Strings.YYYY_MM_DD);
		String[] dateTextArray = dateTime.split(Strings.T);
		Date publishedDate = sdf.parse(dateTextArray[0]);
		article.setPublishDate(publishedDate.getTime());
	}
}