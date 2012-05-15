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
	private ArrayList<Person> Leaders;
	private ArrayList<Person> Reviewers;
	private ArrayList<DiplomaWork> SoftwareWorks;
	private ArrayList<DiplomaWork> HardwareWorks;
	private ArrayList<DiplomaWork> NetWorks;
	private ArrayList<Defence> netDefences;
	private ArrayList<Defence> hardDefences;
	private ArrayList<Defence> softDefences;

	public FunctionalityManager() {
		super();
		startDate = new Date();
		endDate = new Date();
		Leaders = new ArrayList<Person>();
		Reviewers = new ArrayList<Person>();
		SoftwareWorks = new ArrayList<DiplomaWork>();
		HardwareWorks = new ArrayList<DiplomaWork>();
		NetWorks = new ArrayList<DiplomaWork>();
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
		return Leaders;
	}

	public void setLeaders(ArrayList<Person> leaders) {
		Leaders = leaders;
	}

	public ArrayList<Person> getReviewers() {
		return Reviewers;
	}

	public void setReviewers(ArrayList<Person> reviewers) {
		Reviewers = reviewers;
	}

	public ArrayList<DiplomaWork> getSoftwareWorks() {
		return SoftwareWorks;
	}

	public void setSoftwareWorks(ArrayList<DiplomaWork> softwareWorks) {
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

		for (int i = 0; i < Leaders.size(); i++) {
			if (Leaders
					.get(i)
					.getName()
					.equals(diplomaLeader.getValue(diplomaLeader
							.getSelectedIndex()))) {
				leader = Leaders.get(i);
			}
		}

		for (int k = 0; k < Reviewers.size(); k++) {
			if (Reviewers
					.get(k)
					.getName()
					.equals(diplomaReviewer.getValue(diplomaReviewer
							.getSelectedIndex()))) {

			}
			reviewer = Reviewers.get(k);
		}

		if (specialtie.getValueAsString().equalsIgnoreCase("Software")
				&& !SoftwareWorks.contains(new DiplomaWork(projectName
						.getValueAsString(), diplomants.getValueAsString(),
						leader, reviewer, type.getValueAsString()))) {
			SoftwareWorks.add(new DiplomaWork(projectName.getValueAsString(),
					diplomants.getValueAsString(), leader, reviewer, type
							.getValueAsString()));

		} else if (specialtie.getValueAsString().equalsIgnoreCase("Hardware")
				&& !HardwareWorks.contains(new DiplomaWork(projectName
						.getValueAsString(), diplomants.getValueAsString(),
						leader, reviewer, "Hardware"))) {
			HardwareWorks
					.add(new DiplomaWork(projectName.getValueAsString(),
							diplomants.getValueAsString(), leader, reviewer,
							"Hardware"));

		} else if (specialtie.getValueAsString().equalsIgnoreCase(
				"Communication")
				&& !NetWorks.contains(new DiplomaWork(projectName
						.getValueAsString(), diplomants.getValueAsString(),
						leader, reviewer, "Communication"))) {
			NetWorks.add(new DiplomaWork(projectName.getValueAsString(),
					diplomants.getValueAsString(), leader, reviewer,
					"Communication"));
		}

	}

	public void generateDefences() {
		HashSet<Date> takenDates = new HashSet<Date>();
		HashSet<Date> softTakenDates = new HashSet<Date>();

		while (HardwareWorks.size() > 0) {
			hardDefences.add(defaultGeneration(HardwareWorks, takenDates));
		}

		while (NetWorks.size() > 0) {
			netDefences.add(defaultGeneration(NetWorks, takenDates));
		}

		if(SoftwareWorks.size()>0){
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
			if(SoftwareWorks.size()>0){
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

				if (defence.getDiplomaWorks().size() >= 7
						&& defence.getDiplomaWorks().size() <= 9) {
					jury.add(defence.getDiplomaWorks()
							.get(defence.getDiplomaWorks().size() - 1)
							.getReviewer());
				} else if (defence.getDiplomaWorks().size() > 9) {
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
			for (int i = 0; i < SoftwareWorks.size() && defence.getDiplomaWorks().size()<9; i++) {
				defence.addDiploma(SoftwareWorks.get(i));
			}
		} else {
			int size = 0;
			for (int i = 0; i < SoftwareWorks.size(); i++) {
				if (SoftwareWorks.get(i).getType().equals(type)) {
					defence.addDiploma(SoftwareWorks.get(i));
					size++;
					if (size == 8) {
						break;
					}
				}
			}
		}

		SoftwareWorks.removeAll(defence.getDiplomaWorks());

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
			defence.setDay(jury.get(0).getAvailableDates().get(0));
		}

		defence.setJury(jury);

		return defence;
	}

	public void organizeLastSoftwareWorks(ArrayList<Defence> defences,
			HashSet<Date> takenDates) {
		if (SoftwareWorks.size() >= 5 && SoftwareWorks.size() <= 9) {
			defences.add(softwareDefenceTypeGeneration("All", takenDates));
		} else if (SoftwareWorks.size() <= 4 && SoftwareWorks.size()>0) {
			for (int i = 0; i < SoftwareWorks.size(); i++) {
				defences.get(i).addDiploma(SoftwareWorks.get(i));
			}
			SoftwareWorks.clear();
		} else if(SoftwareWorks.size() > 9){
			defences.add(softwareDefenceTypeGeneration("All", takenDates));
			organizeLastSoftwareWorks(defences, takenDates);
		}
	}
	
	public int softwareWorksContains(String type){
		int contains = 0;
		for(int i =0; i<SoftwareWorks.size(); i++){
			if(SoftwareWorks.get(i).getType().equals(type)){
				contains = 1;
				break;
			}
		}
		return contains;
	}
	
	public void clearAllData() {
		Leaders.clear();
		Reviewers.clear();
		SoftwareWorks.clear();
		HardwareWorks.clear();
		NetWorks.clear();
	}

}
