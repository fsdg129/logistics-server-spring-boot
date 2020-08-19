package com.yaozuw.logistics_server.service;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yaozuw.logistics_server.entity.properties.Identification;
import com.yaozuw.logistics_server.entity.properties.Priviledge;
import com.yaozuw.logistics_server.entity.properties.User;
import com.yaozuw.logistics_server.exception.RepeatedUsernameException;
import com.yaozuw.logistics_server.exception.UserException;

@Component
@Transactional
public class UserServiceImp implements UserService {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public User addUser(String username, String rawPassword, Priviledge priviledge) {
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(encoder.encode(rawPassword));
		user.setPriviledge(priviledge);

		em.persist(user);
		
		return user;
	}
	
	//nullable
	public User getUserById(Long id) {
		
		User user = em.find(User.class, id);
		
		return user;
	}
	
	//nullable
	public User getUserByUsername(String username) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :name", User.class);
		query.setParameter("name", username);
		List<User> list = query.getResultList();
		
		return list.isEmpty() ? null : list.get(0);
	}
	
	
	public boolean boolRepeatedUsername(String username) {
		
		if( this.getUserByUsername(username) == null ) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public User updateUsername(Long id, String username) {
		
		User user = this.getUserById(id);
		if(user == null) {
			
			return null;
		} else {
			user.setUsername(username);
			em.refresh(user);
			
			return user;
		}
	}
	
	public User updatePassword(Long id, String password) {
		
		User user = this.getUserById(id);
		if(user == null) {
			
			return null;
		} else {
			user.setPassword(password);
			em.refresh(user);
			
			return user;
		}
	}
	
	public User updatePriviledge(Long id, Priviledge priviledge) {
		
		User user = this.getUserById(id);
		if(user == null) {
			
			return null;
		} else {
			user.setPriviledge(priviledge);
			em.refresh(user);
			
			return user;
		}
	}
	
	
	

}

/*
 * if( User.boolValidUsername(username) == false ) { throw new
 * IllegalArgumentException("Invalid username"); } if(
 * this.boolRepeatedUsername(username) ) { throw new
 * RepeatedUsernameException(); }
 */