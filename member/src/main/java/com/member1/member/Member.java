package com.member1.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Member {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	public String name;
	public String surname;
	public String email;
	public String password;
	
	//private List<Book> books;
	

	public Member() {
		super();}
	
	
	
	public Member(String name, String surname, String email,String password) {
		super();
		
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", surname=" + surname + ", age=" + ", email=" + email + "]\n";
	}
	
	

}
