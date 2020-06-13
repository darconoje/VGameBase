package com.vgamebase.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vgamebase.game_platform")
public class GamePlatform implements Serializable {

	private static final long serialVersionUID = -4687403711641516790L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(name="release_year", nullable=false)
	private int releaseYear;
	
	@JoinColumn(name = "game_publisher_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private GamePublisher gamepublisher;
	
	@JoinColumn(name = "platform_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Platform platform;
	
	@NotNull
	@Column(name="active", nullable=false)
	private boolean active;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST}) 
	@JoinColumn(name = "game_platform_id", nullable=true) 
	private List<Image> images;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST}) 
	@JoinColumn(name = "game_platform_id", nullable=true) 
	private List<Comment> comments;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST}) 
	@JoinColumn(name = "game_platform_id", nullable=true) 
	private List<Vote> votes;
	
	public GamePlatform() {
		super();
	}

	public GamePlatform(long id, int releaseYear, GamePublisher gamepublisher, Platform platform, boolean active,
			List<Image> images, List<Comment> comments, List<Vote> votes) {
		this.id = id;
		this.releaseYear = releaseYear;
		this.gamepublisher = gamepublisher;
		this.platform = platform;
		this.active = active;
		this.images = images;
		this.comments = comments;
		this.votes = votes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public GamePublisher getGamepublisher() {
		return gamepublisher;
	}

	public void setGamepublisher(GamePublisher gamepublisher) {
		this.gamepublisher = gamepublisher;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}	

	@Override
	public String toString() {
		return "GamePlatform [id=" + id + ", releaseYear=" + releaseYear + ", gamepublisher=" + gamepublisher
				+ ", platform=" + platform + "]";
	}
	
	public int getLikes() {
		
		int likes = 0;
		
		List<Vote> votes = this.votes;
		
		likes = (int) votes.stream().filter( v -> v.getVote().equals("like")).count();
		
		return likes;
		
	}
	
	public int getDislikes() {
		
		int dislikes = 0;
		
		List<Vote> votes = this.votes;
		
		dislikes = (int) votes.stream().filter( v -> v.getVote().equals("dislike")).count();
		
		return dislikes;
		
	}
	
}
