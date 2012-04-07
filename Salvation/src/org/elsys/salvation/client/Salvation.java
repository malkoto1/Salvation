package org.elsys.salvation.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


import com.smartgwt.client.data.DataSource;  
import com.smartgwt.client.data.Record;  
import com.smartgwt.client.types.Alignment;  
import com.smartgwt.client.types.ListGridEditEvent;  
import com.smartgwt.client.types.RowEndEditAction;  
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;  
import com.smartgwt.client.widgets.IButton;  
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.calendar.Calendar;
import com.smartgwt.client.widgets.events.ClickEvent;  
import com.smartgwt.client.widgets.events.ClickHandler;  
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;  
import com.smartgwt.client.widgets.grid.ListGridRecord;  
import com.smartgwt.client.widgets.layout.HLayout;  
import com.smartgwt.client.widgets.layout.VLayout; 


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
		
		existingData.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainHorizontalPanel.clear();
				editDiploma();
			}				
		});
		
	}
	
	private void addDiploma() {
		
		final TextItem projectNameTextBox = new TextItem();
		projectNameTextBox.setTitle("Project name:");
		final TextItem diplomantsNameTextBox = new TextItem();
		diplomantsNameTextBox.setTitle("Diplomants name/s:");
		final TextItem diplomaManagerTextBox = new TextItem();
		diplomaManagerTextBox.setTitle("Diploma Manager:");
		final TextItem reviewerTextBox = new TextItem();
		reviewerTextBox.setTitle("Reviewer:");
		
		DynamicForm projectNameForm = new DynamicForm();
		projectNameForm.setFields(projectNameTextBox);
		DynamicForm diplomantsNameForm = new DynamicForm();
		diplomantsNameForm.setFields(diplomaManagerTextBox);
		DynamicForm diplomaManagerForm = new DynamicForm();
		diplomaManagerForm.setFields(diplomaManagerTextBox);
		DynamicForm reviewerForm = new DynamicForm();
		reviewerForm.setFields(reviewerTextBox);
		
//		final Label projectNameLabel = new Label("Project Name: ");
//		final Label diplomantsNameLabel = new Label("Diplomants Name: ");
//		final Label specialtiesLabel = new Label("Specialties: ");
//		final Label diplomaManagerLabel = new Label("Diploma Manager: ");
//		final Label reviewerLabel = new Label("Reviewer: ");
		
		final Calendar whenAvailableDiplomaManager = new Calendar();
		final Calendar whenAvailableReviewer = new Calendar();
		
		//final ListBox specialties = new ListBox();
		
		final Button submitButton = new Button("Submit");
		
		final HorizontalPanel firstHorizontalPanel = new HorizontalPanel();
		final HorizontalPanel secondHorizontalPanel = new HorizontalPanel();
		final HorizontalPanel thirdHorizontalPanel = new HorizontalPanel();
		final HorizontalPanel forthHorizontalPanel = new HorizontalPanel();
		final HorizontalPanel fifthHorizontalPanel = new HorizontalPanel();
		
		
		
		firstHorizontalPanel.add(projectNameForm);
		
		secondHorizontalPanel.add(diplomantsNameForm);
		
//		thirdHorizontalPanel.add(specialtiesLabel);
//		thirdHorizontalPanel.add(specialties);
		
		forthHorizontalPanel.add(diplomaManagerForm);
		forthHorizontalPanel.add(whenAvailableDiplomaManager);

		fifthHorizontalPanel.add(reviewerForm);
		fifthHorizontalPanel.add(whenAvailableReviewer);
		
		mainVerticalPanel.add(firstHorizontalPanel);
		mainVerticalPanel.add(secondHorizontalPanel);
		mainVerticalPanel.add(thirdHorizontalPanel);
		mainVerticalPanel.add(forthHorizontalPanel);
		mainVerticalPanel.add(fifthHorizontalPanel);
		mainVerticalPanel.add(submitButton);
		RootPanel.get("mainDiv").add(mainVerticalPanel);
	}

	private void editDiploma() {
		
	}
}
