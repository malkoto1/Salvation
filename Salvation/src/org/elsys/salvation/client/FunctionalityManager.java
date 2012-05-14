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
		Leaders = new HashSet<Person>();
		Reviewers = new HashSet<Person>();
		SoftwareWorks = new ArrayList<SoftwareWork>();
		HardwareWorks = new ArrayList<DiplomaWork>();
		NetWorks = new ArrayList<DiplomaWork>();
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

	public void addLeader(Person leader) {
		this.Leaders.add(leader);
	}

	public HashSet<Person> getReviewers() {
		return Reviewers;
	}

	public void setReviewers(HashSet<Person> reviewers) {
		Reviewers = reviewers;
	}

	public void addReviewer(Person reviewer) {
		Reviewers.add(reviewer);
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
				&& !SoftwareWorks.contains(new DiplomaWork(projectName
						.getValueAsString(), diplomants.getValueAsString(),
						leader, reviewer))) {
			SoftwareWorks.add(new SoftwareWork(projectName.getValueAsString(),
					diplomants.getValueAsString(), leader, reviewer, type
							.getValueAsString()));

		} else if (specialtie.getValueAsString().equalsIgnoreCase("Hardware")
				&& !HardwareWorks.contains(new DiplomaWork(projectName
						.getValueAsString(), diplomants.getValueAsString(),
						leader, reviewer))) {
			HardwareWorks.add(new DiplomaWork(projectName.getValueAsString(),
					diplomants.getValueAsString(), leader, reviewer));

		} else if (specialtie.getValueAsString().equalsIgnoreCase(
				"Communication")
				&& !NetWorks.contains(new DiplomaWork(projectName
						.getValueAsString(), diplomants.getValueAsString(),
						leader, reviewer))) {
			NetWorks.add(new DiplomaWork(projectName.getValueAsString(),
					diplomants.getValueAsString(), leader, reviewer));
		}

	}

	public void generateDefences() {
		HashSet<Date> takenDates = new HashSet<Date>();
		ArrayList<Defence> netDefences = new ArrayList<Defence>();
		ArrayList<Defence> hardDefences = new ArrayList<Defence>();

		while (HardwareWorks.size() > 0) {
			hardDefences.add(defaultGeneration(HardwareWorks, takenDates));
		}

		while (NetWorks.size() > 0) {
			netDefences.add(defaultGeneration(NetWorks, takenDates));
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
											.getAvailableDates().get(k))) {
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
				// povtarq se turseneto na lider i se dobavq za 4etvurti
				// reviewer

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
					}else if(defence.getDiplomaWorks().size() > 8){
						while (defence.getDiplomaWorks().size() > 8) {
							works.add(defence.getDiplomaWorks().get(defence.getDiplomaWorks().size()-1));
							defence.removeDiploma();
						}
					}
				}
			}
		}

		works.removeAll(defence.getDiplomaWorks());

		return defence;
	}

	public Defence softwareDefenceTypeGeneration(String specialtie,
			HashSet<Date> takenDates) {
		Defence defence = new Defence();
		ArrayList<Person> jury = new ArrayList<Person>();
		for (int i = 0; i < SoftwareWorks.size(); i++) {
			if (SoftwareWorks.get(i).getType().equals(specialtie)) {
				defence.addDiploma(SoftwareWorks.get(i));
			}
		}

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
			if (jury.get(i).getAvailableDates().size() < busyMan.getAvailableDates().size()) {
				busyMan = jury.get(i);
			}
		}

		boolean setDate = false;

		for (int i = 0; i < busyMan.getAvailableDates().size(); i++) {
			if (!takenDates.contains(busyMan.getAvailableDates().get(i))) {
				defence.setDay(busyMan.getAvailableDates().get(i));
				setDate = true;
				break;
			}
		}

		if (!setDate) {
			for (int i = 0; i < jury.get(2).getAvailableDates().size(); i++) {
				if (!takenDates.contains(jury.get(2).getAvailableDates().get(i))) {
					defence.setDay(jury.get(2).getAvailableDates().get(i));
					break;
				}
			}
		}

		defence.setJury(jury);

		return defence;
	}

	public void clearAllData() {
		Leaders.clear();
		Reviewers.clear();
		SoftwareWorks.clear();
		HardwareWorks.clear();
		NetWorks.clear();
	}

}
