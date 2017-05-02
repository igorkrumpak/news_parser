package si.iitech.news_parser;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties({ "id", "revision" })
public class TestObject {

	@JsonProperty("_id")
	private String id;

	@JsonProperty("_rev")
	private String revision;

	private String color;
	
	public TestObject(String color) {
		this.color = color;
	}

	public void setId(String s) {
		id = s;
	}

	public String getId() {
		return id;
	}

	public String getRevision() {
		return revision;
	}

	public void setColor(String s) {
		color = s;
	}

	public String getColor() {
		return color;
	}
}
