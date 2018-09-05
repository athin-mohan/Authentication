package com.capgemini.controller;

import java.io.Console;




import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
	
	@RequestMapping(value = "/getFromSS", method = RequestMethod.POST)
	public String getBasicAndSendForm(@RequestHeader(value="Authorization") String header) 
	{
		System.out.println("header is "+header);
		int len=("Authorization:Basic ").length();
		if (header != null && header.startsWith("Authorization: Basic ")) {
			
	    System.out.println("Header is for basic Authentication");
		String encodedcred=header.substring(len).trim();
		String decodedcred=new String(Base64Utils.decodeFromString(encodedcred));
		String decodedUname=decodedcred.substring(0,decodedcred.indexOf(":"));
		String decodedPwd=decodedcred.substring(decodedcred.indexOf(":")+1);
		System.out.println("decoded Username is: "+decodedUname);
		System.out.println("decoded Password is: "+decodedPwd);
	
		return "Success: "+decodedUname+decodedPwd;
		} else {
			
			System.out.println("Header for basic authentication not found");
			return "Failed";
		}

	}

}
