package org.elsys.salvation.server;

import java.util.ArrayList;

import org.elsys.salvation.client.Defence;
import org.elsys.salvation.client.FunctionalityManager;

public class Defences {

	private ArrayList<Defence> netDefences;
	private ArrayList<Defence> hardDefences;
	private ArrayList<Defence> softDefences;
	
	public Defences(FunctionalityManager fm){
		netDefences = fm.getNetDefences();
		hardDefences = fm.getHardDefences();
		softDefences = fm.getSoftDefences();
	}
	
	

	public void setNetDefences(ArrayList<Defence> netDefences) {
		this.netDefences = netDefences;
	}



	public void setHardDefences(ArrayList<Defence> hardDefences) {
		this.hardDefences = hardDefences;
	}



	public void setSoftDefences(ArrayList<Defence> softDefences) {
		this.softDefences = softDefences;
	}



	public ArrayList<Defence> getNetDefences() {
		return netDefences;
	}

	public ArrayList<Defence> getHardDefences() {
		return hardDefences;
	}

	public ArrayList<Defence> getSoftDefences() {
		return softDefences;
	}
}