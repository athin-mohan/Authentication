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

@Controller
public class AuthenticationController {

	@RequestMapping("/showHomePage")
	public String showHomepage()
	{
		return "Homepage";
		
	} 
	@SuppressWarnings("null")
	@RequestMapping(value = "/sendrequest", method = RequestMethod.POST)
	public HttpServletResponse sendRequest(@RequestParam("uname") String username,@RequestParam("pwd") String pwd )
	{
		HttpServletResponse response1 = null;
		//HttpServletRequest request;
		String uandp= username+":"+pwd;
		String encodedup =Base64Utils.encodeToString(uandp.getBytes());
		response1.setHeader("Authorization","Basic "+encodedup );
		return response1;
		
	}
	
	
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/getFromSS", method = RequestMethod.POST)
	public HttpServletResponse getBasicAndSendForm(@RequestHeader String header,
			@RequestBody String body) {
		HttpServletResponse response = null;
		if (header != null && header.startsWith("Basic ")) {
		String encodedcred=header.substring(6).trim();
		String decodedcred=Base64Utils.decodeFromString(encodedcred).toString();
		String decodedUname=decodedcred.substring(0,decodedcred.indexOf(":"));
		String decodedPwd=decodedcred.substring(decodedcred.indexOf(":")+1);
		System.out.println(decodedUname);
		System.out.println(decodedPwd);
		Console console = null;
		console.printf(decodedUname, decodedUname);
		console.printf(decodedPwd, decodedPwd);
		response.setHeader("Uname",decodedUname);
		response.setHeader("Uname",decodedPwd);
		return response;
		} else {
			return response;
		}

	}

}
