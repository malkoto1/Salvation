package org.elsys.salvation.client;

import java.util.Date;
import java.util.HashSet;

public class Person {
	private String name;
	private HashSet<Date> unavailableDates;
	private boolean leader;
	private boolean reviewer;

	public Person() {
		super();
		this.name = new String();
		this.unavailableDates = new HashSet<Date>();
		leader = false;
		reviewer = false;
	}
	
	public Person(String name, HashSet<Date> unavailableDates, boolean leader, boolean reviewer) {
		super();
		this.name = name;
		this.unavailableDates = unavailableDates;
		this.leader = leader;
		this.reviewer = reviewer;
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

	public boolean isLeader() {
		return leader;
	}

	public void setLeader(boolean leader) {
		this.leader = leader;
	}

	public boolean isReviewer() {
		return reviewer;
	}

	public void setReviewer(boolean reviewer) {
		this.reviewer = reviewer;
	}

}
