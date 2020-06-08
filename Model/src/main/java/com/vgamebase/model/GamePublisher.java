package com.vgamebase.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vgamebase.game_publisher")
public class GamePublisher implements Serializable {

	private static final long serialVersionUID = -4687403711641516790L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@JoinColumn(name = "game_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Game game;

	@JoinColumn(name = "publisher_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Publisher publisher;
	
	public GamePublisher() {
		super();
	}

	public GamePublisher(long id, Game game, Publisher publisher) {
		this.id = id;
		this.game = game;
		this.publisher = publisher;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "GamePublisher [id=" + id + ", game=" + game + ", publisher=" + publisher + "]";
	}
	
}
