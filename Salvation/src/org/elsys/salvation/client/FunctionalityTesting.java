package org.elsys.salvation.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.junit.Test;

public class FunctionalityTesting {

	@Test
	public void diplomaWorkGetersTest() {
		DiplomaWork work = new DiplomaWork("name", "diplomants", new Person(
				"Leader", dateList()), new Person("Reviewer", dateList()));

		assertEquals("name", work.getName());
		assertEquals("diplomants", work.getDiplomants());
		assertEquals("Leader", work.getLeader().getName());
		assertEquals("Reviewer", work.getReviewer().getName());
	}

	@Test
	public void diplomaWorkSetersTest() {
		DiplomaWork work = new DiplomaWork();

		work.setName("name");
		work.setDiplomants("diplomants");
		work.setLeader(new Person("Leader", dateList()));
		work.setReviewer(new Person("Reviewer", dateList()));

		assertEquals("name", work.getName());
		assertEquals("diplomants", work.getDiplomants());
		assertEquals("Leader", work.getLeader().getName());
		assertEquals("Reviewer", work.getReviewer().getName());
	}

	@Test
	public void personGetersTest() {
		Person person = new Person("name", dateList());

		assertEquals("name", person.getName());
		assertEquals(dateList(), person.getAvailableDates());
	}

	@Test
	public void personSettersTest() {
		Person person = new Person();

		person.setName("name");
		person.setAvailableDates(dateList());

		assertEquals("name", person.getName());
		assertEquals(dateList(), person.getAvailableDates());
	}

	@Test
	public void softwareWorkFirstConstructorTest() {
		SoftwareWork work = new SoftwareWork();

		work.setName("name");
		work.setDiplomants("diplomants");
		work.setLeader(new Person("Leader", dateList()));
		work.setReviewer(new Person("Reviewer", dateList()));
		work.setType("type");

		assertEquals("name", work.getName());
		assertEquals("diplomants", work.getDiplomants());
		assertEquals("Leader", work.getLeader().getName());
		assertEquals("Reviewer", work.getReviewer().getName());
		assertEquals("type", work.getType());
	}

	@Test
	public void softwareWorkSecondConstructorTest() {
		SoftwareWork work = new SoftwareWork("name", "diplomants", new Person(
				"Leader", dateList()), new Person("Reviewer", dateList()), "type");

		assertEquals("name", work.getName());
		assertEquals("diplomants", work.getDiplomants());
		assertEquals("Leader", work.getLeader().getName());
		assertEquals("Reviewer", work.getReviewer().getName());
		assertEquals("type", work.getType());
	}

	@Test
	public void firstDefenceTest() {
		ArrayList<DiplomaWork> set = new ArrayList<DiplomaWork>();

		DiplomaWork diplomaWork = new DiplomaWork("name", "diplomants",
				new Person("Leader", dateList()), new Person("Reviewer",
						dateList()));
		SoftwareWork softwareWork = new SoftwareWork("name", "diplomants",
				new Person("Leader", dateList()), new Person("Reviewer",
						dateList()), "type");
		set.add(diplomaWork);
		set.add(softwareWork);
		Defence defence = new Defence(set,new Date(2012, 10, 10),
				new Person("Leader", dateList()), new Person("Reviewer",dateList()),
				new Person("Leader", dateList()), new Person("Reviewer",dateList()));

		assertEquals(set, defence.getDiplomaWorks());
		assertEquals(new Date(2012, 10, 10), defence.getDay());
		assertEquals("Leader", defence.getFirstPerson().getName());
		assertEquals(dateList(),defence.getFirstPerson().getAvailableDates());
		assertEquals("Reviewer", defence.getSecondPerson().getName());
		assertEquals(dateList(),defence.getSecondPerson().getAvailableDates());
		assertEquals("Leader", defence.getThirdPerson().getName());
		assertEquals(dateList(),defence.getThirdPerson().getAvailableDates());
		assertEquals("Reviewer", defence.getFourthPerson().getName());
		assertEquals(dateList(),defence.getFourthPerson().getAvailableDates());
	}
	
	@Test
	public void defenceSetDiplomaWorks(){
		Defence defence = new Defence();
		ArrayList<DiplomaWork> set = new ArrayList<DiplomaWork>();

		DiplomaWork diplomaWork = new DiplomaWork("name", "diplomants",
				new Person("Leader", dateList()), new Person("Reviewer",
						dateList()));
		SoftwareWork softwareWork = new SoftwareWork("name", "diplomants",
				new Person("Leader", dateList()), new Person("Reviewer",
						dateList()), "type");
		set.add(diplomaWork);
		set.add(softwareWork);
		defence.setDiplomaWorks(set);
		
		assertEquals(set, defence.getDiplomaWorks());
		
	}
	
	@Test
	public void defenceAddDiplomaWork(){
		Defence defence = new Defence();
		DiplomaWork diplomaWork = new DiplomaWork("name", "diplomants",
				new Person("Leader", dateList()), new Person("Reviewer",
						dateList()));
		defence.addDiploma(diplomaWork);
		
		assertEquals(diplomaWork, defence.getDiplomaWorks().get(0));
	}
	
	@Test
	public void defenceSetDay(){
		Defence defence = new Defence();
		Date day = new Date(2012,10,10);
		defence.setDay(day);
		
		assertEquals(day, defence.getDay());
	}
	
	@Test
	public void defenceSetFirstPerson(){
		Defence defence = new Defence();
		
		defence.setFirstPerson(new Person("Leader", dateList()));
		
		assertEquals("Leader", defence.getFirstPerson().getName());
		assertEquals(dateList(),defence.getFirstPerson().getAvailableDates());
	}		

	@Test
	public void defenceSetSecondPerson(){
		Defence defence = new Defence();
		
		defence.setSecondPerson(new Person("Leader", dateList()));
		
		assertEquals("Leader", defence.getSecondPerson().getName());
		assertEquals(dateList(),defence.getSecondPerson().getAvailableDates());
	}	

	@Test
	public void defenceSetThirdPerson(){
		Defence defence = new Defence();
		
		defence.setThirdPerson(new Person("Leader", dateList()));
		
		assertEquals("Leader", defence.getThirdPerson().getName());
		assertEquals(dateList(),defence.getThirdPerson().getAvailableDates());
	}	

	@Test
	public void defenceSetFourthPerson(){
		Defence defence = new Defence();
		
		defence.setFourthPerson(new Person("Leader", dateList()));
		
		assertEquals("Leader", defence.getFourthPerson().getName());
		assertEquals(dateList(),defence.getFourthPerson().getAvailableDates());
	}	

	public ArrayList<Date> dateList() {
		ArrayList<Date> set = new ArrayList<Date>();
		set.add(new Date(1, 2, 2012));
		set.add(new Date(1, 3, 2012));
		set.add(new Date(1, 4, 2012));

		return set;
	}

}