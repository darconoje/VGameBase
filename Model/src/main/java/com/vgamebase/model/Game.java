package com.vgamebase.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "vgamebase.game")
public class Game implements Serializable {

	private static final long serialVersionUID = -4096888939812857252L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	@Column(name="game_name", nullable=false)
	private String name;
	
	@JoinColumn(name = "genre_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Genre genre;
	
	public Game() {
		super();
	}

	public Game(long id, String name, Genre genre) {
		this.id = id;
		this.name = name;
		this.genre = genre;
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

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	@Override
	public String toString() {
		return "{id: "+id+",name: "+name+"}";
	}
	
}
