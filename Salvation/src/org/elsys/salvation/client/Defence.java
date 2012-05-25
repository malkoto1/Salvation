package org.elsys.salvation.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Defence implements Serializable{
	private ArrayList<DiplomaWork> diplomaWorks;
	private Date day ;
	private ArrayList<Person> jury;	
	
	public Defence(ArrayList<DiplomaWork> diplomaWorks, Date day, ArrayList<Person> jury) {
		super();
		this.diplomaWorks = diplomaWorks;
		this.day = day;
		this.jury = jury;
	}

	public Defence() {
		super();
		this.diplomaWorks = new ArrayList<DiplomaWork>();
		this.day = new Date();
		this.jury = new ArrayList<Person>();
	}

	public ArrayList<DiplomaWork> getDiplomaWorks() {
		return diplomaWorks;
	}

	public void setDiplomaWorks(ArrayList<DiplomaWork> diplomaWorks) {
		this.diplomaWorks = diplomaWorks;
	}

	public void addDiploma(DiplomaWork work) {
		this.diplomaWorks.add(work);
	}
	
	public void removeDiploma() {
		this.diplomaWorks.remove(diplomaWorks.size()-1);
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public ArrayList<Person> getJury() {
		return jury;
	}

	public void setJury (ArrayList<Person> jury) {
		this.jury = jury;
	}

}