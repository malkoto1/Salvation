package org.elsys.salvation.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import com.google.gwt.user.client.ui.ListBox;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
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

	public void getPerson(ArrayList<Date> set, TextItem box, ListBox listBox) {
		if (listBox.getSelectedIndex() == 0) {
			Person person = new Person(box.getValueAsString(), set);
			leaders.add(person);
		} else if (listBox.getSelectedIndex() == 1) {
			Person person = new Person(box.getValueAsString(), set);
			reviewers.add(person);
		} else if (listBox.getSelectedIndex() == 2) {
			Person person = new Person(box.getValueAsString(), set);
			leaders.add(person);
			reviewers.add(person);
		}
	}

	public void getDiploma(TextItem projectName, TextItem diplomants,
			ListBox diplomaLeader, ListBox diplomaReviewer,
			ComboBoxItem specialtie, ComboBoxItem type) {

		Person leader = new Person();
		Person reviewer = new Person();

		for (int i = 0; i < leaders.size(); i++) {
			if (leaders
					.get(i)
					.getName()
					.equals(diplomaLeader.getValue(diplomaLeader
							.getSelectedIndex()))) {
				leader = leaders.get(i);
			}
		}

		for (int k = 0; k < reviewers.size(); k++) {
			if (reviewers
					.get(k)
					.getName()
					.equals(diplomaReviewer.getValue(diplomaReviewer
							.getSelectedIndex()))) {

			}
			reviewer = reviewers.get(k);
		}

		if (specialtie.getValueAsString().equalsIgnoreCase("Software")
				&& !softwareWorks.contains(new DiplomaWork(projectName
						.getValueAsString(), diplomants.getValueAsString(),
						leader, reviewer, type.getValueAsString()))) {
			softwareWorks.add(new DiplomaWork(projectName.getValueAsString(),
					diplomants.getValueAsString(), leader, reviewer, type
							.getValueAsString()));

		} else if (specialtie.getValueAsString().equalsIgnoreCase("Hardware")
				&& !hardwareWorks.contains(new DiplomaWork(projectName
						.getValueAsString(), diplomants.getValueAsString(),
						leader, reviewer, "Hardware"))) {
			hardwareWorks
					.add(new DiplomaWork(projectName.getValueAsString(),
							diplomants.getValueAsString(), leader, reviewer,
							"Hardware"));

		} else if (specialtie.getValueAsString().equalsIgnoreCase(
				"Communication")
				&& !netWorks.contains(new DiplomaWork(projectName
						.getValueAsString(), diplomants.getValueAsString(),
						leader, reviewer, "Communication"))) {
			netWorks.add(new DiplomaWork(projectName.getValueAsString(),
					diplomants.getValueAsString(), leader, reviewer,
					"Communication"));
		}

	}

	public void generateDefences() {
		HashSet<Date> takenDates = new HashSet<Date>();
		HashSet<Date> softTakenDates = new HashSet<Date>();

		while (hardwareWorks.size() > 0) {
			hardDefences.add(defaultGeneration(hardwareWorks, takenDates));
		}

		while (netWorks.size() > 0) {
			netDefences.add(defaultGeneration(netWorks, takenDates));
		}

		if(softwareWorks.size()>0){
			if(softwareWorksContains("Game/Media") == 1){
				softDefences.add(softwareDefenceTypeGeneration("Game/Media",
						softTakenDates));
			}
			if(softwareWorksContains("Web Site/App") == 1){
				softDefences.add(softwareDefenceTypeGeneration("Web Site/App",
						softTakenDates));
			}
			if(softwareWorksContains("Plug-in/Driver") == 1){
				softDefences.add(softwareDefenceTypeGeneration("Plug-in/Driver",
						softTakenDates));
			}
			if(softwareWorksContains("Other") == 1){
				softDefences
				.add(softwareDefenceTypeGeneration("Other", softTakenDates));
			}
			if(softwareWorks.size()>0){
				organizeLastSoftwareWorks(softDefences, softTakenDates);
			}
		}
	}

	public Defence defaultGeneration(ArrayList<DiplomaWork> works,
			HashSet<Date> takenDates) {
		Defence defence = new Defence();
		ArrayList<Person> jury = new ArrayList<Person>();

		if (works.size() <= 9) {
			for (int i = 0; i < works.size(); i++) {
				defence.addDiploma(works.get(i));
			}
			works.clear();
			return defence;
		} else {
			jury.add(works.get(0).getLeader());

			for (int i = 0; i < works.size(); i++) {
				if (works.get(i).getLeader().equals(jury.get(0))) {
					defence.addDiploma(works.get(i));
				}
			}

			for (int f = 0; f < works.size(); f++) {
				int broken = 0;
				if (!works.get(f).getLeader().equals(jury.get(0))) {
					for (int i = 0; i < jury.get(0).getAvailableDates().size(); i++) {
						for (int k = 0; k < works.get(f).getLeader()
								.getAvailableDates().size(); k++) {
							if (jury.get(0)
									.getAvailableDates()
									.get(i)
									.equals(works.get(f).getLeader()
											.getAvailableDates().get(k))
									&& !takenDates.contains(jury.get(0)
											.getAvailableDates().get(i))) {
								defence.setDay(jury.get(0).getAvailableDates()
										.get(i));
								jury.add(works.get(f).getLeader());
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

			for (int i = 0; i < works.size(); i++) {
				if (works.get(i).getLeader().equals(jury.get(1))) {
					defence.addDiploma(works.get(i));
				}
			}

			works.removeAll(defence.getDiplomaWorks());

			if (defence.getDiplomaWorks().size() >= 7
					&& defence.getDiplomaWorks().size() <= 9) {
				jury.add(defence.getDiplomaWorks().get(0).getReviewer());
				for (int i = 1; i < defence.getDiplomaWorks().size(); i++) {
					if (!defence.getDiplomaWorks().get(i).getReviewer()
							.equals(jury.get(2))) {
						jury.add(defence.getDiplomaWorks().get(i).getReviewer());
						break;
					}
				}
			} else if (defence.getDiplomaWorks().size() > 9) {
				while (defence.getDiplomaWorks().size() > 8) {
					works.add(defence.getDiplomaWorks().get(
							defence.getDiplomaWorks().size() - 1));
					defence.removeDiploma();
				}
				jury.add(defence.getDiplomaWorks().get(0).getReviewer());
				for (int i = 1; i < defence.getDiplomaWorks().size(); i++) {
					if (!defence.getDiplomaWorks().get(i).getReviewer()
							.equals(jury.get(2))) {
						jury.add(defence.getDiplomaWorks().get(i).getReviewer());
						break;
					}
				}
			} else if (defence.getDiplomaWorks().size() < 7) {

				for (int f = 0; f < works.size(); f++) {
					int broken = 0;
					if (!works.get(f).getLeader().equals(jury.get(1))) {
						for (int k = 0; k < works.get(f).getLeader()
								.getAvailableDates().size(); k++) {
							if (defence.getDay().equals(
									works.get(f).getLeader()
											.getAvailableDates().get(k))) {
								jury.add(works.get(f).getLeader());
								broken = 1;
								break;
							}
						}
						if (broken == 1) {
							break;
						}
					}
				}

				for (int i = 0; i < works.size(); i++) {
					if (works.get(i).getLeader().equals(jury.get(2))) {
						defence.addDiploma(works.get(i));
					}
				}
				works.removeAll(defence.getDiplomaWorks());

				if (defence.getDiplomaWorks().size() > 7) {
					while (defence.getDiplomaWorks().size() > 8) {
						works.add(defence.getDiplomaWorks().get(
								defence.getDiplomaWorks().size() - 1));
						defence.removeDiploma();
					}
					jury.add(defence.getDiplomaWorks().get(0).getReviewer());
				} else if (defence.getDiplomaWorks().size() < 7) {
					for (int f = 0; f < works.size(); f++) {
						int broken = 0;
						if (!works.get(f).getLeader().equals(jury.get(2))) {
							for (int k = 0; k < works.get(f).getLeader()
									.getAvailableDates().size(); k++) {
								if (defence.getDay().equals(
										works.get(f).getLeader()
												.getAvailableDates().get(k))) {
									jury.add(works.get(f).getLeader());
									broken = 1;
									break;
								}
							}
							if (broken == 1) {
								break;
							}
						}
					}

					for (int i = 0; i < works.size(); i++) {
						if (works.get(i).getLeader().equals(jury.get(3))) {
							defence.addDiploma(works.get(i));
						}
					}
					works.removeAll(defence.getDiplomaWorks());
					if (defence.getDiplomaWorks().size() < 8) {
						while (defence.getDiplomaWorks().size() < 8) {
							defence.addDiploma(works.get(works.size() - 1));
							works.remove(works.get(works.size() - 1));
						}
					} else if (defence.getDiplomaWorks().size() > 8) {
						while (defence.getDiplomaWorks().size() > 8) {
							works.add(defence.getDiplomaWorks().get(
									defence.getDiplomaWorks().size() - 1));
							defence.removeDiploma();
						}
					}
				}
			}
		}

		works.removeAll(defence.getDiplomaWorks());

		return defence;
	}

	public Defence softwareDefenceTypeGeneration(String type,
			HashSet<Date> takenDates) {
		Defence defence = new Defence();
		ArrayList<Person> jury = new ArrayList<Person>();

		if (type.equals("All")) {
			for (int i = 0; i < softwareWorks.size() && defence.getDiplomaWorks().size()<9; i++) {
				defence.addDiploma(softwareWorks.get(i));
			}
		} else {
			int size = 0;
			for (int i = 0; i < softwareWorks.size(); i++) {
				if (softwareWorks.get(i).getType().equals(type)) {
					defence.addDiploma(softwareWorks.get(i));
					size++;
					if (size == 8) {
						break;
					}
				}
			}
		}

		softwareWorks.removeAll(defence.getDiplomaWorks());

		for (int i = 0; i < defence.getDiplomaWorks().size(); i++) {
			if (!jury.contains(defence.getDiplomaWorks().get(i).getLeader())) {
				jury.add(defence.getDiplomaWorks().get(i).getLeader());
			}
		}

		for (int i = 0; i < defence.getDiplomaWorks().size(); i++) {
			if (!jury.contains(defence.getDiplomaWorks().get(i).getReviewer())) {
				jury.add(defence.getDiplomaWorks().get(i).getReviewer());
			}
		}

		if (jury.size() > 4) {
			while (jury.size() > 4) {
				jury.remove(jury.size() - 1);
			}
		}

		Person busyMan = new Person();
		busyMan = jury.get(0);

		for (int i = 1; i < jury.size(); i++) {
			if (jury.get(i).getAvailableDates().size() < busyMan
					.getAvailableDates().size()) {
				busyMan = jury.get(i);
			}
		}

		int setDate = 0;

		for (int i = 0; i < busyMan.getAvailableDates().size(); i++) {
			if (!takenDates.contains(busyMan.getAvailableDates().get(i))) {
				defence.setDay(busyMan.getAvailableDates().get(i));
				setDate = 1;
				break;
			}
		}

		if (setDate == 1 && jury.size() > 1) {
			for (int i = 0; i < jury.get(1).getAvailableDates().size(); i++) {
				if (!takenDates
						.contains(jury.get(1).getAvailableDates().get(i))) {
					defence.setDay(jury.get(1).getAvailableDates().get(i));
					break;
				}
			}
		} else {
			defence.setDay(startDate);
		}

		defence.setJury(jury);

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
