package com.jsware.sms.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import java.util.logging.Level; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jsware.sms.constants.AppConstants;
import com.jsware.sms.interfaces.EmailService;
import com.jsware.sms.model.Email;
import com.jsware.sms.model.TextMessage;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Controller
public class SendController {
	
	private final static Logger LOGGER =  
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 
	
	@Autowired
	EmailService emailservice;

	@Autowired
	public SendController()
	{
		Twilio.init(
				AppConstants.api_keys.ACCOUNT_SID.getName(),
				AppConstants.api_keys.AUTH_ID.getName()
				);
		
	}
	@RequestMapping(value="/", method=RequestMethod.GET)
	@ResponseBody
	public String hello(@RequestBody TextMessage tm)
	{ 
		return "hello motto"; 
	}
	
	@RequestMapping(value="/send", method=RequestMethod.POST)
	@ResponseBody
	public Object send(@RequestBody TextMessage tm)
	{
		try {
			return Message.creator(
					new PhoneNumber(tm.getPhone_number()), 
					new PhoneNumber(AppConstants.api_keys.PHONE_NUMBER.getName()),
					tm.getMessage())
			.create();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/sendEmail", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> sendEmail(@RequestBody Email email)
	{
		try {
			emailservice.sendEmail(email);
			return ResponseEntity
		            .status(HttpStatus.ACCEPTED)                 
		            .body(email);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,e.getMessage());
			return ResponseEntity
		            .status(HttpStatus.NOT_ACCEPTABLE)                 
		            .body(null);
		}
	}
}


