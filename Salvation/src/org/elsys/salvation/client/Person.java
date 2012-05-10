package org.elsys.salvation.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class Person {
	private String name;
	private ArrayList<Date> availableDates;

	public Person() {
		super();
		this.name = new String();
		this.availableDates = new ArrayList<Date>();
	}

	public Person(String name, ArrayList<Date> availableDates) {
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

	public ArrayList<Date> getAvailableDates() {
		return availableDates;
	}

	public void setAvailableDates(ArrayList<Date> availableDates) {
		this.availableDates.addAll(availableDates);
	}

}