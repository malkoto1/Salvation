package org.elsys.salvation.client;

public class DiplomaWork {
	private String name;
	private String diplomants;
	private DiplomaLeader leader;
	private Reviewer reviewer;
	
	public DiplomaWork(String name, String diplomants,
			DiplomaLeader leader, Reviewer reviewer) {
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

	public DiplomaLeader getLeader() {
		return leader;
	}

	public void setLeader(DiplomaLeader leader) {
		this.leader = leader;
	}

	public Reviewer getReviewer() {
		return reviewer;
	}

	public void setReviewer(Reviewer reviewer) {
		this.reviewer = reviewer;
	}
	
}
