package org.elsys.salvation.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import com.google.gwt.user.client.ui.ListBox;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class FunctionalityManager {
	
	private Date startDate;
	private Date endDate;
	private HashSet<Person> Leaders;
	private HashSet<Person> Reviewers;
	private ArrayList<SoftwareWork> SoftwareWorks;
	private ArrayList<DiplomaWork> HardwareWorks;
	private ArrayList<DiplomaWork> NetWorks;
	
	public FunctionalityManager() {
		super();
		startDate = new Date();
		endDate = new Date();
		HashSet<Person> Leaders = new HashSet<Person>();
		HashSet<Person> Reviewers = new HashSet<Person>();
		ArrayList<SoftwareWork> SoftwareWorks = new ArrayList<SoftwareWork>();
		ArrayList<DiplomaWork> HardwareWorks = new ArrayList<DiplomaWork>();
		ArrayList<DiplomaWork> NetWorks = new ArrayList<DiplomaWork>();
		
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
	public HashSet<Person> getLeaders() {
		return Leaders;
	}
	public void setLeaders(HashSet<Person> leaders) {
		Leaders = leaders;
	}
	public HashSet<Person> getReviewers() {
		return Reviewers;
	}
	public void setReviewers(HashSet<Person> reviewers) {
		Reviewers = reviewers;
	}
	public ArrayList<SoftwareWork> getSoftwareWorks() {
		return SoftwareWorks;
	}
	public void setSoftwareWorks(ArrayList<SoftwareWork> softwareWorks) {
		SoftwareWorks = softwareWorks;
	}
	public ArrayList<DiplomaWork> getHardwareWorks() {
		return HardwareWorks;
	}
	public void setHardwareWorks(ArrayList<DiplomaWork> hardwareWorks) {
		HardwareWorks = hardwareWorks;
	}
	public ArrayList<DiplomaWork> getNetWorks() {
		return NetWorks;
	}
	public void setNetWorks(ArrayList<DiplomaWork> netWorks) {
		NetWorks = netWorks;
	}

	public void getPerson(ArrayList<Date> set, TextItem box, ListBox listBox) {
		if (listBox.getSelectedIndex() == 0) {
			Person person = new Person(box.getValueAsString(), set);
			Leaders.add(person);
		} else if (listBox.getSelectedIndex() == 1) {
			Person person = new Person(box.getValueAsString(), set);
			Reviewers.add(person);
		} else if (listBox.getSelectedIndex() == 2) {
			Person person = new Person(box.getValueAsString(), set);
			Leaders.add(person);
			Reviewers.add(person);
		}

	}
	
	public void getDiploma(TextItem projectName, TextItem diplomants,
			ListBox diplomaLeader, ListBox diplomaReviewer,
			ComboBoxItem specialtie, ComboBoxItem type) {

		Person leader = new Person();
		Person reviewer = new Person();

		Iterator<Person> i = Leaders.iterator();
		while (i.hasNext()) {
			if (i.next()
					.getName()
					.equals(diplomaLeader.getValue(diplomaLeader
							.getSelectedIndex()))) {
				leader = i.next();
			}
		}

		Iterator<Person> k = Reviewers.iterator();
		while (k.hasNext()) {
			if (k.next()
					.getName()
					.equals(diplomaReviewer.getValue(diplomaReviewer
							.getSelectedIndex()))) {
				reviewer = k.next();
			}
		}

		if (specialtie.getValueAsString().equalsIgnoreCase("Software")
				&& !SoftwareWorks.contains(new DiplomaWork(projectName.getValueAsString(),
						diplomants.getValueAsString(), leader, reviewer))) {
			SoftwareWorks.add(new SoftwareWork(projectName.getValueAsString(),
					diplomants.getValueAsString(), leader, reviewer, type
							.getValueAsString()));

		} else if (specialtie.getValueAsString().equalsIgnoreCase("Hardware")
				&& !HardwareWorks.contains(new DiplomaWork(projectName.getValueAsString(),
						diplomants.getValueAsString(), leader, reviewer))) {
			HardwareWorks.add(new DiplomaWork(projectName.getValueAsString(),
					diplomants.getValueAsString(), leader, reviewer));

		} else if (specialtie.getValueAsString().equalsIgnoreCase(
				"Communication") && !NetWorks.contains(new DiplomaWork(projectName.getValueAsString(),
						diplomants.getValueAsString(), leader, reviewer))) {
			NetWorks.add(new DiplomaWork(projectName.getValueAsString(),
					diplomants.getValueAsString(), leader, reviewer));
		}

	}
	
	public void generateDefences() {
		HashSet<Date> takenDates = new HashSet<Date>();
		ArrayList<Defence> netDefences= new ArrayList<Defence>();
		ArrayList<Defence> hardDefences= new ArrayList<Defence>();
		
		while(HardwareWorks.size()>0){
			hardDefences.add(defaultGeneration(HardwareWorks, takenDates));
		}
		
		while(NetWorks.size()>0){
			netDefences.add(defaultGeneration(NetWorks, takenDates));
		}
	}
	
	public Defence  defaultGeneration(ArrayList<DiplomaWork> works, HashSet<Date> takenDates){
		Defence defence = new Defence();

		defence.setFirstPerson(works.get(0).getLeader());

		for (int i = 0; i < works.size(); i++) {
			if (works.get(i).getLeader()
					.equals(defence.getFirstPerson())) {
				defence.addDiploma(works.get(i));
			}
		}		

		for (int f = 0; f < works.size(); f++) {
			int broken = 0;
			if (!works.get(f).getLeader()
					.equals(defence.getFirstPerson())) {
				for (int i = 0; i < defence.getFirstPerson()
						.getAvailableDates().size(); i++) {
					for (int k = 0; k < works.get(f).getLeader()
							.getAvailableDates().size(); k++) {
						if (defence
								.getFirstPerson()
								.getAvailableDates()
								.get(i)
								.equals(works.get(f).getLeader()
										.getAvailableDates().get(k))) {
							defence.setDay(defence
									.getFirstPerson().getAvailableDates()
									.get(i));
							defence.setSecondPerson(works.get(
									f).getLeader());
							takenDates.add(defence.getDay());
							broken = 1;
							break;
						}
					}
					if (broken == 1) {
						break;
					}
				}
			}
			if (broken == 1) {
				break;
			}
		}
		
		for(int i = 0; i<works.size(); i++){
			if(works.get(i).getLeader().equals(defence.getSecondPerson())){
				defence.addDiploma(works.get(i));
			}
		}
		
		if(works.isEmpty()){
			return defence;
		}
		
		if(defence.getDiplomaWorks().size()>=7
				|| defence.getDiplomaWorks().size()<=9){
			defence.setThirdPerson(defence.getDiplomaWorks().get(0).getReviewer());
			for(int i = 1; i<defence.getDiplomaWorks().size(); i++){
				if (!defence.getDiplomaWorks().get(i).getReviewer().equals(defence.getThirdPerson())){
					defence.setFourthPerson(works.get(i).getReviewer());
					break;
				}
			}		
		}else if(defence.getDiplomaWorks().size() > 9){
			int size = defence.getDiplomaWorks().size()-1;
			while(defence.getDiplomaWorks().size()>9){
				defence.getDiplomaWorks().remove(size-1);
			}
			defence.setThirdPerson(defence.getDiplomaWorks().get(0).getReviewer());
			for(int i = 1; i<defence.getDiplomaWorks().size(); i++){
				if (!defence.getDiplomaWorks().get(i).getReviewer().equals(defence.getThirdPerson())){
					defence.setFourthPerson(defence.getDiplomaWorks().get(i).getReviewer());
					break;
				}
			}
		}else if(defence.getDiplomaWorks().size()<7){
			//povtarq se turseneto na lider i se dobavq za 4etvurti reviewer
			
			for (int f = 0; f < works.size(); f++) {
				int broken = 0;
				if (!works.get(f).getLeader().equals(defence.getSecondPerson())) {
					for (int k = 0; k < works.get(f).getLeader()
							.getAvailableDates().size(); k++) {
						if (defence.getDay().equals(works.get(f).getLeader().getAvailableDates().get(k))) {
							defence.setThirdPerson(works.get(f).getLeader());
							broken = 1;
							break;
						}
					}
				}
				if (broken == 1) {
					break;
				}
			}
			
			for(int i = 0; i<works.size(); i++){
				if(works.get(i).getLeader().equals(defence.getThirdPerson())){
					defence.addDiploma(works.get(i));
				}
			}
		}
		
		works.removeAll(defence.getDiplomaWorks());
		
		return defence;
	}
	
	public void clearAllData(){
		Leaders.clear();
		Reviewers.clear();
		SoftwareWorks.clear();
		HardwareWorks.clear();
		NetWorks.clear();
	}
	
}
