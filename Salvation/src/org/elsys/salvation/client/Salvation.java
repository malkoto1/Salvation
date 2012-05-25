package org.elsys.salvation.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.smartgwt.client.data.DateRange;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.RowEndEditAction;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.DateRangeItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

public class Salvation implements EntryPoint {

	private FunctionalityManager FM = new FunctionalityManager();
	private DefenceServiceAsync defenceSvc = GWT.create(DefenceService.class);

	public void onModuleLoad() {

		final Button newData = new Button("New");
		// final Button existingData = new Button("Existing");
		final HorizontalPanel mainHorizontalPanel = new HorizontalPanel();
		final Label lastUpdatedLabel = new Label();

		mainHorizontalPanel.add(newData);
		// mainHorizontalPanel.add(existingData);
		mainHorizontalPanel.add(lastUpdatedLabel);

		RootPanel.get("mainDiv").add(mainHorizontalPanel);
		newData.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				//dateRange();
				addPerson();
			}
		});

		// existingData.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// RootPanel.get("mainDiv").clear();
		// editDiploma();
		// }
		// });

	}

	protected void dateRange() {

		VLayout layout = new VLayout(3);

		DateRangeItem dateRangeItem = new DateRangeItem("dri", "Date Range");
		dateRangeItem.setAllowRelativeDates(true);

		final DateRange dateRange = new DateRange();
//		dateRange.setRelativeStartDate(RelativeDate.YESTERDAY);
//		dateRange.setRelativeEndDate(RelativeDate.YESTERDAY);
		dateRangeItem.setValue(dateRange);

		final Button nextButton = new Button("Next");

		nextButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
//				FM.setStartDate(new Date(dateRange.getStartDate().getDate(),
//						dateRange.getStartDate().getMonth(),dateRange.getStartDate().getYear()));
//				FM.setEndDate(new Date(dateRange.getEndDate().getDate(),
//						dateRange.getEndDate().getMonth(),dateRange.getEndDate().getYear()));
				RootPanel.get("mainDiv").clear();
				addPerson();
			}
		});

		Button backButton = new Button("Back");
		backButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				onModuleLoad();
			}
		});

		DynamicForm form = new DynamicForm();
		form.setItems(dateRangeItem);
		layout.addMember(form);
		layout.addMember(nextButton);
		layout.addMember(backButton);

		layout.draw();
		RootPanel.get("mainDiv").add(layout);

	}

	private void addPerson() {
//		SC.say("Pick dates in this range:" + FM.getStartDate().getDate() + "/"
//				+ (FM.getStartDate().getMonth() + 1) + "/20"
//				+ (FM.getStartDate().getYear() - 100) + " : "
//				+ FM.getEndDate().getDate() + "/"
//				+ (FM.getEndDate().getMonth() + 1) + "/20"
//				+ (FM.getEndDate().getYear() - 100));
		final ArrayList<Date> dates = new ArrayList<Date>();

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		HorizontalPanel buttonsPanel = new HorizontalPanel();

		DynamicForm textBoxForm = new DynamicForm();

		final TextItem textBox = new TextItem();
		textBox.setTitle("Name");

		textBoxForm.setFields(textBox);

		final ListBox listBox = new ListBox();
		listBox.addItem("DiplomaLeader");
		listBox.addItem("Reviewer");
		listBox.addItem("Both");

		final DatePicker datePicker = new DatePicker();
		datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@SuppressWarnings("deprecation")
			public void onValueChange(ValueChangeEvent<Date> event) {
				if (dates.add(event.getValue())) {
					datePicker.setTransientEnabledOnDates(false,
							event.getValue());
					SC.say(event.getValue().getDate() + "/"
							+ (event.getValue().getMonth() + 1) + "/20"
							+ (event.getValue().getYear() - 100)
							+ " Added to available dates ");
				}
			}
		});

		Button oneMoreButton = new Button("One More");
		oneMoreButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				FM.getPerson(dates, textBox, listBox);
				RootPanel.get("mainDiv").clear();
				addPerson();
			}
		});

		Button back = new Button("Back");
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				FM.clearAllData();
				onModuleLoad();
			}
		});

		Button next = new Button("Next");
		next.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				FM.getPerson(dates, textBox, listBox);
				RootPanel.get("mainDiv").clear();
				addDiploma();

			}
		});

		horizontalPanel.add(textBoxForm);
		horizontalPanel.add(datePicker);
		horizontalPanel.add(listBox);

		buttonsPanel.add(oneMoreButton);
		buttonsPanel.add(back);
		buttonsPanel.add(next);

		RootPanel.get("mainDiv").add(horizontalPanel);
		RootPanel.get("mainDiv").add(buttonsPanel);
	}

	private void addDiploma() {
		
		final AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {
				
		    }

			@Override
			public void onSuccess(Void result) {
				//SC.say("Submitted");
				RootPanel.get("mainDiv").clear();
				showDefences();
			}
		};

		HorizontalPanel buttonHorizontalPanel = new HorizontalPanel();
		HorizontalPanel listsHorizontalPanel = new HorizontalPanel();
		HorizontalPanel typeListsHorizontalPanel = new HorizontalPanel();
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
		final ListBox reviewersListBox = new ListBox();

		Iterator<Person> i = FM.getLeaders().iterator();
		while (i.hasNext()) {
			diplomaLeadersListBox.addItem(i.next().getName());

		}

		Iterator<Person> k = FM.getReviewers().iterator();
		while (k.hasNext()) {
			reviewersListBox.addItem(k.next().getName());

		}

		final ListBox specialtiesListBox = new ListBox();
		specialtiesListBox.setTitle("Specialties");
		specialtiesListBox.addItem("Hardware");
		specialtiesListBox.addItem("Software");
		specialtiesListBox.addItem("Communication");
		
		final ListBox typeListBox = new ListBox();
		typeListBox.setTitle("Software Type");
		typeListBox.addItem("Game/Media");
		typeListBox.addItem("Plug-in/Driver");
		typeListBox.addItem("Web Site/App");
		typeListBox.addItem("Other");
		typeListBox.setEnabled(false);

		specialtiesListBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				if (specialtiesListBox.getValue(specialtiesListBox.getSelectedIndex())
						.equals("Software")) {
					typeListBox.setEnabled(true);
				} else {
					typeListBox.setEnabled(false);
				}
			}
		});

		Button submitButton = new Button("Submit");
		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				FM.getDiploma(projectNameTextBox, diplomantsNameTextBox,
						diplomaLeadersListBox, reviewersListBox,
						specialtiesListBox, typeListBox);
				FM.generateDefences();
				defenceSvc.saveDefences(FM,callback);
			}
		});

		Button oneMoreButton = new Button("One More");
		oneMoreButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				FM.getDiploma(projectNameTextBox, diplomantsNameTextBox,
						diplomaLeadersListBox, reviewersListBox,
						specialtiesListBox, typeListBox);
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

		buttonHorizontalPanel.add(back);
		buttonHorizontalPanel.add(oneMoreButton);
		buttonHorizontalPanel.add(submitButton);

		listsHorizontalPanel.add(diplomaLeadersListBox);
		listsHorizontalPanel.add(reviewersListBox);
		
		typeListsHorizontalPanel.add(specialtiesListBox);
		typeListsHorizontalPanel.add(typeListBox);

		mainVerticalPanel.add(listsHorizontalPanel);
		mainVerticalPanel.add(typeListsHorizontalPanel);
		mainVerticalPanel.add(buttonHorizontalPanel);

		RootPanel.get("mainDiv").add(mainVerticalPanel);
	}

	protected void showDefences() {
		
		Button showSoftwareDefences = new Button("Show Software");
		showSoftwareDefences.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				showDefence();
			}
		});

		Button showHardwareDefences = new Button("Show Hardware");
		showHardwareDefences.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				showDefence();
			}
		});

		Button showCommunicationsDefences = new Button("Show Communications");
		showCommunicationsDefences.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				showDefence();
			}
		});
		
		RootPanel.get("mainDiv").add(showSoftwareDefences);
		RootPanel.get("mainDiv").add(showCommunicationsDefences);
		RootPanel.get("mainDiv").add(showHardwareDefences);

	}

	protected void showDefence() {  
		final FunctionalityManager funcM = new FunctionalityManager();
		final AsyncCallback<FunctionalityManager> callback1 = new AsyncCallback<FunctionalityManager>() {
			public void onFailure(Throwable caught) {
				
		    }

			@Override
			public void onSuccess(FunctionalityManager result) {
				funcM.setHardDefences(result.getHardDefences());
				
			}
		};
		final AsyncCallback<FunctionalityManager> callback2 = new AsyncCallback<FunctionalityManager>() {
			public void onFailure(Throwable caught) {
				
		    }

			@Override
			public void onSuccess(FunctionalityManager result) {
				funcM.setNetDefences(result.getNetDefences());
				
			}
		};
		final AsyncCallback<FunctionalityManager> callback3 = new AsyncCallback<FunctionalityManager>() {
			public void onFailure(Throwable caught) {
				
		    }

			@Override
			public void onSuccess(FunctionalityManager result) {
				funcM.setSoftDefences(result.getSoftDefences());
				
			}
		};
		defenceSvc.getHardDefences(funcM, callback1);  
		defenceSvc.getNetDefences(funcM, callback2);  
		defenceSvc.getSoftDefences(funcM, callback3);  
        final ListGrid DiplomaGrid = new ListGrid();  
        DiplomaGrid.setWidth(500);  
        DiplomaGrid.setHeight(224);  
        DiplomaGrid.setShowAllRecords(true);  
        DiplomaGrid.setCanEdit(true);  
        DiplomaGrid.setEditEvent(ListGridEditEvent.CLICK);  
        DiplomaGrid.setModalEditing(true);  
        
        DiplomaData dd= new DiplomaData(funcM);
  
        ListGridField nameField = new ListGridField("name", "Project Name");  
        ListGridField diplomantsField = new ListGridField("diplomants", "Diplomants");  
        ListGridField leaderField = new ListGridField("leader", "Leader");   
        ListGridField reviewerField = new ListGridField("reviewer", "Reviewer");  
        ListGridField typeField = new ListGridField("type", "Type"); 
        ListGridField dateField = new ListGridField("date", "Date");
        DiplomaGrid.setFields(new ListGridField[] {nameField, diplomantsField, leaderField,reviewerField, typeField, dateField});  
        DiplomaGrid.setData(dd.getRecords());
        
        RootPanel.get("mainDiv").add(DiplomaGrid);
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
			// public DataSource getRelatedDataSource(ListGridRecord record) {
			// return ItemSupplyXmlDS.getInstance();
			// }

			@Override
			protected Canvas getExpansionComponent(final ListGridRecord record) {

				VLayout layout = new VLayout(5);
				layout.setPadding(5);

				final ListGrid countryGrid = new ListGrid();
				countryGrid.setWidth(500);
				countryGrid.setHeight(224);
				countryGrid.setCellHeight(22);
				// countryGrid.setDataSource(getRelatedDataSource(record));
				// countryGrid.fetchRelatedData(record,
				// SupplyCategoryXmlDS.getInstance());

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

		// listGrid.setAutoFetchData(true);
		// listGrid.setDataSource(dataSource);

		listGrid.draw();

		RootPanel.get("mainDiv").add(listGrid);
		RootPanel.get("mainDiv").add(back);
	}
}