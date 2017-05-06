package si.iitech.parser.object.impl;

import java.util.List;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

public class Article extends CouchDbDocument {

	private static final long serialVersionUID = 8607580583173603622L;

	@TypeDiscriminator
	private String title;
	
	private ArticleSource source;

	private Long publishDate;

	private String url;

	private String html;
	
	private List<String> authors;
	
	private List<ArticleMedia> sources;
	
	private List<ArticleMedia> images;
	
	private List<ArticleMedia> videos;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public ArticleSource getSource() {
		return source;
	}

	public void setSource(ArticleSource source) {
		this.source = source;
	}

	public Long getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Long publishDate) {
		this.publishDate = publishDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public List<ArticleMedia> getSources() {
		return sources;
	}

	public void setSources(List<ArticleMedia> sources) {
		this.sources = sources;
	}

	public List<ArticleMedia> getImages() {
		return images;
	}

	public void setImages(List<ArticleMedia> images) {
		this.images = images;
	}

	public List<ArticleMedia> getVideos() {
		return videos;
	}

	public void setVideos(List<ArticleMedia> videos) {
		this.videos = videos;
	}

	public void mergeArticle(Article article) {
		this.url = article.getUrl();
		this.authors = article.getAuthors();
		this.html = article.getHtml();
		this.images = article.getImages();
		this.publishDate = article.getPublishDate();
		this.source = article.getSource();
		this.sources = article.getSources();
	}

	@Override
	public String toString() {
		return "Article [title=" + title + ", source=" + source + ", publishDate=" + publishDate + ", url=" + url
				+ ", html=" + html + ", authors=" + authors + ", sources=" + sources + ", images=" + images
				+ ", videos=" + videos + "]";
	}
	
	
}
