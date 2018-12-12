package com.gatorcupid.server.controller;

import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gatorcupid.server.beans.request.SignupRequest;
import com.gatorcupid.server.beans.request.UpdateUserProfileRequest;
import com.gatorcupid.server.beans.response.BrowseResponse;
import com.gatorcupid.server.beans.response.ResponseBean;
import com.gatorcupid.server.beans.response.UserResponse;
import com.gatorcupid.server.constants.ApiUrl;
import com.gatorcupid.server.constants.Errorcode;
import com.gatorcupid.server.dao.UserDao;
import com.gatorcupid.server.exception.GCException;
import com.gatorcupid.server.model.User;
import com.gatorcupid.server.service.UserService;


@Controller
@RequestMapping(ApiUrl.API)
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserDao userDao;
	
	private static final Logger logger = Logger.getLogger(UserController.class);

	@ResponseBody
	@RequestMapping(value = ApiUrl.SIGNUP, method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> sendSignupCode(@RequestBody SignupRequest request)
			throws Exception {

		logger.info("SignUP API >>> " +request);
		request.validateRequest();
		
		if(request.getCode() == null) {
			userService.sendSignupEmail(request.getEmail());
		}
		ResponseBean responseBean = new ResponseBean(HttpStatus.OK.value(), "Success");
		return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = ApiUrl.SIGNIN, method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> signin(@RequestBody SignupRequest request)
			throws GCException {

		logger.info("SignIn API >>> " +request);
		request.validateRequest();
		
		if(request.getCode() == null) {
			logger.error("INVALID_CREDENTIALS");
			throw new GCException(Errorcode.INVALID_CREDENTIALS, "INVALID_CREDENTIALS");
		}
		User user = userService.validateUserCredentials(request.getEmail(), request.getCode());
		UserResponse ur = new UserResponse(user);
		
		ResponseBean responseBean = new ResponseBean(HttpStatus.OK.value(), "Success",ur);
		logger.info("Signin API Response>>>"+ responseBean);
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = ApiUrl.UPDATE_USER_PROFILE, method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> updateUserProfile(@PathVariable("id") Long userId, @RequestBody UpdateUserProfileRequest request)
			throws GCException, ParseException {

		logger.info("UpdateUserProfile API >>> " +request);
		request.validateRequest();
		User user = userDao.findUserById(userId);
		if(user == null) {
			throw new GCException(Errorcode.USER_NOT_FOUND, "USER_NOT_FOUND");
		}
		userService.updateUserProfile(user, request);
		
		ResponseBean responseBean = new ResponseBean(HttpStatus.OK.value(), "Success");
		return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = ApiUrl.GET_USER_PROFILE, method = RequestMethod.GET)
	public ResponseEntity<ResponseBean> getUserProfile(@PathVariable("id") Long userId)
			throws GCException {

		logger.info("GetUserProfile API >>> userId:" + userId.toString());
		User user = userDao.findUserById(userId);
		if(user == null) {
			throw new GCException(Errorcode.USER_NOT_FOUND, "USER_NOT_FOUND");
		}
		
		UserResponse ur = new UserResponse(user);
		
		ResponseBean responseBean = new ResponseBean(HttpStatus.OK.value(), "Success",ur);
		logger.info("GetUserProfile API Response>>>"+ responseBean);
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = ApiUrl.GET_BROWSE_PROFILE, method = RequestMethod.GET)
	public ResponseEntity<ResponseBean> getBrowseProfile(@PathVariable("id") Long userId, 
			@RequestParam(value= "pageSize", defaultValue = "1000")Integer pageSize, 
			@RequestParam(value= "pageNo", defaultValue = "0") Integer pageNo)
			throws GCException {

		logger.info("GetBrowseProfile API >>> userId:" + userId.toString() + "\n>>> offset:" + pageSize + "\n>>> pageSize:" + pageNo);
		User user = userDao.findUserById(userId);
		if(user == null) {
			throw new GCException(Errorcode.USER_NOT_FOUND, "USER_NOT_FOUND");
		}
		
		BrowseResponse browseResponse = new BrowseResponse(userService.fetchBrowseList(user, pageSize, pageNo));
		
		ResponseBean responseBean = new ResponseBean(HttpStatus.OK.value(), "Success",browseResponse);
		logger.info("GetBrowseProfile API Response>>>"+ responseBean);
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
	}
	
}
