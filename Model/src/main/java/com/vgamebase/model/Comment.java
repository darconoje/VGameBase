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
@Table(name = "vgamebase.comment")
public class Comment implements Serializable {

	private static final long serialVersionUID = -4096888939812857252L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Column(name = "text", nullable = false)
	private String text;
	
	@JoinColumn(name = "game_platform_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private GamePlatform game;
	
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private User user;
	
	public Comment() {
		super();
	}

	public Comment(long id, String text, GamePlatform game, User user) {
		this.id = id;
		this.text = text;
		this.game = game;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public GamePlatform getGame() {
		return game;
	}

	public void setGame(GamePlatform game) {
		this.game = game;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "{id: "+id+",text: "+text+"}";
	}
	
}
