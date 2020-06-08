package com.vgamebase.model;

import java.io.Serializable;
import java.util.Base64;

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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "vgamebase.image")
public class Image implements Serializable {

	private static final long serialVersionUID = -4096888939812857252L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@Column(name = "image")
	private byte[] image;
	
	@JoinColumn(name = "game_platform_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private GamePlatform game;
	
	public Image() {
		super();
	}

	public Image(long id, String name, byte[] image, GamePlatform game) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.game = game;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public GamePlatform getGame() {
		return game;
	}

	public void setGame(GamePlatform game) {
		this.game = game;
	}
	
	public String getImageParsed() {
		return Base64.getEncoder().encodeToString(this.getImage());
	}
	
	@Override
	public String toString() {
		return "{id: "+id+",name: "+name+"}";
	}
	
}
