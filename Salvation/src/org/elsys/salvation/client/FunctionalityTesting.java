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
		ArrayList<Person> jury = new ArrayList<Person>();
		jury = jury();

		DiplomaWork diplomaWork = new DiplomaWork("name", "diplomants",
				new Person("Leader", dateList()), new Person("Reviewer",
						dateList()));
		SoftwareWork softwareWork = new SoftwareWork("name", "diplomants",
				new Person("Leader", dateList()), new Person("Reviewer",
						dateList()), "type");
		set.add(diplomaWork);
		set.add(softwareWork);
		Defence defence = new Defence(set,new Date(2012, 10, 10),jury);

		assertEquals(set, defence.getDiplomaWorks());
		assertEquals(new Date(2012, 10, 10), defence.getDay());
		assertEquals(jury,defence.getJury());
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
	public void defenceRemoveDiplomaWork(){
		Defence defence = new Defence();
		
		DiplomaWork diplomaWork = new DiplomaWork("name", "diplomants",
				new Person("Leader", dateList()), new Person("Reviewer",
						dateList()));
		defence.addDiploma(diplomaWork);
		defence.addDiploma(diplomaWork);
		
		defence.removeDiploma();
		
		assertEquals(1,defence.getDiplomaWorks().size());
	}
	
	@Test
	public void defenceSetDay(){
		Defence defence = new Defence();
		Date day = new Date(2012,10,10);
		defence.setDay(day);
		
		assertEquals(day, defence.getDay());
	}
	
	@Test
	public void defenceSetJury(){
		Defence defence = new Defence();
		ArrayList<Person> jury = new ArrayList<Person>();
		jury = jury();
		
		defence.setJury(jury);
		
		assertEquals(jury, defence.getJury());
	}		
	
	@Test
	public void FMStartDate(){
		FunctionalityManager FM = new FunctionalityManager();
		
		Date date = new Date(2012,10,10);
		
		FM.setStartDate(date);
		
		assertEquals(date,FM.getStartDate());
	}
	
	@Test
	public void FMEndDate(){
		FunctionalityManager FM = new FunctionalityManager();

		Date date = new Date(2012,10,10);
		
		FM.setEndDate(date);
		
		assertEquals(date,FM.getEndDate());
		
	}
	
	@Test
	public void FMLeaders(){
		FunctionalityManager FM = new FunctionalityManager();
		
		HashSet<Person> leaders = new HashSet<Person>();
		leaders=people();
		
		FM.setLeaders(leaders);
		
		assertEquals(leaders, FM.getLeaders());
	}
	
	@Test
	public void FMReviewers(){
		FunctionalityManager FM = new FunctionalityManager();
		HashSet<Person> reviewers = new HashSet<Person>();
		reviewers=people();
		
		FM.setReviewers(reviewers);
		
		assertEquals(reviewers, FM.getReviewers());
	}
	
	@Test
	public void FMSoftwareWorks(){
		FunctionalityManager FM = new FunctionalityManager();
		ArrayList<SoftwareWork> softArray = new ArrayList<SoftwareWork>();
		softArray = softwareWorks();
		
		FM.setSoftwareWorks(softArray);
		
		assertEquals(softArray,FM.getSoftwareWorks());
		
	}
	
	@Test
	public void FMHardwareWorks(){
		FunctionalityManager FM = new FunctionalityManager();
		
		ArrayList<DiplomaWork> hardWorks = new ArrayList<DiplomaWork>();
		hardWorks=works();
		
		FM.setHardwareWorks(hardWorks);
		
		assertEquals(hardWorks, FM.getHardwareWorks());
		
	}
	
	@Test
	public void FMNewtWorks(){
		FunctionalityManager FM = new FunctionalityManager();
		
		ArrayList<DiplomaWork> netWorks = new ArrayList<DiplomaWork>();
		netWorks=works();
		
		FM.setNetWorks(netWorks);
		
		assertEquals(netWorks, FM.getNetWorks());
		
	}
	
	@Test
	public void FMClearAll(){
		FunctionalityManager FM = new FunctionalityManager();
		
		HashSet<Person> leaders = new HashSet<Person>();
		leaders=people();
		FM.setLeaders(leaders);
		
		HashSet<Person> reviewers = new HashSet<Person>();
		reviewers=people();
		FM.setReviewers(reviewers);
		
		ArrayList<DiplomaWork> hardWorks = new ArrayList<DiplomaWork>();
		hardWorks=works();	
		FM.setHardwareWorks(hardWorks);
		
		ArrayList<DiplomaWork> netWorks = new ArrayList<DiplomaWork>();
		netWorks=works();		
		FM.setNetWorks(netWorks);
		
		ArrayList<SoftwareWork> softArray = new ArrayList<SoftwareWork>();
		softArray = softwareWorks();		
		FM.setSoftwareWorks(softArray);
		
		FM.clearAllData();
		
		assertEquals(0, FM.getLeaders().size());
		assertEquals(0, FM.getReviewers().size());
		assertEquals(0, FM.getSoftwareWorks().size());
		assertEquals(0, FM.getHardwareWorks().size());
		assertEquals(0, FM.getNetWorks().size());	
	}
	
	@Test
	public void lessThan9Generation(){
		FunctionalityManager FM = new FunctionalityManager();
		HashSet<Date> dateSet = new HashSet<Date>();
		ArrayList<DiplomaWork> works = new ArrayList<DiplomaWork>();
		Defence defence = new Defence();
		
		works.add(new DiplomaWork("name", "diplomants",
				new Person("Leader1", dateList()), new Person("Reviewer1",dateList())));
		works.add(new DiplomaWork("name", "diplomants",
				new Person("Leader2", dateList()), new Person("Reviewer2",dateList())));
		works.add(new DiplomaWork("name", "diplomants",
				new Person("Leader3", dateList()), new Person("Reviewer3",dateList())));
		works.add(new DiplomaWork("name", "diplomants",
				new Person("Leader1", dateList()), new Person("Reviewer1",dateList())));
		works.add(new DiplomaWork("name", "diplomants",
				new Person("Leader1", dateList()), new Person("Reviewer1",dateList())));
		works.add(new DiplomaWork("name", "diplomants",
				new Person("Leader1", dateList()), new Person("Reviewer3",dateList())));
		works.add(new DiplomaWork("name", "diplomants",
				new Person("Leader2", dateList()), new Person("Reviewer1",dateList())));
		
		FM.setHardwareWorks(works);
		
		defence = FM.defaultGeneration(FM.getHardwareWorks(), dateSet);
		
		assertEquals(7,defence.getDiplomaWorks().size());	
	}
	
	@Test
	public void moreThan9Generation1(){
		FunctionalityManager FM = new FunctionalityManager();
		HashSet<Date> dateSet = new HashSet<Date>();
		ArrayList<DiplomaWork> works = new ArrayList<DiplomaWork>();
		Defence defence = new Defence();
		
		Person leader1 = new Person("Leader1", dateList());
		Person leader2 = new Person("Leader2", dateList());
		Person leader3 = new Person("Leader3", dateList());
		Person reviewer1 = new Person("Reviewer1",dateList());
		Person reviewer2 = new Person("Reviewer2",dateList());
		Person reviewer3 = new Person("Reviewer3",dateList());
		
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader2,reviewer2));
		works.add(new DiplomaWork("name", "diplomants",
				leader3,reviewer2));
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer3));
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader2,reviewer3));
		works.add(new DiplomaWork("name", "diplomants",
				leader2,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader3,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader2,reviewer3));
		
		FM.setHardwareWorks(works);
		
		defence = FM.defaultGeneration(FM.getHardwareWorks(), dateSet);
		
		assertEquals(8,defence.getDiplomaWorks().size());	
	}
	
	@Test
	public void moreThan9Generation2(){
		FunctionalityManager FM = new FunctionalityManager();
		HashSet<Date> dateSet = new HashSet<Date>();
		ArrayList<DiplomaWork> works = new ArrayList<DiplomaWork>();
		Defence defence = new Defence();
		
		Person leader1 = new Person("Leader1", dateList());
		Person leader2 = new Person("Leader2", dateList());
		Person reviewer1 = new Person("Reviewer1",dateList());
		Person reviewer2 = new Person("Reviewer2",dateList());
		Person reviewer3 = new Person("Reviewer3",dateList());
		
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader2,reviewer2));
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer2));
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer3));
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader2,reviewer3));
		works.add(new DiplomaWork("name", "diplomants",
				leader2,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader2,reviewer3));
		
		FM.setHardwareWorks(works);
		
		defence = FM.defaultGeneration(FM.getHardwareWorks(), dateSet);
		
		assertEquals(8,defence.getDiplomaWorks().size());
		assertEquals(2,FM.getHardwareWorks().size());
	}
	
	@Test
	public void moreThan9Generation3(){
		FunctionalityManager FM = new FunctionalityManager();
		HashSet<Date> dateSet = new HashSet<Date>();
		ArrayList<DiplomaWork> works = new ArrayList<DiplomaWork>();
		Defence defence = new Defence();
		
		Person leader1 = new Person("Leader1", dateList());
		Person leader2 = new Person("Leader2", dateList());
		Person leader3 = new Person("Leader3", dateList());
		Person reviewer1 = new Person("Reviewer1",dateList());
		Person reviewer2 = new Person("Reviewer2",dateList());
		Person reviewer3 = new Person("Reviewer3",dateList());
		
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader2,reviewer2));
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer2));
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer3));
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader2,reviewer3));
		works.add(new DiplomaWork("name", "diplomants",
				leader3,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader3,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader3,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader3,reviewer3));
		
		FM.setHardwareWorks(works);
		
		defence = FM.defaultGeneration(FM.getHardwareWorks(), dateSet);
		
		assertEquals(8,defence.getDiplomaWorks().size());
		assertEquals(2,FM.getHardwareWorks().size());
	}
	
	@Test
	public void moreThan9Generation4(){
		FunctionalityManager FM = new FunctionalityManager();
		HashSet<Date> dateSet = new HashSet<Date>();
		ArrayList<DiplomaWork> works = new ArrayList<DiplomaWork>();
		Defence defence = new Defence();
		
		Person leader1 = new Person("Leader1", dateList());
		Person leader2 = new Person("Leader2", dateList());
		Person leader3 = new Person("Leader3", dateList());
		Person leader4 = new Person("Leader4", dateList());
		Person reviewer1 = new Person("Reviewer1",dateList());
		Person reviewer2 = new Person("Reviewer2",dateList());
		Person reviewer3 = new Person("Reviewer3",dateList());
		
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader2,reviewer2));
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer2));
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer3));
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader2,reviewer3));
		works.add(new DiplomaWork("name", "diplomants",
				leader3,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader3,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader4,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader4,reviewer3));
		
		FM.setHardwareWorks(works);
		
		defence = FM.defaultGeneration(FM.getHardwareWorks(), dateSet);
		
		assertEquals(8,defence.getDiplomaWorks().size());
		assertEquals(2,FM.getHardwareWorks().size());
	}
	
	@Test
	public void moreThan9Generation5(){
		FunctionalityManager FM = new FunctionalityManager();
		HashSet<Date> dateSet = new HashSet<Date>();
		ArrayList<DiplomaWork> works = new ArrayList<DiplomaWork>();
		Defence defence = new Defence();
		
		Person leader1 = new Person("Leader1", dateList());
		Person leader2 = new Person("Leader2", dateList());
		Person leader3 = new Person("Leader3", dateList());
		Person leader4 = new Person("Leader4", dateList());
		Person reviewer1 = new Person("Reviewer1",dateList());
		Person reviewer2 = new Person("Reviewer2",dateList());
		Person reviewer3 = new Person("Reviewer3",dateList());
		
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader2,reviewer2));
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer2));
		works.add(new DiplomaWork("name", "diplomants",
				leader2,reviewer3));
		works.add(new DiplomaWork("name", "diplomants",
				leader3,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader2,reviewer3));
		works.add(new DiplomaWork("name", "diplomants",
				leader4,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader4,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader4,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader4,reviewer3));
		
		FM.setHardwareWorks(works);
		
		defence = FM.defaultGeneration(FM.getHardwareWorks(), dateSet);
		
		assertEquals(8,defence.getDiplomaWorks().size());
		assertEquals(2,FM.getHardwareWorks().size());
	}
	
	@Test
	public void moreThan9Generation6(){
		FunctionalityManager FM = new FunctionalityManager();
		HashSet<Date> dateSet = new HashSet<Date>();
		ArrayList<DiplomaWork> works = new ArrayList<DiplomaWork>();
		Defence defence = new Defence();
		
		Person leader1 = new Person("Leader1", dateList());
		Person leader2 = new Person("Leader2", dateList());
		Person leader3 = new Person("Leader3", dateList());
		Person leader4 = new Person("Leader4", dateList());
		Person leader5 = new Person("Leader5", dateList());
		Person reviewer1 = new Person("Reviewer1",dateList());
		Person reviewer2 = new Person("Reviewer2",dateList());
		Person reviewer3 = new Person("Reviewer3",dateList());
		
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader2,reviewer2));
		works.add(new DiplomaWork("name", "diplomants",
				leader1,reviewer2));
		works.add(new DiplomaWork("name", "diplomants",
				leader2,reviewer3));
		works.add(new DiplomaWork("name", "diplomants",
				leader3,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader2,reviewer3));
		works.add(new DiplomaWork("name", "diplomants",
				leader4,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader5,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader5,reviewer1));
		works.add(new DiplomaWork("name", "diplomants",
				leader5,reviewer3));
		
		FM.setHardwareWorks(works);
		
		defence = FM.defaultGeneration(FM.getHardwareWorks(), dateSet);
		
		assertEquals(8,defence.getDiplomaWorks().size());
		assertEquals(2,FM.getHardwareWorks().size());
	}

	public ArrayList<Date> dateList() {
		ArrayList<Date> set = new ArrayList<Date>();
		set.add(new Date(1, 2, 2012));
		set.add(new Date(1, 3, 2012));
		set.add(new Date(1, 4, 2012));

		return set;
	}
	
	public HashSet<Person> people(){
		HashSet<Person> people = new HashSet<Person>();
		
		people.add(new Person("Leader", dateList()));
		people.add(new Person("Reviewer",dateList()));
		
		return people;
	}
	
	public ArrayList<Person> jury(){
		ArrayList<Person> people = new ArrayList<Person>();
		
		people.add(new Person("Leader", dateList()));
		people.add(new Person("Reviewer",dateList()));
		people.add(new Person("Leader", dateList()));
		people.add(new Person("Reviewer",dateList()));
		
		return people;
	}
	
	public ArrayList<DiplomaWork> works(){
		ArrayList<DiplomaWork> works = new ArrayList<DiplomaWork>();
		
		works.add(new DiplomaWork("name", "diplomants",
				new Person("Leader", dateList()), new Person("Reviewer",dateList())));
		works.add(new DiplomaWork("name", "diplomants",
				new Person("Leader", dateList()), new Person("Reviewer",dateList())));
		works.add(new DiplomaWork("name", "diplomants",
				new Person("Leader", dateList()), new Person("Reviewer",dateList())));
		
		return works;
	}
	
	public ArrayList<SoftwareWork> softwareWorks(){
		ArrayList<SoftwareWork> softArray = new ArrayList<SoftwareWork>();
		
		softArray.add(new SoftwareWork("name", "diplomants",
				new Person("Leader", dateList()), new Person("Reviewer",
						dateList()), "type"));
		softArray.add(new SoftwareWork("name", "diplomants",
				new Person("Leader", dateList()), new Person("Reviewer",
						dateList()), "type"));
		
		return softArray;
	}

}