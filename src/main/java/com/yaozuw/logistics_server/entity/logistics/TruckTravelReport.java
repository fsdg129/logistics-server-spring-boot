package com.yaozuw.logistics_server.entity.logistics;

import java.time.Instant;
import java.util.Set;

import com.yaozuw.server_for_express.entity.properties.Staff;
import com.yaozuw.server_for_express.entity.properties.Truck;

public class TruckTravelReport {

	private Truck truck;
	private Set<Staff> drivers;
	
	private Instant departureTime;
	private Instant arrivalTime;
	
	private String logging;
	
}
