package com.gatorcupid.server.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gatorcupid.server.constants.Status;


/**
 * The persistent class for the profile_pic database table.
 * 
 */
@Entity
@Table(name="profile_pic")
public class ProfilePic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Column(name="created_ts")
	private Timestamp createdTs;

	@Column(name="updated_ts")
	private Timestamp updatedTs;

	@Column(name="status")
	private Status status;

	@Column(name="url")
	private String url;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public ProfilePic() {}
	
	public ProfilePic(User user) {
		this.user = user;
		this.createdTs = new Timestamp(System.currentTimeMillis());
		this.updatedTs = new Timestamp(System.currentTimeMillis());
		this.status = Status.ACTIVE;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Timestamp createdTs) {
		this.createdTs = createdTs;
	}

	public Timestamp getUpdatedTs() {
		return updatedTs;
	}

	public void setUpdatedTs(Timestamp updatedTs) {
		this.updatedTs = updatedTs;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}