package si.iitech.parser.object.impl;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

public class Article extends CouchDbDocument {

	private static final long serialVersionUID = 8607580583173603622L;

	@TypeDiscriminator
	private String title;

	@JsonIgnore
	private Source source;
	
	private String sourceKey;

	private Long publishDate;

	private String url;

	private String html;

	private List<String> authors;

	private List<Media> sources;

	private List<Media> images;

	private List<Media> videos;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
		this.sourceKey = source.getKey();
	}
	
	public String getSourceKey() {
		return sourceKey;
	}
	
	public void setSourceKey(String sourceKey) {
		this.sourceKey = sourceKey;
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

	public List<Media> getSources() {
		return sources;
	}

	public void setSources(List<Media> sources) {
		this.sources = sources;
	}

	public List<Media> getImages() {
		return images;
	}

	public void setImages(List<Media> images) {
		this.images = images;
	}

	public List<Media> getVideos() {
		return videos;
	}

	public void setVideos(List<Media> videos) {
		this.videos = videos;
	}

	public void mergeArticle(Article article) {
		this.url = article.getUrl();
		this.authors = article.getAuthors();
		this.html = article.getHtml();
		this.images = article.getImages();
		this.publishDate = article.getPublishDate();
		this.sources = article.getSources();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + ((html == null) ? 0 : html.hashCode());
		result = prime * result + ((images == null) ? 0 : images.hashCode());
		result = prime * result + ((publishDate == null) ? 0 : publishDate.hashCode());
		result = prime * result + ((sourceKey == null) ? 0 : sourceKey.hashCode());
		result = prime * result + ((sources == null) ? 0 : sources.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((videos == null) ? 0 : videos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (html == null) {
			if (other.html != null)
				return false;
		} else if (!html.equals(other.html))
			return false;
		if (images == null) {
			if (other.images != null)
				return false;
		} else if (!images.equals(other.images))
			return false;
		if (publishDate == null) {
			if (other.publishDate != null)
				return false;
		} else if (!publishDate.equals(other.publishDate))
			return false;
		if (sourceKey == null) {
			if (other.sourceKey != null)
				return false;
		} else if (!sourceKey.equals(other.sourceKey))
			return false;
		if (sources == null) {
			if (other.sources != null)
				return false;
		} else if (!sources.equals(other.sources))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (videos == null) {
			if (other.videos != null)
				return false;
		} else if (!videos.equals(other.videos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Article [title=" + title + ", source=" + source + ", publishDate=" + publishDate + ", url=" + url
				+ ", html=" + html + ", authors=" + authors + ", sources=" + sources + ", images=" + images
				+ ", videos=" + videos + "]";
	}

}
