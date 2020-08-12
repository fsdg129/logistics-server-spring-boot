package com.yaozuw.logistics_server.entity.logistics;

import java.util.Set;

import com.yaozuw.logistics_server.entity.properties.Staff;
import com.yaozuw.logistics_server.entity.properties.Truck;

public class Shipment {

	private ShipmentMap map;
	private Staff manager;
	private Staff creator;
	private Set<Staff> workers;
	private Set<Goods> goods;
	
	private Set<TruckTravelReport> reports;
}
