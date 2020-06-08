package com.vgamebase.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "vgamebase.audit")
public class Audit implements Serializable {

	private static final long serialVersionUID = -4096888939812857252L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Column(name="table_name", nullable=false)
	private String table;
	
	@NotNull
	@Column(name="line", nullable=false)
	private long row;
	
	@NotEmpty
	@Column(name="new_entity", nullable=false)
	private String newEntity;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@NotNull
	@Column(name="create_user", nullable=false)
	private long createUser;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@NotNull
	@Column(name="modified_user", nullable=false)
	private long modifiedUser;
	
	public Audit() {
		super();
	}

	public Audit(long id, String table, long row, String newEntity, Date createDate, long createUser, Date modifiedDate,
			long modifiedUser) {
		this.id = id;
		this.table = table;
		this.row = row;
		this.newEntity = newEntity;
		this.createDate = createDate;
		this.createUser = createUser;
		this.modifiedDate = modifiedDate;
		this.modifiedUser = modifiedUser;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public long getRow() {
		return row;
	}

	public void setRow(long row) {
		this.row = row;
	}

	public String getNewEntity() {
		return newEntity;
	}

	public void setNewEntity(String newEntity) {
		this.newEntity = newEntity;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(long createUser) {
		this.createUser = createUser;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public long getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(long modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	@Override
	public String toString() {
		return "Audit [id=" + id + ", table=" + table + ", row=" + row + ", newEntity=" + newEntity + ", createDate="
				+ createDate + ", createUser=" + createUser + ", modifiedDate=" + modifiedDate + ", modifiedUser="
				+ modifiedUser + "]";
	}
	
}
