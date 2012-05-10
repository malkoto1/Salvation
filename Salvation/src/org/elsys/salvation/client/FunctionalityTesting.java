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
	public void defenceTesting() {
		HashSet<DiplomaWork> set = new HashSet<DiplomaWork>();

		DiplomaWork diplomaWork = new DiplomaWork("name", "diplomants",
				new Person("Leader", dateList()), new Person("Reviewer",
						dateList()));
		SoftwareWork softwareWork = new SoftwareWork("name", "diplomants",
				new Person("Leader", dateList()), new Person("Reviewer",
						dateList()), "type");
		set.add(diplomaWork);
		set.add(diplomaWork);
		Defence defence = new Defence();

		assertEquals(set, defence.getDiplomaWorks());
	}

	public ArrayList<Date> dateList() {
		ArrayList<Date> set = new ArrayList<Date>();
		set.add(new Date(1, 2, 2012));
		set.add(new Date(1, 3, 2012));
		set.add(new Date(1, 4, 2012));

		return set;
	}

}