package com.yaozuw.logistics_server.entity.logistics;

import java.time.Instant;
import java.util.ArrayList;

import com.yaozuw.logistics_server.entity.properties.ContactInformation;

public class Order {

	//only used for storing the number of shipments over a period(not repeatable)
	private int id;
	//A id used for track the shipment(not repeatable)
	private String trackingNumber;
	//A id used for other companies
	private String descriptionNumber;
	private Location origin;
	private ContactInformation originContactPerson;
	private Location destination;
	private ContactInformation destinationContactPerson;
	private ArrayList<Goods> goods;
	private Instant createTime;
	private Instant departureTime;
	private Instant arriveTime;
	private String logging;
	private GoodsStatus status;
}
