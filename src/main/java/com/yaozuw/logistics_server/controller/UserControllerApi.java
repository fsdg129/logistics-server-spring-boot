package com.yaozuw.logistics_server.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yaozuw.logistics_server.entity.properties.Priviledge;
import com.yaozuw.logistics_server.entity.properties.User;
import com.yaozuw.logistics_server.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserControllerApi {

	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public ResponseTemplate doRegister(@RequestParam("username") String username,
			@RequestParam("password") String password ) {
		
		if(userService.boolRepeatedUsername(username)) {
			return new ResponseTemplate("failed", "username has been used", 1, new Object());

		} else {
			User user = userService.addUser(username, password, Priviledge.VISITOR);
			return new ResponseTemplate("succeeded", "", 0, user);
		}
		
	}
	
	@GetMapping("/signin")
	public ModelAndView signin(HttpSession session) {
		
		Boolean boolSignin = (Boolean) session.getAttribute("boolSignin");
		if(boolSignin == null || boolSignin.equals(Boolean.FALSE) ) {
			return new ModelAndView( "signin.html" );
		} else {
			return new ModelAndView( "welcome.html" );
		}
	}
	
	@PostMapping("/signin")
	public ModelAndView doSignin(@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpSession session) {
		
		User user = userService.getUserByUsername(username);
		if(user == null || password.equals( user.getPassword() )==false ) {
			return new ModelAndView( "signin.html", Map.of("status", "failed", 
					"message", "either username or password is not correct", "error_code", "2") );
		} else {
			session.setAttribute("boolSignin", Boolean.TRUE);
			return new ModelAndView( "welcome.html" );
		} 
	}

	
}
