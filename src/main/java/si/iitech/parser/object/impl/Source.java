package si.iitech.parser.object.impl;

import org.ektorp.support.CouchDbDocument;

public class Source extends CouchDbDocument {

	public static final Source SLO_TECH = new Source("SLO_TECH", "Slo Tech", "https://slo-tech.com/");

	private static final long serialVersionUID = 174668847322345840L;

	private String key;
	private String name;
	private String url;

	private Source() {
	};

	private Source(String key, String name, String url) {
		this.key = key;
		this.name = name;
		this.url = url;
	};
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void mergeArticleSource(Source articleSource) {
		this.name = articleSource.getName();
		this.url = articleSource.getUrl();
	}

}
