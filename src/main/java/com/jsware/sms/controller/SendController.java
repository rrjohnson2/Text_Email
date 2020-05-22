package com.jsware.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jsware.sms.constants.AppConstants;
import com.jsware.sms.model.TextMessage;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Controller
public class SendController {

	public SendController()
	{
		Twilio.init(
				AppConstants.api_keys.ACCOUNT_SID.getName(),
				AppConstants.api_keys.AUTH_ID.getName()
				);
	}
	
	@RequestMapping(value="/send", method=RequestMethod.POST)
	@ResponseBody
	public Object send(@RequestBody TextMessage tm)
	{
		return Message.creator(
				new PhoneNumber(tm.getPhone_number()), 
				new PhoneNumber(AppConstants.api_keys.PHONE_NUMBER.getName()),
				tm.getMessage())
		.create();
	}
}
