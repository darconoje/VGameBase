package com.vgamebase.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="vgamebase.user")
public class User implements Serializable{

	private static final long serialVersionUID = 5199726122007038726L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	@Column(name="password", nullable=false)
	private String password;
		
	@NotEmpty
	@Column(name="username", nullable=false)
	private String userName;

	@NotNull
	@Column(name="active", nullable=false)
	private boolean active;
	
	@NotEmpty
	@Column(name="email", nullable=false)
	private String email;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "vgamebase.user_user_profile", 
             joinColumns = { @JoinColumn(name = "user_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "user_profile_id") })
	private UserProfile userProfile = new UserProfile();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST}) 
	@JoinColumn(name = "user_id", nullable=true) 
	private List<Comment> comments;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST}) 
	@JoinColumn(name = "user_id", nullable=true) 
	private List<Vote> votes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean enabled) {
		this.active = enabled;
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

	
	/*
	 * @Override public int hashCode() { final int prime = 31; int result = 1;
	 * result = prime * result + ((id == null) ? 0 : id.hashCode()); return result;
	 * }
	 */

	/*
	 * @Override public boolean equals(Object obj) { if (this == obj) return true;
	 * if (obj == null) return false; if (!(obj instanceof User)) return false; User
	 * other = (User) obj; if (id == null) { if (other.id != null) return false; }
	 * else if (!id.equals(other.id)) return false; return true; }
	 */

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password
				+ ", userName=" + userName
				+ ", email=" + email + "]";
	}
	
	public boolean alreadyVoted(GamePlatform gameplatform, String vote) {
		
		boolean voted = false;
		
		if((int) this.votes.stream().filter( v -> v.getGame().getId() == gameplatform.getId() && v.getVote().equals(vote)).count() > 0) {
			voted = true;
		}
		
		return voted;
		
	}

}
