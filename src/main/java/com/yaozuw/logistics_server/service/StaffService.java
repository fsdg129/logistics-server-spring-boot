package com.yaozuw.logistics_server.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yaozuw.server_for_express.entity.properties.ContactInformation;
import com.yaozuw.server_for_express.entity.properties.Position;
import com.yaozuw.server_for_express.entity.properties.Priviledge;
import com.yaozuw.server_for_express.entity.properties.Staff;
import com.yaozuw.server_for_express.entity.properties.User;

@Component
@Transactional
public class StaffService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UserService userService;
	
	public Staff addStaff(Long userID, String realName, String phoneNumber, 
			String email, Position position, Boolean boolChecked) {
		
		ContactInformation contactInformation = new ContactInformation(realName, 
				phoneNumber, email);
		Staff staff = new Staff();
		staff.setContactInformation(contactInformation);
		staff.setPosition(position);
		staff.setBoolChecked(boolChecked);
		User user = userService.getUserById(userID);
		staff.setUser(user);

		em.persist(staff);
		//em.merge(user);
		
		return staff;
	}
	
	public Staff getStaffById(Long id) {
		
		Staff staff = em.find(Staff.class, id);
		
		return staff;
	}
	
	

}
