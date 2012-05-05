package org.elsys.salvation.client;

public class SoftwareWork extends DiplomaWork{
	private String type;

	public SoftwareWork() {
		super();
		this.type = new String();
	}

	public SoftwareWork(String name, String diplomants, Person leader,
			Person reviewer, String type) {
		super(name, diplomants, leader, reviewer);
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
