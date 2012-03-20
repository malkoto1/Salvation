package org.elsys.salvation.client;

import java.util.List;

public class Defence {
	private List<DiplomaWork> diplomaWorks;

	public Defence(List<DiplomaWork> diplomaWorks) {
		super();
		this.diplomaWorks = diplomaWorks;
	}

	public List<DiplomaWork> getDiplomaWorks() {
		return diplomaWorks;
	}

	public void setDiplomaWorks(List<DiplomaWork> diplomaWorks) {
		this.diplomaWorks = diplomaWorks;
	}
	
}
