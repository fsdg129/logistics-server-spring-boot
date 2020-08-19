package com.yaozuw.logistics_server.entity.properties;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends Identification {

	
}
