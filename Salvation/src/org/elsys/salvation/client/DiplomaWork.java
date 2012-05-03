package org.elsys.salvation.client;

public class DiplomaWork {
	private String name;
	private String diplomants;
	private Person leader;
	private Person reviewer;
	
	public DiplomaWork() {
		super();
		this.name = new String();
		this.diplomants = new String();
		this.leader = new Person();
		this.reviewer = new Person();
	}
	
	public DiplomaWork(String name, String diplomants,
			Person leader, Person reviewer) {
		super();
		this.name = name;
		this.diplomants = diplomants;
		this.leader = leader;
		this.reviewer = reviewer;
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiplomants() {
		return diplomants;
	}

	public void setDiplomants(String diplomants) {
		this.diplomants = diplomants;
	}

	public Person getLeader() {
		return leader;
	}

	public void setLeader(Person leader) {
		this.leader = leader;
	}

	public Person getReviewer() {
		return reviewer;
	}

	public void setReviewer(Person reviewer) {
		this.reviewer = reviewer;
	}
	
}
