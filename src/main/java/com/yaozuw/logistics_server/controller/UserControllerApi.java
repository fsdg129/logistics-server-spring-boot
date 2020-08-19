package com.yaozuw.logistics_server.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yaozuw.logistics_server.entity.properties.Priviledge;
import com.yaozuw.logistics_server.entity.properties.User;
import com.yaozuw.logistics_server.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserControllerApi {

	@Autowired
	UserService userService;
	
	@PostMapping("/")
	public ResponseTemplate register(@RequestBody ObjectNode json) {
		String username = json.get("username").asText();
		String password = json.get("password").asText();
		if(userService.boolRepeatedUsername(username)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The username has been used");
		} else {
			User user = userService.addUser(username, password, Priviledge.VISITOR);
			user.setPassword("");
			return new ResponseTemplate("succeeded", "", "", 0, user);
		}
		
	}
	
	@GetMapping("/{userId:\\d+}")
	public ResponseTemplate getUserById(@PathVariable String userId, HttpServletRequest request) {
		String username = request.getUserPrincipal().getName();
		User operatingUser = userService.getUserByUsername(username);
		User wantedUser = userService.getUserById(Long.valueOf(userId));
		if(wantedUser == null) {
			return new ResponseTemplate("failed", "", "User doesn't exist", 2, new Object());
		} else if( wantedUser.getUsername().equals(operatingUser.getUsername()) || 
				operatingUser.getPriviledge().getAuthoritise().contains(new SimpleGrantedAuthority("MANAGER")) ){
			wantedUser.setPassword("");
			return new ResponseTemplate("succeeded", "", "", 0, wantedUser);
		} else {
			return new ResponseTemplate("failed", "", "Access denied", 3, new Object());
		}
		
	}
	
	@GetMapping("/{username}")
	public ResponseTemplate getUserByUsername(@PathVariable String username, HttpServletRequest request) {

		User wantedUser = userService.getUserById(Long.valueOf(username));
		if(wantedUser == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		} else {
			Principal principal = request.getUserPrincipal();
			if(principal == null) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to get this resource");
			} else {
				String operatingUsername = principal.getName();
				User operatingUser = userService.getUserByUsername(operatingUsername);
				if( username.equals(operatingUsername) ||
						operatingUser.getPriviledge().getAuthoritise().contains(new SimpleGrantedAuthority("MANAGER")) ) {
					wantedUser.setPassword("");
					return new ResponseTemplate("succeeded", "", "", 0, wantedUser);
				} else {
					throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to get this resource");
				}
			}
		}
	}
	
}
