package com.yaozuw.logistics_server.entity.logistics;

import java.time.Instant;

public class Goods {

	//only used for storing the number of goods over a period(not repeatable)
	private int id;
	//A id used for track the good(could repeat over a month)
	private String trackingNumber;
	//A id used for other companies
	private String descriptionNumber;
	private String description;
	private Instant createTime;
	//The order the goods belong to
	private Order order;
	//The status of the goods. The information for transporting.
	private Status status;
}



