package org.elsys.salvation.client;

import java.util.Date;
import java.util.HashSet;

public class Person {
	private String name;
	private HashSet<Date> availableDates;

	public Person() {
		super();
		this.name = new String();
		this.availableDates = new HashSet<Date>();
	}

	public Person(String name, HashSet<Date> availableDates) {
		super();
		this.name = name;
		this.availableDates = availableDates;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashSet<Date> getAvailableDates() {
		return availableDates;
	}

	public void setAvailableDates(HashSet<Date> availableDates) {
		this.availableDates.addAll(availableDates);
	}

}