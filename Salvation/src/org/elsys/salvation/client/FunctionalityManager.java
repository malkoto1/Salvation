package org.elsys.salvation.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import com.google.gwt.user.client.ui.ListBox;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class FunctionalityManager implements Serializable {

	private Date startDate;
	private Date endDate;
	private ArrayList<Person> leaders;
	private ArrayList<Person> reviewers;
	private ArrayList<DiplomaWork> softwareWorks;
	private ArrayList<DiplomaWork> hardwareWorks;
	private ArrayList<DiplomaWork> netWorks;
	private ArrayList<Defence> netDefences;
	private ArrayList<Defence> hardDefences;
	private ArrayList<Defence> softDefences;

	public FunctionalityManager() {
		super();
		startDate = new Date();
		endDate = new Date();
		leaders = new ArrayList<Person>();
		reviewers = new ArrayList<Person>();
		softwareWorks = new ArrayList<DiplomaWork>();
		hardwareWorks = new ArrayList<DiplomaWork>();
		netWorks = new ArrayList<DiplomaWork>();
		netDefences = new ArrayList<Defence>();
		hardDefences = new ArrayList<Defence>();
		softDefences = new ArrayList<Defence>();
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ArrayList<Person> getLeaders() {
		return leaders;
	}

	public void setLeaders(ArrayList<Person> leaders) {
		this.leaders = leaders;
	}

	public ArrayList<Person> getReviewers() {
		return reviewers;
	}

	public void setReviewers(ArrayList<Person> reviewers) {
		this.reviewers = reviewers;
	}

	public ArrayList<DiplomaWork> getSoftwareWorks() {
		return softwareWorks;
	}

	public void setSoftwareWorks(ArrayList<DiplomaWork> softwareWorks) {
		this.softwareWorks = softwareWorks;
	}

	public ArrayList<DiplomaWork> getHardwareWorks() {
		return hardwareWorks;
	}

	public void setHardwareWorks(ArrayList<DiplomaWork> hardwareWorks) {
		this.hardwareWorks = hardwareWorks;
	}

	public ArrayList<DiplomaWork> getNetWorks() {
		return netWorks;
	}

	public void setNetWorks(ArrayList<DiplomaWork> netWorks) {
		this.netWorks = netWorks;
	}

	public ArrayList<Defence> getNetDefences() {
		return netDefences;
	}

	public void setNetDefences(ArrayList<Defence> netDefences) {
		this.netDefences = netDefences;
	}

	public ArrayList<Defence> getHardDefences() {
		return hardDefences;
	}

	public void setHardDefences(ArrayList<Defence> hardDefences) {
		this.hardDefences = hardDefences;
	}

	public ArrayList<Defence> getSoftDefences() {
		return softDefences;
	}

	public void setSoftDefences(ArrayList<Defence> softDefences) {
		this.softDefences = softDefences;
	}

	public void getPerson(ArrayList<Date> set, String name, String type) {
		if (type.equals("Diploma Leader")) {
			Person person = new Person(name, set);
			leaders.add(person);
		} else if (type.equals("Reviewer")) {
			Person person = new Person(name, set);
			reviewers.add(person);
		} else if (type.equals("Both")) {
			Person person = new Person(name, set);
			leaders.add(person);
			reviewers.add(person);
		}
	}
	
	public boolean checkEmpty(TextItem box){
		if(box.getValueAsString()==null){
			return true;
		}else{
			return false;
		}
	}

	public void getDiploma(String projectName, String diplomants,
			String diplomaLeader, String diplomaReviewer,
			String specialtie, String type) {

		Person leader = new Person();
		Person reviewer = new Person();

		for (int i = 0; i < leaders.size(); i++) {
			if (leaders.get(i).getName().equals(diplomaLeader)) {
				leader = leaders.get(i);
			}
		}

		for (int k = 0; k < reviewers.size(); k++) {
			if (reviewers.get(k).getName().equals(diplomaReviewer)) {
				reviewer = reviewers.get(k);
			}
		}

		if (specialtie.equalsIgnoreCase("Software")
				&& !softwareWorks.contains(new DiplomaWork(projectName, diplomants,
						leader, reviewer, type))) {
			softwareWorks.add(new DiplomaWork(projectName,
					diplomants, leader, reviewer, type));
		} else if (specialtie.equalsIgnoreCase("Hardware")
				&& !hardwareWorks.contains(new DiplomaWork(projectName, diplomants,
						leader, reviewer, "Hardware"))) {
			hardwareWorks.add(new DiplomaWork(projectName,
					diplomants, leader, reviewer,"Hardware"));
		} else if (specialtie.equalsIgnoreCase("Communications")
				&& !netWorks.contains(new DiplomaWork(projectName, diplomants,
						leader, reviewer, "Communications"))) {
			netWorks.add(new DiplomaWork(projectName,
					diplomants, leader, reviewer,"Communication"));
		}
	}

	public void generateDefences() {
		HashSet<Date> takenDates = new HashSet<Date>();
		HashSet<Date> softTakenDates = new HashSet<Date>();

		resetDefences();

		while (hardwareWorks.size() > 0) {
			hardDefences.add(defaultGeneration(hardwareWorks, takenDates));
		}

		while (netWorks.size() > 0) {
			netDefences.add(defaultGeneration(netWorks, takenDates));
		}

		if(softwareWorks.size()>0){
			if(softwareWorksContains("Game/Media") == 1){
				softDefences.add(softwareDefenceTypeGeneration("Game/Media",softTakenDates));
			}
			if(softwareWorksContains("Web Site/App") == 1){
				softDefences.add(softwareDefenceTypeGeneration("Web Site/App",softTakenDates));
			}
			if(softwareWorksContains("Plug-in/Driver") == 1){
				softDefences.add(softwareDefenceTypeGeneration("Plug-in/Driver",softTakenDates));
			}
			if(softwareWorksContains("Other") == 1){
				softDefences.add(softwareDefenceTypeGeneration("Other", softTakenDates));
			}
			if(softwareWorks.size()>0){
				organizeLastSoftwareWorks(softDefences, softTakenDates);
			}
		}
	}

	private void resetDefences() {
		resetDefence(hardDefences, hardwareWorks);
		resetDefence(softDefences, softwareWorks);
		resetDefence(netDefences, netWorks);
	}

	private void resetDefence(ArrayList<Defence> defences, ArrayList<DiplomaWork> works) {
		if(defences.size() > 1){
			for(int i = 0; i < defences.size(); i++){
				for(int k = 0; k < defences.get(i).getDiplomaWorks().size(); k++){
					works.add(defences.get(i).getDiplomaWorks().get(k));
				}
			}
			defences.clear();
		}
	}

	public Defence defaultGeneration(ArrayList<DiplomaWork> works,
			HashSet<Date> takenDates) {
		Defence defence = new Defence();

		if(works.size()<=9){
			for (int i = 0; i < works.size(); i++) {
				defence.addDiploma(works.get(i));
			}
			works.clear();
		}else{
			for (int i = 0; i < 8; i++) {
				defence.addDiploma(works.get(i));
			}
			works.removeAll(defence.getDiplomaWorks());
		} 
		
		generateJury(defence,works);
		setDefenceDate(defence,takenDates);
		
		return defence;
	}

	private void setDefenceDate(Defence defence, HashSet<Date> takenDates) {
		Person busyMan = new Person();

		for (int i = 1; i < defence.getJury().size(); i++) {
			if (defence.getJury().get(i).getAvailableDates().size() > 0){
				busyMan = defence.getJury().get(i);
				break;
			}
		}
		
		for(int i = 0; i<defence.getJury().size(); i++){
			if(defence.getJury().get(i).getAvailableDates().size()<
					busyMan.getAvailableDates().size()){
				busyMan = defence.getJury().get(i);
			}
		}
				
		findDate(defence,busyMan,takenDates);
	}

	private void findDate(Defence defence, Person busyMan, HashSet<Date> takenDates) {
		int setDate = 0;
		
		for(int i = 0; i<defence.getJury().size(); i++){
			for(int k = 0; k<busyMan.getAvailableDates().size(); k++){
				for(int f = 0; f < defence.getJury().get(i).getAvailableDates().size(); f++){
					if(busyMan.getAvailableDates().get(k).equals(defence.getJury().get(i).getAvailableDates().get(f))
							&& !takenDates.contains(busyMan.getAvailableDates().get(k))){
						defence.setDay(busyMan.getAvailableDates().get(k));
						takenDates.add(busyMan.getAvailableDates().get(k));
						setDate=1;
						break;
					}
				}
				if(setDate==1){break;}
			}
			if(setDate==1){break;}
		}
		
		if(setDate==0){
			for(int i = 0; i<defence.getJury().size(); i++){
				for(int k = 0; k<defence.getJury().get(i).getAvailableDates().size(); k++){
					if(!takenDates.contains(defence.getJury().get(i).getAvailableDates().get(k))){
						defence.setDay(defence.getJury().get(i).getAvailableDates().get(k));
						setDate=1;
						break;
					}
					
				}
				if(setDate==1){break;}
			}
		}
	}

	private void generateJury(Defence defence, ArrayList<DiplomaWork> works) {
		ArrayList<Person> jury = new ArrayList<Person>();
		
		for (int i = 0; i < defence.getDiplomaWorks().size(); i++) {
			addPersonToJury(jury,defence.getDiplomaWorks().get(i));
		}
		
		if(jury.size()<4){
			int k=0;
			while(jury.size()<4 && k < works.size()){
				addPersonToJury(jury,works.get(k));
				k++;
			}
		}
		defence.setJury(jury);
	}

	public void addPersonToJury(ArrayList<Person> jury, DiplomaWork diplomaWork) {
		if (!jury.contains(diplomaWork.getLeader()) && jury.size()<4) {
			jury.add(diplomaWork.getLeader());
		}
		if (!jury.contains(diplomaWork.getReviewer()) && jury.size()<4) {
				jury.add(diplomaWork.getReviewer());
		}
	}

	public Defence softwareDefenceTypeGeneration(String type, HashSet<Date> takenDates) {
		Defence defence = new Defence();

		if (type.equals("All")) {
			for (int i = 0; i < softwareWorks.size() && defence.getDiplomaWorks().size()<9; i++) {
				defence.addDiploma(softwareWorks.get(i));
			}
		} else {
			int size = 0;
			for (int i = 0; i < softwareWorks.size(); i++) {
				if (softwareWorks.get(i).getType()==type) {
					defence.addDiploma(softwareWorks.get(i));
					size++;
					if (size == 8) {
						break;
					}
				}
			}
		}

		softwareWorks.removeAll(defence.getDiplomaWorks());

		generateJury(defence,softwareWorks);
		setDefenceDate(defence,takenDates);		

		return defence;
	}

	public void organizeLastSoftwareWorks(ArrayList<Defence> defences,
			HashSet<Date> takenDates) {
		if (softwareWorks.size() >= 5 && softwareWorks.size() <= 9) {
			defences.add(softwareDefenceTypeGeneration("All", takenDates));
		} else if (softwareWorks.size() <= 4 && softwareWorks.size()>0) {
			for (int i = 0; i < softwareWorks.size(); i++) {
				defences.get(i).addDiploma(softwareWorks.get(i));
			}
			softwareWorks.clear();
		} else if(softwareWorks.size() > 9){
			defences.add(softwareDefenceTypeGeneration("All", takenDates));
			organizeLastSoftwareWorks(defences, takenDates);
		}
	}
	
	public int softwareWorksContains(String type){
		int contains = 0;
		for(int i =0; i<softwareWorks.size(); i++){
			if(softwareWorks.get(i).getType().equals(type)){
				contains = 1;
				break;
			}
		}
		return contains;
	}
	
	public void clearAllData() {
		leaders.clear();
		reviewers.clear();
		softwareWorks.clear();
		hardwareWorks.clear();
		netWorks.clear();
	}
}