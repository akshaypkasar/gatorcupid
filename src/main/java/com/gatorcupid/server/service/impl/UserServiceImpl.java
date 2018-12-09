package com.gatorcupid.server.service.impl;

import java.sql.Timestamp;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.gatorcupid.server.constants.Errorcode;
import com.gatorcupid.server.constants.GcConstant;
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
		User user = userdao.findByEmail(email);
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
		User user = userdao.findByEmail(email);
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

}
