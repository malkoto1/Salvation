package org.elsys.salvation.client;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.smartgwt.client.data.DateRange;
import com.smartgwt.client.data.RelativeDate;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.RowEndEditAction;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateRangeItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;


public class Salvation implements EntryPoint {
	
	private Date startDate = new Date();
	private Date endDate = new Date();
	private HashSet<Reviewer> Reviewers = new HashSet<Reviewer>();
	private HashSet<DiplomaLeader> DiplomaLeaders = new HashSet<DiplomaLeader>();
	private HashSet<DiplomaWork> SoftwareWorks = new HashSet<DiplomaWork>();
	private HashSet<DiplomaWork> HardwareWorks = new HashSet<DiplomaWork>();
	private HashSet<DiplomaWork> NetWorks = new HashSet<DiplomaWork>();
	
	public void onModuleLoad() {
		
		final Button newData = new Button("New");
		final Button existingData = new Button("Existing");
		final HorizontalPanel mainHorizontalPanel = new HorizontalPanel();
		final Label lastUpdatedLabel = new Label();
		
		mainHorizontalPanel.add(newData);
		mainHorizontalPanel.add(existingData);
		mainHorizontalPanel.add(lastUpdatedLabel);
		
		
		RootPanel.get("mainDiv").add(mainHorizontalPanel);
		newData.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				dateRange();
			}				
		});
		
		existingData.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				editDiploma();
			}				
		});  
		
	}
	
	protected void dateRange() {
		
		VLayout layout = new VLayout(3);
		
		DateRangeItem dateRangeItem = new DateRangeItem("dri", "Date Range");  
        dateRangeItem.setAllowRelativeDates(true);  
        final DateRange dateRange = new DateRange();  
        dateRange.setRelativeStartDate(RelativeDate.YESTERDAY);  
        dateRange.setRelativeEndDate(RelativeDate.YESTERDAY);  
        dateRangeItem.setValue(dateRange);
		
        Button nextButton = new Button("Next");

        nextButton.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) { 
                startDate = dateRange.getStartDate();
                endDate = dateRange.getEndDate();
            	RootPanel.get("mainDiv").clear();
            	addPerson();
            }
        }); 
        
        
        
//        if(!dateRange.getStartDate().equals(dateRange.getEndDate())){
//        		nextButton.setDisabled(!nextButton.getDisabled());
//        }
        DynamicForm form = new DynamicForm();
        form.setItems(dateRangeItem);
        layout.addMember(form);
        layout.addMember(nextButton);
        
        layout.draw();
        RootPanel.get("mainDiv").add(layout);
      
        
	}

	private void addPerson(){
		final HashSet<Date> dates = new HashSet<Date>();
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		
		DynamicForm form = new DynamicForm();
		
		final TextItem textBox = new TextItem();
		textBox.setTitle("Name");
		
		form.setFields(textBox);
		
		final ListBox listBox = new ListBox();
		listBox.addItem("DiplomaManager");
		listBox.addItem("Reviewer");

		final DatePicker datePicker = new DatePicker();
		datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@SuppressWarnings("deprecation")
			public void onValueChange(ValueChangeEvent<Date> event) {
		    	  if(dates.add(event.getValue())){
		    		  datePicker.setTransientEnabledOnDates(false, event.getValue());
		    		  SC.say(event.getValue().getDate() +"/"+ (event.getValue().getMonth()+1)
		    			  + "/20" + (event.getValue().getYear()-100) + " Added to unavailable dates ");
		    	  }
			}
		});
		
		Button oneMoreButton = new Button("One More");
		oneMoreButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				getPerson(dates,textBox,listBox);
				RootPanel.get("mainDiv").clear();
            	addPerson();
			}
		});
		
		Button back = new Button("Back");
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				Reviewers.clear();
				DiplomaLeaders.clear();
				SoftwareWorks.clear();
				HardwareWorks.clear();
				NetWorks.clear();
				onModuleLoad();
			}				
		});
		
		Button next = new Button("Next");
		next.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				getPerson(dates,textBox,listBox);
				RootPanel.get("mainDiv").clear();
            	addDiploma();
				
			}			
		});
		
		horizontalPanel.add(form);
		horizontalPanel.add(datePicker);
		horizontalPanel.add(listBox);
		

		buttonsPanel.add(oneMoreButton);
		buttonsPanel.add(back);
		buttonsPanel.add(next);

		RootPanel.get("mainDiv").add(horizontalPanel);
		RootPanel.get("mainDiv").add(buttonsPanel);
	}
	
	private void getPerson(HashSet<Date> set, TextItem box, ListBox list){
		if(list.getSelectedIndex()==0){
			DiplomaLeader leader = new DiplomaLeader(box.getValueAsString(), set);
			DiplomaLeaders.add(leader);
		}else if(list.getSelectedIndex()==1){
			Reviewer reviewer = new Reviewer(box.getValueAsString(), set);
			Reviewers.add(reviewer);
		}else {
			SC.say("Error accured :) Maybe nothing was entered");
		}
		
	}	
	
	private void addDiploma() {
		
		HorizontalPanel buttonHorizontalPanel = new HorizontalPanel();
		HorizontalPanel listsHorizontalPanel = new HorizontalPanel();
		VerticalPanel mainVerticalPanel = new VerticalPanel();
		
		final TextItem projectNameTextBox = new TextItem();
		projectNameTextBox.setTitle("Project name");
		final TextItem diplomantsNameTextBox = new TextItem();
		diplomantsNameTextBox.setTitle("Diplomants name/s");
		
		
		DynamicForm projectNameForm = new DynamicForm();
		projectNameForm.setFields(projectNameTextBox);
		DynamicForm diplomantsNameForm = new DynamicForm();
		diplomantsNameForm.setFields(diplomantsNameTextBox);
		
		final ListBox diplomaLeadersListBox = new ListBox();
		Iterator<DiplomaLeader> i = DiplomaLeaders.iterator();
		while(i.hasNext()){
			diplomaLeadersListBox.addItem(i.next().getName());
		}
		
		final ListBox reviewersListBox = new ListBox();
		Iterator<Reviewer> k = Reviewers.iterator();
		while(k.hasNext()){
			reviewersListBox.addItem(k.next().getName());
		}
		
		final ListBox specialtiesListBox = new ListBox();
		specialtiesListBox.setTitle("Specialty");
		specialtiesListBox.addItem("Software");
		specialtiesListBox.addItem("Hardware");
		specialtiesListBox.addItem("Communication");
		
		final ListBox typeListBox = new ListBox();
		specialtiesListBox.setTitle("Type");
		specialtiesListBox.addItem("Game");
		specialtiesListBox.addItem("Media");
		specialtiesListBox.addItem("Plug-in");
		specialtiesListBox.addItem("Driver");
		specialtiesListBox.addItem("Websites");
		specialtiesListBox.addItem("Other");
		
		Button submitButton = new Button("Submit");
		Button oneMoreButton = new Button("One More");
		oneMoreButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				getDiploma(projectNameTextBox,diplomantsNameTextBox,diplomaLeadersListBox,
						reviewersListBox,specialtiesListBox, typeListBox);
				RootPanel.get("mainDiv").clear();
				addDiploma();
			}				
		});
		
		Button back = new Button("Back");
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				addPerson();
			}				
		});
				
		
		mainVerticalPanel.add(projectNameForm);
		mainVerticalPanel.add(diplomantsNameForm);
		mainVerticalPanel.add(specialtiesListBox);

		buttonHorizontalPanel.add(back);
		buttonHorizontalPanel.add(oneMoreButton);
		buttonHorizontalPanel.add(submitButton);
		
		listsHorizontalPanel.add(diplomaLeadersListBox);
		listsHorizontalPanel.add(reviewersListBox);
		
		mainVerticalPanel.add(listsHorizontalPanel);
		mainVerticalPanel.add(buttonHorizontalPanel);
		
		RootPanel.get("mainDiv").add(mainVerticalPanel);
	}

	protected void getDiploma(TextItem projectName,
			TextItem diplomants, ListBox diplomaLeader,
			ListBox reviewer, ListBox specialtie, ListBox type) {
			DiplomaWork work = new DiplomaWork(projectName.getValueAsString(),
				diplomants.getValueAsString(), diplomaLeader.getItemText(diplomaLeader.getSelectedIndex()),
				reviewer.getItemText(reviewer.getSelectedIndex()), reviewer.getItemText(reviewer.getSelectedIndex()));
			if(specialtie.getSelectedIndex() == 0){
				SoftwareWorks.add(work);
			}else if(specialtie.getSelectedIndex() == 1) {
				HardwareWorks.add(work);
			} else if(specialtie.getSelectedIndex() == 2) {
				NetWorks.add(work);
			}
		
	}

	private void editDiploma() {
		
		final Button back = new Button("Back");
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				onModuleLoad();
			}				
		});
		
		ListGrid listGrid = new ListGrid() {  
//          public DataSource getRelatedDataSource(ListGridRecord record) {  
//              return ItemSupplyXmlDS.getInstance();  
//          }  

          @Override  
          protected Canvas getExpansionComponent(final ListGridRecord record) {  

              VLayout layout = new VLayout(5);  
              layout.setPadding(5);  

              final ListGrid countryGrid = new ListGrid();  
              countryGrid.setWidth(500);  
              countryGrid.setHeight(224);  
              countryGrid.setCellHeight(22);  
//            countryGrid.setDataSource(getRelatedDataSource(record));  
//            countryGrid.fetchRelatedData(record, SupplyCategoryXmlDS.getInstance());  

              countryGrid.setCanEdit(true);  
              countryGrid.setModalEditing(true);  
              countryGrid.setEditEvent(ListGridEditEvent.CLICK);  
              countryGrid.setListEndEditAction(RowEndEditAction.NEXT);  
              countryGrid.setAutoSaveEdits(false);  

              layout.addMember(countryGrid);  
                                                  
              return layout;  
          }
		
		};
		listGrid.setWidth(600);  
	    listGrid.setHeight(500);  
	    listGrid.setDrawAheadRatio(4);  
	    listGrid.setCanExpandRecords(true);  

//	    listGrid.setAutoFetchData(true);  
//	    listGrid.setDataSource(dataSource);  

	    listGrid.draw();
	    
	    RootPanel.get("mainDiv").add(listGrid);
	    RootPanel.get("mainDiv").add(back);
	}  
}
