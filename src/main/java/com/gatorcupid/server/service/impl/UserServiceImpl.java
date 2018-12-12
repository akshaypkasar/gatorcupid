package com.gatorcupid.server.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gatorcupid.server.beans.request.UpdateUserProfileRequest;
import com.gatorcupid.server.beans.response.UserResponse;
import com.gatorcupid.server.constants.Errorcode;
import com.gatorcupid.server.constants.GcConstant;
import com.gatorcupid.server.constants.Gender;
import com.gatorcupid.server.constants.Intention;
import com.gatorcupid.server.constants.InterestedIn;
import com.gatorcupid.server.constants.State;
import com.gatorcupid.server.dao.UserDao;
import com.gatorcupid.server.exception.GCException;
import com.gatorcupid.server.model.User;
import com.gatorcupid.server.model.UserSession;
import com.gatorcupid.server.service.UserService;
import com.gatorcupid.server.util.CommonUtil;
import com.gatorcupid.server.util.EmailUtil;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userdao;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	private Environment environment;
	
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Override
	public void sendSignupEmail(String email) throws UnirestException, GCException {
		User user = userdao.findUnregisteredByEmail(email);
		if(user != null && user.getIsValidated() == State.TRUE) {
			logger.error("EMAIL_ALREADY_REGISTERED");
			throw new GCException(Errorcode.EMAIL_ALREADY_REGISTERED, "EMAIL_ALREADY_REGISTERED");
		}

		String randomNumber = RandomStringUtils.randomNumeric(GcConstant.OTP_LENGTH);
        String emailContent = new String(GcConstant.REGISTER_EMAIL_CONTENT);
        emailContent = emailContent.replaceAll("<CODE>", randomNumber);
        logger.info("Email Content:"+emailContent);
        
        if(user == null) {
        	user = new User(email, CommonUtil.getMD5Hash(randomNumber), randomNumber);
        }else {
        	user.setPassword(CommonUtil.getMD5Hash(randomNumber));
        	user.setTempCode(randomNumber);
        }
        
        userdao.save(user);
		// send email and code
		JsonNode result = emailUtil.sendEmail(email, environment.getProperty("email.mailgun.from"), GcConstant.REGISTER_EMAIL_SUBJECT, emailContent);
		logger.info("Email Sent to User:"+user.getId());
		logger.info(result);
		
		user.setUpdatedTs(new Timestamp(System.currentTimeMillis()));
		user.setIsEmailSent(State.TRUE);
		userdao.save(user);
		
		UserSession usersession = new UserSession(user);
	}

	@Override
	public User validateUserCredentials(String email, String code) throws GCException {
		User user = userdao.findUnregisteredByEmail(email);
		if(user == null) {
			logger.error("INVALID_EMAIL_ADDRESS");
			throw new GCException(Errorcode.INVALID_EMAIL_ADDRESS, "INVALID_EMAIL_ADDRESS");
		}
		/*if(user != null && user.getIsValidated() == State.TRUE) {
			logger.error("EMAIL_ALREADY_REGISTERED");
			throw new GCException(Errorcode.EMAIL_ALREADY_REGISTERED, "EMAIL_ALREADY_REGISTERED");
		}*/
		
		if(!user.getPassword().equals(CommonUtil.getMD5Hash(code))) {
			logger.error("INVALID_CREDENTIALS");
			throw new GCException(Errorcode.INVALID_CREDENTIALS, "INVALID_CREDENTIALS");
		}
		user.setIsValidated(State.TRUE);
		userdao.save(user);
		return user;
	}

	@Override
	public void setUserPassword(User user) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updateUserProfile(User user, UpdateUserProfileRequest request) throws ParseException {
		if(request.getName() !=null && !request.getName().isEmpty())
			user.setName(request.getName());
		
		if(request.getGender()!=null)
			user.setGender(Gender.valueOf(request.getGender()));
		
		if(request.getBirthYear() != null && !request.getBirthYear().isEmpty()) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = sdf1.parse(request.getBirthYear());
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			user.setBirthYear(sqlDate);
		}
		
		//if(request.getAbout().isEmpty())
		user.setAbout(request.getAbout());
		
		if(request.getIntention()!=null)
			user.setIntention(Intention.valueOf(request.getIntention()));
		
		if(request.getInterestedIn()!=null)
			user.setInterestedIn(InterestedIn.valueOf(request.getInterestedIn()));
		
		user.setAbout(request.getMajor());
		
		/*if(request.getProfilePicAction() == 0) {
			user.setProfilePic(null);
		}else if(request.getProfilePicAction() == 1) {
			user.setProfilePic(request.getProfilePic());
		}*/
		user.setProfilePic(request.getProfilePic());
		
		user.setIsProfileCreated(State.TRUE);
		userdao.saveAndFlush(user);
	}

	@Override
	public List<UserResponse> fetchBrowseList(User user, Integer pageSize, Integer pageNo) {
		
		pageSize = pageSize == null ? 1000 : pageSize;	//1000 is the max pageSize supported by spring data JPA
		pageNo = pageNo == null ? 0 : pageNo;
		
		Pageable pageable = new PageRequest(pageNo, pageSize);
		Page<User> userProfiles = userdao.findBrowsingListByUserId(user.getId(), Gender.valueOf(user.getInterestedIn().getValue()), pageable);
		List<UserResponse> userResponseList = new ArrayList<>();
		for(User u: userProfiles.getContent()) {
			UserResponse ur = new UserResponse(u);
			userResponseList.add(ur);
		}
		return userResponseList;
	}
	
	

}
