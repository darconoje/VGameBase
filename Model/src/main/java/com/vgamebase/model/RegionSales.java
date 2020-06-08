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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vgamebase.region_sales")
public class RegionSales implements Serializable {

	private static final long serialVersionUID = -4687403711641516790L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(name="num_sales", nullable=false)
	private float sales;
	
	@JoinColumn(name = "region_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Region region;
	
	@JoinColumn(name = "game_platform_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private GamePlatform gamePlatform;
	
	public RegionSales() {
		super();
	}

	public RegionSales(long id, float sales, Region region) {
		this.id = id;
		this.sales = sales;
		this.region = region;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getSales() {
		return sales;
	}

	public void setSales(float sales) {
		this.sales = sales;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
	
	public GamePlatform getGamePlatform() {
		return gamePlatform;
	}

	public void setGamePlatform(GamePlatform gamePlatform) {
		this.gamePlatform = gamePlatform;
	}

	@Override
	public String toString() {
		return "RegionSales [id=" + id + ", sales=" + sales + ", region=" + region + "]";
	}

}
