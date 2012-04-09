package org.elsys.salvation.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.layout.client.Layout;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InsertPanel.ForIsWidget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


import com.smartgwt.client.data.DataSource;  
import com.smartgwt.client.data.DateRange;
import com.smartgwt.client.data.Record;  
import com.smartgwt.client.data.RelativeDate;
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
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.DateRangeItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;  
import com.smartgwt.client.widgets.grid.ListGridRecord;  
import com.smartgwt.client.widgets.layout.HLayout;  
import com.smartgwt.client.widgets.layout.VLayout; 


public class Salvation implements EntryPoint {
	
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
		
//		ListGrid listGrid = new ListGrid() {  
//            public DataSource getRelatedDataSource(ListGridRecord record) {  
//                return ItemSupplyXmlDS.getInstance();  
//            }  
		
	}
	
	protected void dateRange() {
		
		VLayout layout = new VLayout(3);
		
		DateRangeItem dateRangeItem = new DateRangeItem("dri", "Date Range");  
        dateRangeItem.setAllowRelativeDates(true);  
        DateRange dateRange = new DateRange();  
        dateRange.setRelativeStartDate(RelativeDate.YESTERDAY);  
        dateRange.setRelativeEndDate(RelativeDate.YESTERDAY);  
        dateRangeItem.setValue(dateRange);
		
        Button nextButton = new Button("Next");
        nextButton.setDisabled(true);

        nextButton.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {  
            	RootPanel.get("mainDiv").clear();
	 			addDiploma();
            }
        }); 
        
        
        
//        if(!dateRange.getStartDate().equals(dateRange.getEndDate())){
//        		nextButton.setDisabled(!nextButton.getDisabled());
//        }
        
//        nextButton.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				RootPanel.get("mainDiv").clear();
//				addDiploma();
//			}				
//		});
        DynamicForm form = new DynamicForm();
        form.setItems(dateRangeItem);
        layout.addMember(form);
        layout.addMember(nextButton);
        
        layout.draw();
        RootPanel.get("mainDiv").add(layout);
      
        
	}

	private void addDiploma() {
		
		HorizontalPanel firstHorizontalPanel = new HorizontalPanel();
		HorizontalPanel secondHorizontalPanel = new HorizontalPanel();
		HorizontalPanel thirdHorizontalPanel = new HorizontalPanel();
		VerticalPanel mainVerticalPanel = new VerticalPanel();
		
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
		
		final Calendar diplomaManagerCalendar = new Calendar();
		setToSimpleCalendar(diplomaManagerCalendar);
		final Calendar reviewerCalendar = new Calendar();
		setToSimpleCalendar(reviewerCalendar);
		
		ComboBoxItem specialtiesCombo = new ComboBoxItem();
		specialtiesCombo.setTitle("Specialty");  
		DynamicForm specialtiesForm = new DynamicForm();
		specialtiesForm.setFields(specialtiesCombo);
		
		final Button submitButton = new Button("Submit");
		final Button oneMoreButton = new Button("One More");
		final Button back = new Button("Back");
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				onModuleLoad();
			}				
		});
				
		
		mainVerticalPanel.add(projectNameForm);
		
		mainVerticalPanel.add(diplomantsNameForm);
		
		mainVerticalPanel.add(specialtiesForm);
		
		firstHorizontalPanel.add(diplomaManagerForm);
		firstHorizontalPanel.add(diplomaManagerCalendar);

		secondHorizontalPanel.add(reviewerForm);
		secondHorizontalPanel.add(reviewerCalendar);
		
		thirdHorizontalPanel.add(submitButton);
		thirdHorizontalPanel.add(oneMoreButton);
		
		mainVerticalPanel.add(firstHorizontalPanel);
		mainVerticalPanel.add(secondHorizontalPanel);
		mainVerticalPanel.add(thirdHorizontalPanel);
		mainVerticalPanel.add(back);
		
		RootPanel.get("mainDiv").add(mainVerticalPanel);
	}

	private void setToSimpleCalendar(Calendar calendar) {
		calendar.setWidth(220);  
        calendar.setHeight(130);  
        calendar.setShowDayView(false);  
        calendar.setShowWeekView(false);  
        calendar.setShowOtherDays(false);  
        calendar.setShowDayHeaders(false);  
        calendar.setShowDatePickerButton(false);  
        calendar.setShowAddEventButton(false);  
        calendar.setDisableWeekends(false);          
        calendar.setShowDateChooser(false);  
        calendar.setCanCreateEvents(false); 
		
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

              final ListGrid grid = this;  

              VLayout layout = new VLayout(5);  
              layout.setPadding(5);  

              final ListGrid countryGrid = new ListGrid();  
              countryGrid.setWidth(500);  
              countryGrid.setHeight(224);  
              countryGrid.setCellHeight(22);  
//              countryGrid.setDataSource(getRelatedDataSource(record));  
//              countryGrid.fetchRelatedData(record, SupplyCategoryXmlDS.getInstance());  

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
