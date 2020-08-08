package com.yaozuw.logistics_server.entity.logistics;

import java.util.ArrayList;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;


public class ShipmentMap {

	private Graph<Location,Path> map;
	
	public ShipmentMap() {
		this.map = new SimpleDirectedWeightedGraph<>(Path.class);
	}
	
	public ShipmentMap(ArrayList<Location> locations) {
		this();
		this.addLocations(locations);
	}
	public void addLocations(ArrayList<Location> locations) {
		for(Location location:locations) {
			this.addLocation(location);
		}
	}
	public boolean addLocation(Location location) {
			return map.addVertex(location);
	}
}
