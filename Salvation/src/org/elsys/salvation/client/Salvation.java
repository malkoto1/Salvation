package org.elsys.salvation.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;


public class Salvation implements EntryPoint {

	private Button newData = new Button("New");
	private Button existingData = new Button("Existing");
	private HorizontalPanel mainHorizontalPanel = new HorizontalPanel();
	private Label lastUpdatedLabel = new Label();
	private VerticalPanel mainVerticalPanel = new VerticalPanel();
	
	public void onModuleLoad() {
		mainHorizontalPanel.add(newData);
		mainHorizontalPanel.add(existingData);
		mainHorizontalPanel.add(lastUpdatedLabel);
		
		
		RootPanel.get("mainDiv").add(mainHorizontalPanel);
		
		newData.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainHorizontalPanel.clear();
				addDiploma();
			}				
		});
	}
	private void addDiploma() {
		
		final TextBox projectNameTextBox = new TextBox();
		final TextBox diplomantsNameTextBox = new TextBox();
		final TextBox diplomaManager = new TextBox();
		final TextBox reviewer = new TextBox();
		
		final Label projectNameLabel = new Label("Project Name: ");
		final Label diplomantsNameLabel = new Label("Diplomants Name: ");
		final Label specialtiesLabel = new Label("Specialties: ");
		final Label diplomaManagerLabel = new Label("Diploma Manager: ");
		final Label reviewerLabel = new Label("Reviewer: ");
		
		final DatePicker whenAvailableDiplomaManager = new DatePicker();
		final DatePicker whenAvailableReviewer = new DatePicker();
		
		final ListBox specialties = new ListBox();
		
		final Button submitButton = new Button("Submit");
		
		final HorizontalPanel firstHorizontalPanel = new HorizontalPanel();
		final HorizontalPanel secondHorizontalPanel = new HorizontalPanel();
		final HorizontalPanel thirdHorizontalPanel = new HorizontalPanel();
		final HorizontalPanel forthHorizontalPanel = new HorizontalPanel();
		final HorizontalPanel fifthHorizontalPanel = new HorizontalPanel();
		
		
		
		firstHorizontalPanel.add(projectNameLabel);
		firstHorizontalPanel.add(projectNameTextBox);
		
		secondHorizontalPanel.add(diplomantsNameLabel);
		secondHorizontalPanel.add(diplomantsNameTextBox);
		
		thirdHorizontalPanel.add(specialtiesLabel);
		thirdHorizontalPanel.add(specialties);
		
		forthHorizontalPanel.add(diplomaManagerLabel);
		forthHorizontalPanel.add(diplomaManager);
		forthHorizontalPanel.add(whenAvailableDiplomaManager);
		
		fifthHorizontalPanel.add(reviewerLabel);
		fifthHorizontalPanel.add(reviewer);
		fifthHorizontalPanel.add(whenAvailableReviewer);
		
		mainVerticalPanel.add(firstHorizontalPanel);
		mainVerticalPanel.add(secondHorizontalPanel);
		mainVerticalPanel.add(thirdHorizontalPanel);
		mainVerticalPanel.add(forthHorizontalPanel);
		mainVerticalPanel.add(fifthHorizontalPanel);
		mainVerticalPanel.add(submitButton);
		RootPanel.get("mainDiv").add(mainVerticalPanel);
	}

}
