package org.elsys.salvation.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class Defence {
	private ArrayList<DiplomaWork> diplomaWorks;
	private Date day ;
	private Person firstPerson;
	private Person secondPerson;
	private Person thirdPerson;
	private Person fourthPerson;	
	
	public Defence(ArrayList<DiplomaWork> diplomaWorks, Date day,
			Person firstPerson, Person secondPerson, Person thirdPerson,
			Person fourthPerson) {
		super();
		this.diplomaWorks = diplomaWorks;
		this.day = day;
		this.firstPerson = firstPerson;
		this.secondPerson = secondPerson;
		this.thirdPerson = thirdPerson;
		this.fourthPerson = fourthPerson;
	}

	public Defence() {
		super();
		this.diplomaWorks = new ArrayList<DiplomaWork>();
		this.day = new Date();
		this.firstPerson = new Person();
		this.secondPerson = new Person();
		this.thirdPerson = new Person();
		this.fourthPerson = new Person();
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

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Person getFirstPerson() {
		return firstPerson;
	}

	public void setFirstPerson(Person firstPerson) {
		this.firstPerson = firstPerson;
	}

	public Person getSecondPerson() {
		return secondPerson;
	}

	public void setSecondPerson(Person secondPerson) {
		this.secondPerson = secondPerson;
	}

	public Person getThirdPerson() {
		return thirdPerson;
	}

	public void setThirdPerson(Person thirdPerson) {
		this.thirdPerson = thirdPerson;
	}

	public Person getFourthPerson() {
		return fourthPerson;
	}

	public void setFourthPerson(Person fourthPerson) {
		this.fourthPerson = fourthPerson;
	}

}