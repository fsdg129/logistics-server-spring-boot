package com.yaozuw.logistics_server.entity.logistics;

import java.time.Instant;
import java.util.Set;

import com.yaozuw.logistics_server.entity.properties.Staff;
import com.yaozuw.logistics_server.entity.properties.Truck;

public class TruckTravelReport {

	private Truck truck;
	private Set<Staff> drivers;
	
	private Instant departureTime;
	private Instant arrivalTime;
	
	private String logging;
	
}
