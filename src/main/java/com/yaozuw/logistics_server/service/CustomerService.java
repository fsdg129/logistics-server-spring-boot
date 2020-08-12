package com.yaozuw.logistics_server.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yaozuw.logistics_server.entity.properties.ContactInformation;
import com.yaozuw.logistics_server.entity.properties.Customer;
import com.yaozuw.logistics_server.entity.properties.User;

@Component
@Transactional
public class CustomerService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UserService userService;
	
	public Customer addCustomer(Long userID, String realName, String phoneNumber, 
			String email) {
		
		ContactInformation contactInformation = new ContactInformation(realName, 
				phoneNumber, email);
		Customer customer = new Customer();
		customer.setContactInformation(contactInformation);
		User user = userService.getUserById(userID);
		customer.setUser(user);

		em.persist(customer);
		//em.merge(user);
		
		return customer;
	}
	
	public Customer getStaffById(Long id) {
		
		Customer customer = em.find(Customer.class, id);
		
		return customer;
	}
}
