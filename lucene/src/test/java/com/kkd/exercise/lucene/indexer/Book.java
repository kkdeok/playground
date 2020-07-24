package com.kkd.exercise.lucene.indexer;

/**
 * POJO for resources/sample_data.json
 */
public class Book {
	private int id;
	private String name;
	private String intro;
	private String writer;
	private int pageCount;
	private String publishedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", name='" + name + '\'' +
				", intro='" + intro + '\'' +
				", writer='" + writer + '\'' +
				", pageCount=" + pageCount +
				", publishedDate='" + publishedDate + '\'' +
				'}';
	}
}