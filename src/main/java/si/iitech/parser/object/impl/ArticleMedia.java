package si.iitech.parser.object.impl;

public class ArticleMedia {

	private String title;
	private String url;
	
	public ArticleMedia() {}
	
	public ArticleMedia(String title, String url) {
		this.title = title;
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "ArticleMedia [title=" + title + ", url=" + url + "]";
	}
}
