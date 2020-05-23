package com.jsware.sms.model;

import java.io.File;

public class Email {
	
		public String from;
		public String to;
		public String subject;
		public String content;
		public String password;
		public File[] attachments = new File[5]; 
}
