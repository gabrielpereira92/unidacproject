package com.project.unidacproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userunidac")
public class UserUnidac {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String cpf;

	@Column(nullable = false)
	private String birthday;

	@Column(nullable = true)
	private String password;

	public UserUnidac() {
		super();
	}

	public UserUnidac(Long id, String name, String cpf, String birthday, String password) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.birthday = birthday;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserUnidac [id=" + id + ", name=" + name + ", cpf=" + cpf + ", birthday=" + birthday + ", password="
				+ password + "]";
	}

}
