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
				"Leader", dateList()), new Person("Reviewer", dateList()),"Game");

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
		DiplomaWork work = new DiplomaWork();

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
		DiplomaWork work = new DiplomaWork("name", "diplomants", new Person(
				"Leader", dateList()), new Person("Reviewer", dateList()),
				"type");

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
						dateList()),"type");
		set.add(diplomaWork);
		set.add(diplomaWork);
		Defence defence = new Defence(set, new Date(2012, 10, 10), jury);

		assertEquals(set, defence.getDiplomaWorks());
		assertEquals(new Date(2012, 10, 10), defence.getDay());
		assertEquals(jury, defence.getJury());
	}

	@Test
	public void defenceSetDiplomaWorks() {
		Defence defence = new Defence();
		ArrayList<DiplomaWork> set = new ArrayList<DiplomaWork>();

		DiplomaWork diplomaWork = new DiplomaWork("name", "diplomants",
				new Person("Leader", dateList()), new Person("Reviewer",
						dateList()),"type");
		set.add(diplomaWork);
		set.add(diplomaWork);
		defence.setDiplomaWorks(set);

		assertEquals(set, defence.getDiplomaWorks());

	}

	@Test
	public void defenceAddDiplomaWork() {
		Defence defence = new Defence();
		DiplomaWork diplomaWork = new DiplomaWork("name", "diplomants",
				new Person("Leader", dateList()), new Person("Reviewer",
						dateList()),"type");
		defence.addDiploma(diplomaWork);

		assertEquals(diplomaWork, defence.getDiplomaWorks().get(0));
	}

	@Test
	public void defenceRemoveDiplomaWork() {
		Defence defence = new Defence();

		DiplomaWork diplomaWork = new DiplomaWork("name", "diplomants",
				new Person("Leader", dateList()), new Person("Reviewer",
						dateList()),"type");
		defence.addDiploma(diplomaWork);
		defence.addDiploma(diplomaWork);

		defence.removeDiploma();

		assertEquals(1, defence.getDiplomaWorks().size());
	}

	@Test
	public void defenceSetDay() {
		Defence defence = new Defence();
		Date day = new Date(2012, 10, 10);
		defence.setDay(day);

		assertEquals(day, defence.getDay());
	}

	@Test
	public void defenceSetJury() {
		Defence defence = new Defence();
		ArrayList<Person> jury = new ArrayList<Person>();
		jury = jury();

		defence.setJury(jury);

		assertEquals(jury, defence.getJury());
	}

	@Test
	public void FMStartDate() {
		FunctionalityManager FM = new FunctionalityManager();

		Date date = new Date(2012, 10, 10);

		FM.setStartDate(date);

		assertEquals(date, FM.getStartDate());
	}

	@Test
	public void FMEndDate() {
		FunctionalityManager FM = new FunctionalityManager();

		Date date = new Date(2012, 10, 10);

		FM.setEndDate(date);

		assertEquals(date, FM.getEndDate());

	}

	@Test
	public void FMLeaders() {
		FunctionalityManager FM = new FunctionalityManager();

		ArrayList<Person> leaders = new ArrayList<Person>();
		leaders = people();

		FM.setLeaders(leaders);

		assertEquals(leaders, FM.getLeaders());
	}

	@Test
	public void FMReviewers() {
		FunctionalityManager FM = new FunctionalityManager();
		ArrayList<Person> reviewers = new ArrayList<Person>();
		reviewers = people();

		FM.setReviewers(reviewers);

		assertEquals(reviewers, FM.getReviewers());
	}

	@Test
	public void FMSoftwareWorks() {
		FunctionalityManager FM = new FunctionalityManager();
		ArrayList<DiplomaWork> softArray = new ArrayList<DiplomaWork>();
		softArray = softwareWorks();

		FM.setSoftwareWorks(softArray);

		assertEquals(softArray, FM.getSoftwareWorks());

	}

	@Test
	public void FMHardwareWorks() {
		FunctionalityManager FM = new FunctionalityManager();

		ArrayList<DiplomaWork> hardWorks = new ArrayList<DiplomaWork>();
		hardWorks = works();

		FM.setHardwareWorks(hardWorks);

		assertEquals(hardWorks, FM.getHardwareWorks());

	}

	@Test
	public void FMNewtWorks() {
		FunctionalityManager FM = new FunctionalityManager();

		ArrayList<DiplomaWork> netWorks = new ArrayList<DiplomaWork>();
		netWorks = works();

		FM.setNetWorks(netWorks);

		assertEquals(netWorks, FM.getNetWorks());

	}
	
	@Test
	public void setHardDefencesTest(){
		FunctionalityManager fm = new FunctionalityManager();
		
		ArrayList<Defence> defences = new ArrayList<Defence>();
		
		defences.add(new Defence());
		defences.add(new Defence());
		defences.add(new Defence());
		
		fm.setHardDefences(defences);
		fm.setNetDefences(defences);
		fm.setSoftDefences(defences);
		
		assertEquals(defences,fm.getHardDefences());
		assertEquals(defences,fm.getNetDefences());
		assertEquals(defences,fm.getSoftDefences());
	}
	
	@Test
	public void setSoftDefencesTest(){
		FunctionalityManager fm = new FunctionalityManager();
	}
	
	@Test
	public void setNetDefencesTest(){
		FunctionalityManager fm = new FunctionalityManager();
		
	}

	@Test
	public void FMClearAll() {
		FunctionalityManager FM = new FunctionalityManager();

		ArrayList<Person> leaders = new ArrayList<Person>();
		leaders = people();
		FM.setLeaders(leaders);

		ArrayList<Person> reviewers = new ArrayList<Person>();
		reviewers = people();
		FM.setReviewers(reviewers);

		ArrayList<DiplomaWork> hardWorks = new ArrayList<DiplomaWork>();
		hardWorks = works();
		FM.setHardwareWorks(hardWorks);

		ArrayList<DiplomaWork> netWorks = new ArrayList<DiplomaWork>();
		netWorks = works();
		FM.setNetWorks(netWorks);

		ArrayList<DiplomaWork> softArray = new ArrayList<DiplomaWork>();
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
	public void lessThan9Generation() {
		FunctionalityManager FM = new FunctionalityManager();
		HashSet<Date> dateSet = new HashSet<Date>();
		ArrayList<DiplomaWork> works = new ArrayList<DiplomaWork>();
		Defence defence = new Defence();

		works.add(new DiplomaWork("name", "diplomants", new Person("Leader1",
				dateList()), new Person("Reviewer1", dateList()),"type"));
		works.add(new DiplomaWork("name", "diplomants", new Person("Leader2",
				dateList()), new Person("Reviewer2", dateList()),"type"));
		works.add(new DiplomaWork("name", "diplomants", new Person("Leader3",
				dateList()), new Person("Reviewer3", dateList()),"type"));
		works.add(new DiplomaWork("name", "diplomants", new Person("Leader1",
				dateList()), new Person("Reviewer1", dateList()),"type"));
		works.add(new DiplomaWork("name", "diplomants", new Person("Leader1",
				dateList()), new Person("Reviewer1", dateList()),"type"));
		works.add(new DiplomaWork("name", "diplomants", new Person("Leader1",
				dateList()), new Person("Reviewer3", dateList()),"type"));
		works.add(new DiplomaWork("name", "diplomants", new Person("Leader2",
				dateList()), new Person("Reviewer1", dateList()),"type"));

		FM.setHardwareWorks(works);

		defence = FM.defaultGeneration(FM.getHardwareWorks(), dateSet);

		assertEquals(7, defence.getDiplomaWorks().size());
	}

	@Test
	public void moreThan9Generation1() {
		FunctionalityManager FM = new FunctionalityManager();
		HashSet<Date> dateSet = new HashSet<Date>();
		ArrayList<DiplomaWork> works = new ArrayList<DiplomaWork>();
		Defence defence = new Defence();

		Person leader1 = new Person("Leader1", dateList());
		Person leader2 = new Person("Leader2", dateList());
		Person leader3 = new Person("Leader3", dateList());
		Person reviewer1 = new Person("Reviewer1", dateList());
		Person reviewer2 = new Person("Reviewer2", dateList());
		Person reviewer3 = new Person("Reviewer3", dateList());

		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer2,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader3, reviewer2,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer3,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader3, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3,"type"));

		FM.setHardwareWorks(works);

		defence = FM.defaultGeneration(FM.getHardwareWorks(), dateSet);

		assertEquals(8, defence.getDiplomaWorks().size());
	}

	@Test
	public void moreThan9Generation2() {
		FunctionalityManager FM = new FunctionalityManager();
		HashSet<Date> dateSet = new HashSet<Date>();
		ArrayList<DiplomaWork> works = new ArrayList<DiplomaWork>();
		Defence defence = new Defence();

		Person leader1 = new Person("Leader1", dateList());
		Person leader2 = new Person("Leader2", dateList());
		Person reviewer1 = new Person("Reviewer1", dateList());
		Person reviewer2 = new Person("Reviewer2", dateList());
		Person reviewer3 = new Person("Reviewer3", dateList());

		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer2,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer2,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer3,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3,"type"));

		FM.setHardwareWorks(works);

		defence = FM.defaultGeneration(FM.getHardwareWorks(), dateSet);

		assertEquals(8, defence.getDiplomaWorks().size());
		assertEquals(2, FM.getHardwareWorks().size());
	}

	@Test
	public void moreThan9Generation4() {
		FunctionalityManager FM = new FunctionalityManager();
		HashSet<Date> dateSet = new HashSet<Date>();
		ArrayList<DiplomaWork> works = new ArrayList<DiplomaWork>();
		Defence defence = new Defence();

		Person leader1 = new Person("Leader1", dateList());
		Person leader2 = new Person("Leader2", dateList());
		Person leader3 = new Person("Leader3", dateList());
		Person leader4 = new Person("Leader4", dateList());
		Person reviewer1 = new Person("Reviewer1", dateList());
		Person reviewer2 = new Person("Reviewer2", dateList());
		Person reviewer3 = new Person("Reviewer3", dateList());

		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer2,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer2,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer3,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader3, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader3, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader4, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader4, reviewer3,"type"));

		FM.setHardwareWorks(works);

		defence = FM.defaultGeneration(FM.getHardwareWorks(), dateSet);

		assertEquals(8, defence.getDiplomaWorks().size());
		assertEquals(2, FM.getHardwareWorks().size());
	}

	@Test
	public void moreThan9Generation5() {
		FunctionalityManager FM = new FunctionalityManager();
		HashSet<Date> dateSet = new HashSet<Date>();
		ArrayList<DiplomaWork> works = new ArrayList<DiplomaWork>();
		Defence defence = new Defence();

		Person leader1 = new Person("Leader1", dateList());
		Person leader2 = new Person("Leader2", dateList());
		Person leader3 = new Person("Leader3", dateList());
		Person leader4 = new Person("Leader4", dateList());
		Person reviewer1 = new Person("Reviewer1", dateList());
		Person reviewer2 = new Person("Reviewer2", dateList());
		Person reviewer3 = new Person("Reviewer3", dateList());

		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer2,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer2,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader3, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader4, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader4, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader4, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader4, reviewer3,"type"));

		FM.setHardwareWorks(works);

		defence = FM.defaultGeneration(FM.getHardwareWorks(), dateSet);

		assertEquals(8, defence.getDiplomaWorks().size());
		assertEquals(2, FM.getHardwareWorks().size());
	}

	@Test
	public void moreThan9Generation6() {
		FunctionalityManager FM = new FunctionalityManager();
		HashSet<Date> dateSet = new HashSet<Date>();
		ArrayList<DiplomaWork> works = new ArrayList<DiplomaWork>();
		Defence defence = new Defence();

		Person leader1 = new Person("Leader1", dateList());
		Person leader2 = new Person("Leader2", dateList());
		Person leader3 = new Person("Leader3", dateList());
		Person leader4 = new Person("Leader4", dateList());
		Person leader5 = new Person("Leader5", dateList());
		Person reviewer1 = new Person("Reviewer1", dateList());
		Person reviewer2 = new Person("Reviewer2", dateList());
		Person reviewer3 = new Person("Reviewer3", dateList());

		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer2,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer2,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader3, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader4, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer1,"type"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer3,"type"));

		FM.setHardwareWorks(works);

		defence = FM.defaultGeneration(FM.getHardwareWorks(), dateSet);

		assertEquals(8, defence.getDiplomaWorks().size());
		assertEquals(2, FM.getHardwareWorks().size());
	}
	
	@Test
	public void softwareDefenceTypeGenerationTesting() {
		FunctionalityManager FM = new FunctionalityManager();
		
		HashSet<Date> dateSet = new HashSet<Date>();
		ArrayList<DiplomaWork> works = new ArrayList<DiplomaWork>();
		Defence defence = new Defence();

		Person leader1 = new Person("Leader1", dateList());
		Person leader2 = new Person("Leader2", dateList());
		Person leader3 = new Person("Leader3", dateList());
		Person leader4 = new Person("Leader4", dateList());
		Person leader5 = new Person("Leader5", dateList());
		Person reviewer1 = new Person("Reviewer1", dateList());
		Person reviewer2 = new Person("Reviewer2", dateList());
		Person reviewer3 = new Person("Reviewer3", dateList());

		works.add(new DiplomaWork("name", "diplomants", leader1,reviewer1, "Game"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer2, "Game"));
		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer2, "Game"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3, "Game"));
		works.add(new DiplomaWork("name", "diplomants", leader3, reviewer1, "Game"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3, "Game"));
		works.add(new DiplomaWork("name", "diplomants", leader4, reviewer1, "Web Site/App"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer1, "Game"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer1, "Game"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer3, "Game"));
		
		FM.setSoftwareWorks(works);
		
		defence = FM.softwareDefenceTypeGeneration("Game", dateSet);
		
		assertEquals(8,defence.getDiplomaWorks().size());
		
	}
	
	@Test
	public void generateDefencesTesting1(){
		FunctionalityManager FM = new FunctionalityManager();
		
		Person leader1 = new Person("Leader1", dateList());
		Person leader2 = new Person("Leader2", dateList());
		Person leader3 = new Person("Leader3", dateList());
		Person leader4 = new Person("Leader4", dateList());
		Person leader5 = new Person("Leader5", dateList());
		Person reviewer1 = new Person("Reviewer1", dateList());
		Person reviewer2 = new Person("Reviewer2", dateList());
		Person reviewer3 = new Person("Reviewer3", dateList());
		
		ArrayList<DiplomaWork> works1 = new ArrayList<DiplomaWork>();
		works1.add(new DiplomaWork("name", "diplomants", leader1, reviewer1,"type"));
		works1.add(new DiplomaWork("name", "diplomants", leader2, reviewer2,"type"));
		works1.add(new DiplomaWork("name", "diplomants", leader1, reviewer2,"type"));
		works1.add(new DiplomaWork("name", "diplomants", leader2, reviewer3,"type"));
		works1.add(new DiplomaWork("name", "diplomants", leader3, reviewer1,"type"));
		works1.add(new DiplomaWork("name", "diplomants", leader2, reviewer3,"type"));
		works1.add(new DiplomaWork("name", "diplomants", leader4, reviewer1,"type"));
		works1.add(new DiplomaWork("name", "diplomants", leader5, reviewer1,"type"));
		works1.add(new DiplomaWork("name", "diplomants", leader5, reviewer1,"type"));
		works1.add(new DiplomaWork("name", "diplomants", leader5, reviewer3,"type"));
		
		FM.setHardwareWorks(works1);
		FM.generateDefences();
		
		assertEquals(2,FM.getHardDefences().size());
		
	}
	@Test
	public void generateDefencesTesting2(){
		FunctionalityManager FM = new FunctionalityManager();
		
		Person leader1 = new Person("Leader1", dateList());
		Person leader2 = new Person("Leader2", dateList());
		Person leader3 = new Person("Leader3", dateList());
		Person leader4 = new Person("Leader4", dateList());
		Person leader5 = new Person("Leader5", dateList());
		Person reviewer1 = new Person("Reviewer1", dateList());
		Person reviewer2 = new Person("Reviewer2", dateList());
		Person reviewer3 = new Person("Reviewer3", dateList());
		
		ArrayList<DiplomaWork> works1 = new ArrayList<DiplomaWork>();
		works1.add(new DiplomaWork("name", "diplomants", leader1, reviewer1,"type"));
		works1.add(new DiplomaWork("name", "diplomants", leader2, reviewer2,"type"));
		works1.add(new DiplomaWork("name", "diplomants", leader1, reviewer2,"type"));
		works1.add(new DiplomaWork("name", "diplomants", leader2, reviewer3,"type"));
		works1.add(new DiplomaWork("name", "diplomants", leader3, reviewer1,"type"));
		works1.add(new DiplomaWork("name", "diplomants", leader2, reviewer3,"type"));
		works1.add(new DiplomaWork("name", "diplomants", leader4, reviewer1,"type"));
		works1.add(new DiplomaWork("name", "diplomants", leader5, reviewer1,"type"));
		works1.add(new DiplomaWork("name", "diplomants", leader5, reviewer1,"type"));
		works1.add(new DiplomaWork("name", "diplomants", leader5, reviewer3,"type"));
		
		FM.setNetWorks(works1);
		FM.generateDefences();
		
		assertEquals(2,FM.getNetDefences().size());
		
	}
	
	@Test
	public void generateDefencesTesting3(){
		FunctionalityManager FM = new FunctionalityManager();
		
		Person leader1 = new Person("Leader1", dateList());
		Person leader2 = new Person("Leader2", dateList());
		Person leader3 = new Person("Leader3", dateList());
		Person leader4 = new Person("Leader4", dateList());
		Person leader5 = new Person("Leader5", dateList());
		Person reviewer1 = new Person("Reviewer1", dateList());
		Person reviewer2 = new Person("Reviewer2", dateList());
		Person reviewer3 = new Person("Reviewer3", dateList());

		ArrayList<DiplomaWork> works = new ArrayList<DiplomaWork>();
		works.add(new DiplomaWork("name", "diplomants", leader1,reviewer1, "Plug-in/Driver"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer2, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer2, "Plug-in/Driver"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3, "Other"));
		works.add(new DiplomaWork("name", "diplomants", leader3, reviewer1, "Plug-in/Driver"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader4, reviewer1, "Web Site/App"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer1, "Plug-in/Driver"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer1, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer3, "Plug-in/Driver"));
		works.add(new DiplomaWork("name", "diplomants", leader1,reviewer1, "Other"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer2, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer2, "Plug-in/Driver"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader3, reviewer1, "Other"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader4, reviewer1, "Web Site/App"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer1, "Other"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer1, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer3, "Plug-in/Driver"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer2, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer2, "Plug-in/Driver"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader3, reviewer1, "Other"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader4, reviewer1, "Web Site/App"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer1, "Other"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer1, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer3, "Plug-in/Driver"));
		works.add(new DiplomaWork("name", "diplomants", leader1,reviewer1, "Plug-in/Driver"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer2, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer2, "Plug-in/Driver"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3, "Other"));
		works.add(new DiplomaWork("name", "diplomants", leader3, reviewer1, "Plug-in/Driver"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader4, reviewer1, "Web Site/App"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer1, "Plug-in/Driver"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer1, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer3, "Plug-in/Driver"));
		works.add(new DiplomaWork("name", "diplomants", leader1,reviewer1, "Other"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer2, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer2, "Plug-in/Driver"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader3, reviewer1, "Other"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader4, reviewer1, "Web Site/App"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer1, "Other"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer1, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer3, "Plug-in/Driver"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer2, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader1, reviewer2, "Plug-in/Driver"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader3, reviewer1, "Other"));
		works.add(new DiplomaWork("name", "diplomants", leader2, reviewer3, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader4, reviewer1, "Web Site/App"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer1, "Other"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer1, "Game/Media"));
		works.add(new DiplomaWork("name", "diplomants", leader5, reviewer3, "Plug-in/Driver"));
		
		FM.setSoftwareWorks(works);
		FM.generateDefences();
		
		assertEquals(7,FM.getSoftDefences().size());
		
	}
	
	@Test
	public void addPersonToJuryTest1(){
		FunctionalityManager fm = new FunctionalityManager();
		Person leader = new Person("Leader", dateList());
		Person reviewer = new Person("Reviewer", dateList());
		DiplomaWork work = new DiplomaWork("name", "diplomants", leader, reviewer,"type");
		ArrayList<Person> jury = new ArrayList<Person>();
		
		fm.addPersonToJury(jury, work);
		
		assertEquals(2,jury.size());
	}
	
	@Test
	public void addPersonToJuryTest2(){
		FunctionalityManager fm = new FunctionalityManager();
		Person leader = new Person("Leader", dateList());
		Person reviewer = new Person("Reviewer", dateList());
		DiplomaWork work = new DiplomaWork("name", "diplomants", leader, reviewer,"type");
		ArrayList<Person> jury = new ArrayList<Person>();
		
		jury.add(leader);
		fm.addPersonToJury(jury, work);
		
		assertEquals(2,jury.size());
	}
	
	@Test
	public void getPersonTest1(){
		FunctionalityManager fm = new FunctionalityManager();
		fm.getPerson(dateList(), "Kolio", "Diploma Leader");
		
		assertEquals(1,fm.getLeaders().size());
	}
	
	@Test
	public void getPersonTest2(){
		FunctionalityManager fm = new FunctionalityManager();
		fm.getPerson(dateList(), "Kolio", "Reviewer");
		
		assertEquals(1,fm.getReviewers().size());
	}
	
	@Test
	public void getPersonTest3(){
		FunctionalityManager fm = new FunctionalityManager();
		fm.getPerson(dateList(), "Kolio", "Both");
		
		assertEquals(1,fm.getLeaders().size());
		assertEquals(1,fm.getReviewers().size());
	}
	
	@Test
	public void getDiplomaTest1(){
		FunctionalityManager fm = new FunctionalityManager();
		
		fm.getPerson(dateList(), "Kolio", "Both");
		fm.getDiploma("projectName", "diplomants", "Kolio", "Kolio", "Software", "Game/Media");
		
		assertEquals(1,fm.getSoftwareWorks().size());
	}

	@Test
	public void getDiplomaTest2(){
		FunctionalityManager fm = new FunctionalityManager();
		
		fm.getPerson(dateList(), "Kolio", "Both");
		fm.getDiploma("projectName", "diplomants", "Kolio", "Kolio", "Communications", "type");
	
		assertEquals(1,fm.getNetWorks().size());
	}
	
	@Test
	public void getDiplomaTest3(){
		FunctionalityManager fm = new FunctionalityManager();
		
		fm.getPerson(dateList(), "Kolio", "Both");
		fm.getDiploma("projectName", "diplomants", "Kolio", "Kolio", "Hardware", "type");
		
		assertEquals(1,fm.getHardwareWorks().size());
	}
	
	
	public ArrayList<Date> dateList() {
		ArrayList<Date> set = new ArrayList<Date>();
		set.add(new Date(1, 2, 2012));
		set.add(new Date(1, 3, 2012));
		set.add(new Date(1, 4, 2012));

		return set;
	}

	public ArrayList<Person> people() {
		ArrayList<Person> people = new ArrayList<Person>();

		people.add(new Person("Leader", dateList()));
		people.add(new Person("Reviewer", dateList()));

		return people;
	}

	public ArrayList<Person> jury() {
		ArrayList<Person> people = new ArrayList<Person>();

		people.add(new Person("Leader", dateList()));
		people.add(new Person("Reviewer", dateList()));
		people.add(new Person("Leader", dateList()));
		people.add(new Person("Reviewer", dateList()));

		return people;
	}

	public ArrayList<DiplomaWork> works() {
		ArrayList<DiplomaWork> works = new ArrayList<DiplomaWork>();

		works.add(new DiplomaWork("name", "diplomants", new Person("Leader",
				dateList()), new Person("Reviewer", dateList()),"type"));
		works.add(new DiplomaWork("name", "diplomants", new Person("Leader",
				dateList()), new Person("Reviewer", dateList()),"type"));
		works.add(new DiplomaWork("name", "diplomants", new Person("Leader",
				dateList()), new Person("Reviewer", dateList()),"type"));

		return works;
	}

	public ArrayList<DiplomaWork> softwareWorks() {
		ArrayList<DiplomaWork> softArray = new ArrayList<DiplomaWork>();

		softArray.add(new DiplomaWork("name", "diplomants", new Person(
				"Leader", dateList()), new Person("Reviewer", dateList()),
				"type"));
		softArray.add(new DiplomaWork("name", "diplomants", new Person(
				"Leader", dateList()), new Person("Reviewer", dateList()),
				"type"));

		return softArray;
	}

}