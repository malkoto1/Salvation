package org.elsys.salvation.client;

import java.util.HashSet;


public class Defence {
	private HashSet<DiplomaWork> diplomaWorks;

	public Defence(HashSet<DiplomaWork> diplomaWorks) {
		super();
		this.diplomaWorks = diplomaWorks;
	}

	public HashSet<DiplomaWork> getDiplomaWorks() {
		return diplomaWorks;
	}

	public void setDiplomaWorks(HashSet<DiplomaWork> diplomaWorks) {
		this.diplomaWorks = diplomaWorks;
	}
	
	public void addDiploma(DiplomaWork work){
		this.diplomaWorks.add(work);
	}
	
}
