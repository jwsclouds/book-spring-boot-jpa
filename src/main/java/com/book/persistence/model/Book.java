package com.book.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "article")
public class Book {

	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name="title", nullable = false)
	private String title;
	
	@Column(name="author",nullable = false)
	private String author;

	public Book() {
		super();
	}
	
	public Book(String title, String author) {
		super();
		this.author = author;
		this.title = title;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ",author=" + author + "]";
	}

}
