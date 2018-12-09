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
import com.gatorcupid.server.util.CommonUtil;

@Entity
@Table(name="usersession")
public class UserSession implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="access_token")
	private String accessToken;	
	
	@Column(name="created_ts")
	private Timestamp createdTs;
	
	@Column(name="updated_ts")
	private Timestamp updatedTs;
	
	@Column(name="status")
	private Status status;	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public UserSession(){}
	
	public UserSession(User user) {
		//access token is md5 hash of email and timestamp
		this.accessToken = CommonUtil.getMD5Hash(user.getEmail() + Long.toString(System.currentTimeMillis()));
		this.createdTs = new Timestamp(System.currentTimeMillis());
		this.status = Status.ACTIVE;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
