package org.elsys.salvation.client;

import java.util.Date;
import java.util.HashSet;

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
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.DateRangeItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;


public class Salvation implements EntryPoint {
	
	private java.util.Date startDate;
	private java.util.Date endDate;
	
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
        
        startDate = dateRange.getStartDate();
        endDate = dateRange.getEndDate();
		
        Button nextButton = new Button("Next");

        nextButton.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {  
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
		final HashSet<Date> dates = new HashSet();
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		
		TextItem textBox = new TextItem();
		textBox.setTitle("Name");
		
		DynamicForm form = new DynamicForm();
		
		 ListBox listBox = new ListBox();
		 listBox.addItem("DiplomaManager");
		 listBox.addItem("Reviewer");
		 listBox.setVisibleItemCount(2);

		DatePicker datePicker = new DatePicker();
		datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
		      public void onValueChange(ValueChangeEvent<Date> event) {
		    	  dates.add(event.getValue());
		      }
		    });
		
		Button oneMoreButton = new Button("One More");
		oneMoreButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
//				SC.say(new Integer(dates.size()).toString());
				RootPanel.get("mainDiv").clear();
            	addPerson();
			}				
		});
		
		Button back = new Button("Back");
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				onModuleLoad();
			}				
		});
		
		Button next = new Button("Next");
		next.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
            	addDiploma();
				
			}				
		});
		
		
		//horizontalPanel.add(form);
		//horizontalPanel.add(datePicker);
		form.setFields(textBox);
		form.addChild(datePicker);

		buttonsPanel.add(oneMoreButton);
		buttonsPanel.add(back);
		buttonsPanel.add(next);

		RootPanel.get("mainDiv").add(form);
		RootPanel.get("mainDiv").add(buttonsPanel);
		
	}
	
	private void addDiploma() {
		
		HorizontalPanel thirdHorizontalPanel = new HorizontalPanel();
		VerticalPanel mainVerticalPanel = new VerticalPanel();
		
		TextItem projectNameTextBox = new TextItem();
		projectNameTextBox.setTitle("Project name:");
		TextItem diplomantsNameTextBox = new TextItem();
		diplomantsNameTextBox.setTitle("Diplomants name/s:");
		
		
		DynamicForm projectNameForm = new DynamicForm();
		projectNameForm.setFields(projectNameTextBox);
		DynamicForm diplomantsNameForm = new DynamicForm();
		diplomantsNameForm.setFields(diplomantsNameTextBox);
		
		
		
		
		ComboBoxItem specialtiesCombo = new ComboBoxItem();
		specialtiesCombo.setTitle("Specialty");  
		DynamicForm specialtiesForm = new DynamicForm();
		specialtiesForm.setFields(specialtiesCombo);
		
		Button submitButton = new Button("Submit");
		Button oneMoreButton = new Button("One More");
		Button back = new Button("Back");
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				onModuleLoad();
			}				
		});
				
		
		mainVerticalPanel.add(projectNameForm);
		
		mainVerticalPanel.add(diplomantsNameForm);
		
		mainVerticalPanel.add(specialtiesForm);

		
		thirdHorizontalPanel.add(submitButton);
		thirdHorizontalPanel.add(oneMoreButton);
		
		mainVerticalPanel.add(thirdHorizontalPanel);
		mainVerticalPanel.add(back);
		
		RootPanel.get("mainDiv").add(mainVerticalPanel);
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
