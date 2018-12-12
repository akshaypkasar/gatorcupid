package com.gatorcupid.server.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gatorcupid.server.beans.request.UpdateUserProfileRequest;
import com.gatorcupid.server.constants.Gender;
import com.gatorcupid.server.constants.Intention;
import com.gatorcupid.server.constants.InterestedIn;
import com.gatorcupid.server.constants.State;
import com.gatorcupid.server.constants.Status;

@Entity
@Table(name="user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="gender")
	private Gender gender;
	
	@Column(name="password")
	private String password;
	
	@Column(name="birth_year")
	private Date birthYear;
	
	@Column(name="interested_in")
	private InterestedIn interestedIn;
	
	@Column(name="intention")
	private Intention intention;
	
	@Column(name="about")
	private String about;
	
	@Column(name="major")
	private String major;
	
	@Column(name="created_ts")
	private Timestamp createdTs;
	
	@Column(name="updated_ts")
	private Timestamp updatedTs;
	
	@Column(name="temp_code")
	private String tempCode;

	@Column(name="is_validated")
	private State isValidated;
	
	@Column(name="email_sent")
	private State isEmailSent;
	
	@Column(name="status")
	private Status status;
	
	@Column(name="is_profile_created")
	private State isProfileCreated;
	
	/*@OneToMany(mappedBy="user")
	private List<UserSession> userSessions = new ArrayList<>();*/
	/*@OneToMany(mappedBy="user")
	private List<ProfilePic> profilePics = new ArrayList<>();*/
	
	@Column(name="profilePic")
	private String profilePic;

	public User() {}

	public User(String email, String password, String tempCode) {
		super();
		this.email = email;
		this.password = password;
		this.createdTs = new Timestamp(System.currentTimeMillis());
		this.updatedTs = new Timestamp(System.currentTimeMillis());
		this.tempCode = tempCode;
		this.isValidated = State.FALSE;
		this.isEmailSent = State.FALSE;
		this.status=Status.INACTIVE;
		this.isProfileCreated=State.FALSE;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(Date birthYear) {
		this.birthYear = birthYear;
	}

	public InterestedIn getInterestedIn() {
		return interestedIn;
	}

	public void setInterestedIn(InterestedIn interestedIn) {
		this.interestedIn = interestedIn;
	}

	public Intention getIntention() {
		return intention;
	}

	public void setIntention(Intention intention) {
		this.intention = intention;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
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

	public String getTempCode() {
		return tempCode;
	}

	public void setTempCode(String tempCode) {
		this.tempCode = tempCode;
	}

	public State getIsValidated() {
		return isValidated;
	}

	public void setIsValidated(State isValidated) {
		this.isValidated = isValidated;
	}

	public State getIsEmailSent() {
		return isEmailSent;
	}

	public void setIsEmailSent(State isEmailSent) {
		this.isEmailSent = isEmailSent;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public State getIsProfileCreated() {
		return isProfileCreated;
	}
	public void setIsProfileCreated(State isProfileCreated) {
		this.isProfileCreated = isProfileCreated;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	
}
