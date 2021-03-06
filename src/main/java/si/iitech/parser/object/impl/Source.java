package si.iitech.parser.object.impl;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.ektorp.support.CouchDbDocument;
import org.springframework.core.io.ClassPathResource;

public class Source extends CouchDbDocument {

	public static final Source SLO_TECH = new Source("SLO_TECH", "Slo Tech", "https://slo-tech.com/",
			"images/slo_tech.png");

	private static final long serialVersionUID = 174668847322345840L;

	private String key;
	private String name;
	private String url;

	@JsonIgnore
	private File image;

	private Source() {
	};

	private Source(String key, String name, String url, String filePath) {
		this.key = key;
		this.name = name;
		this.url = url;
		setImage(filePath);
	}

	private void setImage(String filePath) {
		try {
			this.image = new ClassPathResource(filePath).getFile();
		} catch (IOException e) {
			throw new RuntimeException("image should exist");
		}
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

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public void mergeArticleSource(Source articleSource) {
		this.name = articleSource.getName();
		this.url = articleSource.getUrl();
	}

}
