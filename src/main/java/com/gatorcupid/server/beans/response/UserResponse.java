package com.gatorcupid.server.beans.response;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gatorcupid.server.model.User;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
	
	private Long id;
	private String name;
	private String email;
	private Integer gender;
	private String birthYear;
	private Integer interestedIn;
	private Integer intention;
	private String about;
	private String major;
	private Integer isProfileCreated;
	private String profilePic;
	private Integer age;
	
	@JsonProperty("id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@JsonProperty("email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@JsonProperty("gender")
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	@JsonProperty("birthYear")
	public String getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}
	@JsonProperty("interestedIn")
	public Integer getInterestedIn() {
		return interestedIn;
	}
	public void setInterestedIn(Integer interestedIn) {
		this.interestedIn = interestedIn;
	}
	@JsonProperty("intention")
	public Integer getIntention() {
		return intention;
	}
	public void setIntention(Integer intention) {
		this.intention = intention;
	}
	@JsonProperty("about")
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	@JsonProperty("major")
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	@JsonProperty("isProfileCreated")
	public Integer getIsProfileCreated() {
		return isProfileCreated;
	}
	public void setIsProfileCreated(Integer isProfileCreated) {
		this.isProfileCreated = isProfileCreated;
	}
	@JsonProperty("profilePic")
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	@JsonProperty("age")
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public UserResponse() {}
	
	public UserResponse(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.gender = user.getGender() != null ? user.getGender().getValue() : null;
		this.birthYear = user.getBirthYear() != null ? user.getBirthYear().toString() : null;
		this.interestedIn = user.getInterestedIn() != null ? user.getInterestedIn().getValue() : null;
		this.intention = user.getIntention() != null ? user.getIntention().getValue() : null;
		this.about = user.getAbout();
		this.major = user.getMajor();
		this.isProfileCreated = user.getIsProfileCreated() != null ? user.getIsProfileCreated().getValue() : null;
		this.profilePic = (user.getProfilePic() != null && !user.getProfilePic().isEmpty()) ? user.getProfilePic() : null;
		this.age = user.getBirthYear() != null ? calculateUserAge() : null;
	}
	
	@Override
	public String toString() {
		String str = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			str =  mapper.writeValueAsString(this);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public Integer calculateUserAge(){

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(this.getBirthYear());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(date == null) return 0;

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(date);

        Integer year = dob.get(Calendar.YEAR);
        Integer month = dob.get(Calendar.MONTH);
        Integer day = dob.get(Calendar.DAY_OF_MONTH);

        dob.set(year, month+1, day);

        Integer age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }
        return age;
    }
	
	
}
