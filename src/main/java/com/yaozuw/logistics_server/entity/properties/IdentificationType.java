package com.yaozuw.logistics_server.entity.properties;

import java.util.Map;

public enum IdentificationType {
	CUSTOMER, STAFF;
}



/*
public class IdentificationFactory {
	
	public final IdentificationType identificationType;
	public final Identification identification;
	private static final Map<String, IdentificationType> identificationTypeMapper = 
		Map.of( Staff.class.getName(), IdentificationType.STAFF, 
				Customer.class.getName(), IdentificationType.CUSTOMER );

	public IdentificationFactory(Identification identification){
		this.identification = identification;
		String key = identification.getClass().getName();
		if( identificationTypeMapper.containsKey(key) ) {
			this.identificationType = identificationTypeMapper.get(key);
		} else {
			throw new IllegalArgumentException("Con't convert the intup to a known identification type");
		}
	}
	
}
*/


