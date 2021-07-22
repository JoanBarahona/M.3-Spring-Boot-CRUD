package com.member1.member;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String author;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private double value;
	
	
	
	public Book() {
		super();
	}
	
	public Book(int id, String author, Date date, double value) {
		super();
		this.id = id;
		this.author = author;
		this.date = date;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", date=" + date + ", value=" + value + "]";
	}


	
	
	
	
		
	
}
