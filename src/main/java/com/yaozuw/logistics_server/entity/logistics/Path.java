package com.yaozuw.logistics_server.entity.logistics;

import java.time.Duration;

import org.jgrapht.graph.DefaultWeightedEdge;

public class Path extends DefaultWeightedEdge {

	private Location from;
	private Location to;
	private Duration expectedTime;
	//km
	private int distance;
}
