package org.elsys.salvation.client;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;

import org.junit.Test;

public class FunctionalityTesting {

	@Test
	public void diplomaWorkGetersTest() {
		DiplomaWork work = new DiplomaWork("name", "diplomants", new Person("Leader", dateSet()), new Person("Reviewer", dateSet()));
		
		assertEquals("name", work.getName());
		assertEquals("diplomants", work.getDiplomants());
		assertEquals("Leader", work.getLeader().getName());
		assertEquals("Reviewer", work.getReviewer().getName());
	}
	
	@Test
	public void diplomaWorkSetersTest(){
		DiplomaWork work = new DiplomaWork();
		
		work.setName("name");
		work.setDiplomants("diplomants");
		work.setLeader(new Person("Leader", dateSet()));
		work.setReviewer(new Person("Reviewer", dateSet()));
		
		assertEquals("name", work.getName());
		assertEquals("diplomants", work.getDiplomants());
		assertEquals("Leader", work.getLeader().getName());
		assertEquals("Reviewer", work.getReviewer().getName());	
	}
	
	@Test
	public void personGetersTest(){
		Person person = new Person("name", dateSet());
		
		assertEquals("name", person.getName());
		assertEquals(dateSet(), person.getUnavailableDates());
	}
	
	@Test
	public void personSettersTest(){
		Person person = new Person();
		
		person.setName("name");
		person.setUnavailableDates(dateSet());
		
		assertEquals("name", person.getName());
		assertEquals(dateSet(), person.getUnavailableDates());
	}
	
	@Test
	public void softwareWorkFirstConstructorTest() {
		SoftwareWork work = new SoftwareWork();
		
		work.setName("name");
		work.setDiplomants("diplomants");
		work.setLeader(new Person("Leader", dateSet()));
		work.setReviewer(new Person("Reviewer", dateSet()));
		work.setType("type");
		
		assertEquals("name", work.getName());
		assertEquals("diplomants", work.getDiplomants());
		assertEquals("Leader", work.getLeader().getName());
		assertEquals("Reviewer", work.getReviewer().getName());
		assertEquals("type", work.getType());
	}
	
	@Test
	public void softwareWorkSecondConstructorTest(){
		SoftwareWork work = new SoftwareWork("name", "diplomants", new Person("Leader", dateSet()), new Person("Reviewer", dateSet()), "type");
		
		assertEquals("name", work.getName());
		assertEquals("diplomants", work.getDiplomants());
		assertEquals("Leader", work.getLeader().getName());
		assertEquals("Reviewer", work.getReviewer().getName());	
		assertEquals("type", work.getType());
	}
	
	@Test
	public void defenceTesting(){
		HashSet<DiplomaWork> set = new HashSet<DiplomaWork>();
		
		DiplomaWork diplomaWork = new DiplomaWork("name", "diplomants", new Person("Leader", dateSet()), new Person("Reviewer", dateSet()));
		SoftwareWork softwareWork = new SoftwareWork("name", "diplomants", new Person("Leader", dateSet()), new Person("Reviewer", dateSet()), "type");
		set.add(diplomaWork);
		set.add(softwareWork);
		Defence defence = new Defence(set);
		
		assertEquals(set, defence.getDiplomaWorks());
	}
	
	public HashSet<Date> dateSet(){
		HashSet<Date> set = new HashSet<Date>();
		set.add(new Date(1, 2, 2012));
		set.add(new Date(1, 3, 2012));
		set.add(new Date(1, 4, 2012));
		
		return set;
	}

}

