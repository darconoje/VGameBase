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
@Table(name = "vgamebase.vote")
public class Vote implements Serializable {

	private static final long serialVersionUID = -4096888939812857252L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Column(name = "vote", nullable = false)
	private String vote;
	
	@JoinColumn(name = "game_platform_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private GamePlatform game;
	
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private User user;
	
	public Vote() {
		super();
	}

	public Vote(long id, String vote, GamePlatform game, User user) {
		this.id = id;
		this.vote = vote;
		this.game = game;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVote() {
		return vote;
	}

	public void setText(String vote) {
		this.vote = vote;
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
		return "{id: "+id+",vote: "+vote+"}";
	}
	
}
