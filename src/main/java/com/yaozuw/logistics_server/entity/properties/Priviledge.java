package com.yaozuw.logistics_server.entity.properties;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Priviledge {
	DEVELOPER, MANAGER, WORKER, VISITOR;
	
	private static Map< Priviledge, Set<GrantedAuthority> > mapper = Priviledge.initialMapper();
	
	public Set<GrantedAuthority> getAuthoritise(){
		
		return Priviledge.mapper.get(this);
	}
			
	private static Set<GrantedAuthority> getAuthoritiseHelper(Priviledge priviledge){
		
		Set<GrantedAuthority> authoritise = new HashSet<>();
		
		switch(priviledge) {
		case DEVELOPER:
			authoritise.add( new SimpleGrantedAuthority("DEVELOPER") );
		case MANAGER:
			authoritise.add( new SimpleGrantedAuthority("MANAGER") );
		case WORKER:
			authoritise.add( new SimpleGrantedAuthority("WORKER") );
		case VISITOR:
			authoritise.add( new SimpleGrantedAuthority("VISITOR") );			
		}
		
		return authoritise;
	}
	
	private static Map< Priviledge, Set<GrantedAuthority> > initialMapper(){
		
		Map< Priviledge, Set<GrantedAuthority> > mapper = new HashMap<>();
		for(Priviledge priviledge : Priviledge.values()) {
			mapper.put( priviledge, Priviledge.getAuthoritiseHelper(priviledge) );
		}
		
		return mapper;
	}
	
}
