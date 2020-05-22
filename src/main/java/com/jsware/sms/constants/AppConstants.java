package com.jsware.sms.constants;

import org.springframework.stereotype.Component;

@Component
public class AppConstants {
	
	public enum api_keys
	{
		ACCOUNT_SID("ACeb75d57385a81b60d6116ebb5e8266eb"),
		AUTH_ID("80a76d5115be0c03929155df8352456c"),
		PHONE_NUMBER("+19203253106");
		
		private String name;
		
		private api_keys(String name)
		{
			this.name=name;
		}
		
		public String getName()
		{
			return this.name;
		}
	}

}
