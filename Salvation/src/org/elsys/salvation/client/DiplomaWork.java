package org.elsys.salvation.client;

import java.util.List;

public class DiplomaWork {
	private String name;
	private List<String> diplomants;
	private DiplomaLeader leader;
	private Reviewer reviewer;
	private String type;
	
	public DiplomaWork(String name, List<String> diplomants,
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

	public List<String> getDiplomants() {
		return diplomants;
	}

	public void setDiplomants(List<String> diplomants) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	};
	
}
