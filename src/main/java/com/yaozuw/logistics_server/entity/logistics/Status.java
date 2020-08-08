package com.yaozuw.logistics_server.entity.logistics;

import java.util.ArrayList;

public class Status {

	private GoodsStatus status;
	private ArrayList<GoodsTripReporting> report;

}



enum GoodsStatus {
	CREATED, DELIVERING, DELIVERED;
}