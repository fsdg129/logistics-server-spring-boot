package com.yaozuw.logistics_server.entity.logistics;

import java.time.Instant;

import com.yaozuw.server_for_express.entity.properties.Staff;
import com.yaozuw.server_for_express.entity.properties.Truck;

public class GoodsTripReporting {
	
	private Path path;
	private TripStatus tripStatus;
	private Truck truck;
	private Staff driver;
	private String description;
	
	private Staff uploader;
	private Instant departureTime;
	private Staff downlowder;
	private Instant arrivalTime;

}



enum TripStatus {
	CREATED, DELIVERING, FINISHED;
}