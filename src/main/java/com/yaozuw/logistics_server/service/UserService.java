package com.yaozuw.logistics_server.service;

import com.yaozuw.logistics_server.entity.properties.Priviledge;
import com.yaozuw.logistics_server.entity.properties.User;

public interface UserService {
	
	public User addUser(String username, String password, Priviledge priviledge);
	
	//nullable
	public User getUserById(Long id);
	
	//nullable
	public User getUserByUsername(String username);
	
	public boolean boolRepeatedUsername(String username);
	
	public User updateUsername(Long id, String username); 
	
	public User updatePassword(Long id, String password);
	
	public User updatePriviledge(Long id, Priviledge priviledge);
	
}
