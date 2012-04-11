package org.elsys.salvation.client;

import java.util.ArrayList;
import java.util.Date;

import com.smartgwt.client.widgets.calendar.CalendarEvent;

public class DiplomaLeader {
	private String name;
	private ArrayList<Date> unavailableDates;
	//when...
	public DiplomaLeader(String name, ArrayList<Date> unavailableDates) {
		super();
		this.name = name;
		this.unavailableDates = unavailableDates;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Date> getUnavailableDates() {
		return unavailableDates;
	}
	public void setUnavailableDates(ArrayList<Date> unavailableDates) {
		this.unavailableDates = unavailableDates;
	}
	public void addDate(Date date){
		unavailableDates.add(date);
	
	}
	public void setUnavailableDates(CalendarEvent[] data) {
		for(int i=0; i<data.length; i++) {
			unavailableDates.add(data[i].getStartDate());
		}
		
	}
}
