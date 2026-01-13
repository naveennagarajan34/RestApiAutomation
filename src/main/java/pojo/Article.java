package pojo;

import java.util.Set;

public class Article {
	private String title;
	private String description;
	private String body;
	private Set<String> tagList;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Set<String> getTagList() {
		return tagList;
	}

	public void setTagList(Set<String> tagList) {
		this.tagList = tagList;
	}

}
