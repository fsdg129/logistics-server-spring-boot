package com.yaozuw.logistics_server.entity.logistics;

import java.util.Set;

import com.yaozuw.server_for_express.entity.properties.Staff;
import com.yaozuw.server_for_express.entity.properties.Truck;

public class Shipment {

	private ShipmentMap map;
	private Staff manager;
	private Staff creator;
	private Set<Staff> workers;
	private Set<Goods> goods;
	
	private Set<TruckTravelReport> reports;
}
