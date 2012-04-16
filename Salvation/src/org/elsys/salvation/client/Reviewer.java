package org.elsys.salvation.client;

import java.util.Date;
import java.util.HashSet;

public class Reviewer {
	private String name;
	private HashSet<Date> unavailableDates;
	
	public Reviewer(String name, HashSet<Date> unavailableDates) {
		super();
		this.name = name;
		this.unavailableDates.addAll(unavailableDates);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public HashSet<Date> getUnavailableDates() {
		return unavailableDates;
	}
	
	public void setUnavailableDates(HashSet<Date> unavailableDates) {
		this.unavailableDates.addAll(unavailableDates) ;
	}

}
