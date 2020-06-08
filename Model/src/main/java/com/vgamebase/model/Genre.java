package com.vgamebase.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "vgamebase.genre")
public class Genre implements Serializable {

	private static final long serialVersionUID = -4096888939812857252L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Column(name="genre_name", nullable=false)
	private String name;
	
	public Genre() {
		super();
	}

	public Genre(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "{id: "+id+",name: "+name+"}";
	}
}
