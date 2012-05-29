package org.elsys.salvation.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
//import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.smartgwt.client.data.DateRange;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateRangeItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;

public class Salvation implements EntryPoint {

	private FunctionalityManager FM = new FunctionalityManager();
//	private DefenceServiceAsync defenceSvc = GWT.create(DefenceService.class);

	public void onModuleLoad() {

		final Button newData = new Button("New");
		newData.addStyleName("newDataButton");
		newData.setWidth(100);
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
				// dateRange();
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
		// dateRange.setRelativeStartDate(RelativeDate.YESTERDAY);
		// dateRange.setRelativeEndDate(RelativeDate.YESTERDAY);
		dateRangeItem.setValue(dateRange);

		final Button nextButton = new Button("Next");
		nextButton.setWidth(100);
		nextButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// FM.setStartDate(new Date(dateRange.getStartDate().getDate(),
				// dateRange.getStartDate().getMonth(),dateRange.getStartDate().getYear()));
				// FM.setEndDate(new Date(dateRange.getEndDate().getDate(),
				// dateRange.getEndDate().getMonth(),dateRange.getEndDate().getYear()));
				RootPanel.get("mainDiv").clear();
				addPerson();
			}
		});

		Button backButton = new Button("Back");
		backButton.setWidth(100);
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
		// SC.say("Pick dates in this range:" + FM.getStartDate().getDate() +
		// "/"
		// + (FM.getStartDate().getMonth() + 1) + "/20"
		// + (FM.getStartDate().getYear() - 100) + " : "
		// + FM.getEndDate().getDate() + "/"
		// + (FM.getEndDate().getMonth() + 1) + "/20"
		// + (FM.getEndDate().getYear() - 100));
		final ArrayList<Date> dates = new ArrayList<Date>();

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		HorizontalPanel buttonsPanel = new HorizontalPanel();

		DynamicForm textBoxForm = new DynamicForm();

		final TextItem textBox = new TextItem();
		textBox.setWidth(70);
		textBox.setTitle("Name");

		textBoxForm.setFields(textBox);
		textBoxForm.setWidth(100);

		final ListBox listBox = new ListBox();
		listBox.setPixelSize(100, 25);
		listBox.addItem("Diploma Leader");
		listBox.addItem("Reviewer");
		listBox.addItem("Both");

		final DatePicker datePicker = new DatePicker();
		datePicker.setPixelSize(160, 160);
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
		oneMoreButton.setWidth(160);
		oneMoreButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (FM.checkEmpty(textBox)) {
					SC.say("Enter name!");
				} else {
					FM.getPerson(dates, textBox.getValueAsString(), listBox.getValue(listBox.getSelectedIndex()));
					RootPanel.get("mainDiv").clear();
					addPerson();
				}
			}
		});
		
		Button next = new Button("Next");
		next.setWidth(100);
		next.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (FM.checkEmpty(textBox)) {
					SC.say("Enter name!");
				} else {
					FM.getPerson(dates, textBox.getValueAsString(), listBox.getValue(listBox.getSelectedIndex()));
					RootPanel.get("mainDiv").clear();
					addDiploma();
				}
			}
		});
		
		Button back = new Button("Back");
		back.setWidth(100);
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				FM.clearAllData();
				onModuleLoad();
			}
		});

		horizontalPanel.add(textBoxForm);
		horizontalPanel.add(datePicker);
		horizontalPanel.add(listBox);
		
		buttonsPanel.add(back);
		buttonsPanel.add(oneMoreButton);
		buttonsPanel.add(next);

		RootPanel.get("mainDiv").add(horizontalPanel);
		RootPanel.get("mainDiv").add(buttonsPanel);
	}

	private void addDiploma() {

		// final AsyncCallback<Void> callback = new AsyncCallback<Void>() {
		// public void onFailure(Throwable caught) {
		//
		// }
		//
		// @Override
		// public void onSuccess(Void result) {
		// // SC.say("Submitted");
		// RootPanel.get("mainDiv").clear();
		// showDefences();
		// }
		// };

		HorizontalPanel buttonHorizontalPanel = new HorizontalPanel();
		HorizontalPanel listsHorizontalPanel = new HorizontalPanel();
		HorizontalPanel typeListsHorizontalPanel = new HorizontalPanel();
		VerticalPanel mainVerticalPanel = new VerticalPanel();

		final TextItem projectNameTextBox = new TextItem();
		projectNameTextBox.setTitle("Project name");
		projectNameTextBox.setWidth(250);
		final TextItem diplomantsNameTextBox = new TextItem();
		diplomantsNameTextBox.setTitle("Diplomants name/s");
		diplomantsNameTextBox.setWidth(250);

		DynamicForm projectNameForm = new DynamicForm();
		projectNameForm.setFields(projectNameTextBox);
		projectNameForm.setWidth(300);
		DynamicForm diplomantsNameForm = new DynamicForm();
		diplomantsNameForm.setFields(diplomantsNameTextBox);
		diplomantsNameForm.setWidth(300);

		final ListBox diplomaLeadersListBox = new ListBox();
		diplomaLeadersListBox.setPixelSize(150, 30);
		final ListBox reviewersListBox = new ListBox();
		reviewersListBox.setPixelSize(150, 30);
		
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
		specialtiesListBox.addItem("Communications");
		specialtiesListBox.setPixelSize(150, 30);

		final ListBox typeListBox = new ListBox();
		typeListBox.setTitle("Software Type");
		typeListBox.addItem("Game/Media");
		typeListBox.addItem("Plug-in/Driver");
		typeListBox.addItem("Web Site/App");
		typeListBox.addItem("Other");
		typeListBox.setEnabled(false);
		typeListBox.setPixelSize(150, 30);

		specialtiesListBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				if (specialtiesListBox.getValue(
						specialtiesListBox.getSelectedIndex()).equals(
						"Software")) {
					typeListBox.setEnabled(true);
				} else {
					typeListBox.setEnabled(false);
				}
			}
		});

		Button submitButton = new Button("Submit");
		submitButton.setWidth(100);
		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (FM.checkEmpty(projectNameTextBox)
						|| FM.checkEmpty(diplomantsNameTextBox)) {
					SC.say("Fill all fields!");
				} else {
					FM.getDiploma(projectNameTextBox.getValueAsString(),
							diplomantsNameTextBox.getValueAsString(),
							diplomaLeadersListBox.getValue(diplomaLeadersListBox.getSelectedIndex()),
							reviewersListBox.getValue(reviewersListBox.getSelectedIndex()),
							specialtiesListBox.getValue(specialtiesListBox.getSelectedIndex()),
							typeListBox.getValue(typeListBox.getSelectedIndex()));
					FM.generateDefences();
					// defenceSvc.saveDefences(FM, callback);
					RootPanel.get("mainDiv").clear();
					showDefences();
				}
			}
		});

		Button oneMoreButton = new Button("One More");
		oneMoreButton.setWidth(100);
		oneMoreButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (FM.checkEmpty(projectNameTextBox)
						|| FM.checkEmpty(diplomantsNameTextBox)) {
					SC.say("Fill all fields!");
				} else {
					FM.getDiploma(projectNameTextBox.getValueAsString(),
							diplomantsNameTextBox.getValueAsString(),
							diplomaLeadersListBox.getValue(diplomaLeadersListBox.getSelectedIndex()),
							reviewersListBox.getValue(reviewersListBox.getSelectedIndex()),
							specialtiesListBox.getValue(specialtiesListBox.getSelectedIndex()),
							typeListBox.getValue(typeListBox.getSelectedIndex()));
					FM.generateDefences();
					RootPanel.get("mainDiv").clear();
					addDiploma();
				}
			}
		});

		Button back = new Button("Back");
		back.setWidth(100);
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
				showDefence("Software");
			}
		});

		Button showHardwareDefences = new Button("Show Hardware");
		showHardwareDefences.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				showDefence("Hardware");
			}
		});

		Button showCommunicationsDefences = new Button("Show Communications");
		showCommunicationsDefences.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				showDefence("Communications");
			}
		});

		Button showAllDefences = new Button("Show All");
		showAllDefences.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				showDefence("All");
			}
		});
		
		Button back = new Button("Back");
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				addDiploma();
			}
		});

		RootPanel.get("mainDiv").add(showSoftwareDefences);
		RootPanel.get("mainDiv").add(showCommunicationsDefences);
		RootPanel.get("mainDiv").add(showHardwareDefences);
		RootPanel.get("mainDiv").add(showAllDefences);
		RootPanel.get("mainDiv").add(back);

	}

	protected void showDefence(String specialtie) {
		// final FunctionalityManager funcM = new FunctionalityManager();
		// final AsyncCallback<FunctionalityManager> callback1 = new
		// AsyncCallback<FunctionalityManager>() {
		// public void onFailure(Throwable caught) {
		//
		// }
		//
		// @Override
		// public void onSuccess(FunctionalityManager result) {
		// funcM.setHardDefences(result.getHardDefences());
		//
		// }
		// };
		// final AsyncCallback<FunctionalityManager> callback2 = new
		// AsyncCallback<FunctionalityManager>() {
		// public void onFailure(Throwable caught) {
		//
		// }
		//
		// @Override
		// public void onSuccess(FunctionalityManager result) {
		// funcM.setNetDefences(result.getNetDefences());
		//
		// }
		// };
		// final AsyncCallback<FunctionalityManager> callback3 = new
		// AsyncCallback<FunctionalityManager>() {
		// public void onFailure(Throwable caught) {
		//
		// }
		//
		// @Override
		// public void onSuccess(FunctionalityManager result) {
		// funcM.setSoftDefences(result.getSoftDefences());
		//
		// }
		// };
		// defenceSvc.getHardDefences(funcM, callback1);
		// defenceSvc.getNetDefences(funcM, callback2);
		// defenceSvc.getSoftDefences(funcM, callback3);
		
		
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		
		Button backToAddDiploma = new Button("Add More");
		backToAddDiploma.setWidth(325);
		backToAddDiploma.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				addDiploma();
			}
		});
		
		Button back = new Button("Back");
		back.setWidth(325);
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				addDiploma();
			}
		});

		buttonsPanel.add(back);
		buttonsPanel.add(backToAddDiploma);
		
		RootPanel.get("mainDiv").add(diplomaListGrid(specialtie));
		RootPanel.get("mainDiv").add(personListGrid(specialtie));
		RootPanel.get("mainDiv").add(buttonsPanel);
	}

	private ListGrid personListGrid(String specialtie) {
		final ListGrid personGrid = new ListGrid();
		personGrid.setWidth(650);
		personGrid.setHeight(224);
		personGrid.setShowAllRecords(true);
//		diplomaGrid.setCanEdit(true);
//		diplomaGrid.setEditEvent(ListGridEditEvent.CLICK);
//		diplomaGrid.setModalEditing(true);

		PersonData pd = new PersonData(FM, specialtie);

		ListGridField nameField = new ListGridField("name", "Project Name");
		ListGridField dateField = new ListGridField("date", "Date");
		personGrid.setFields(new ListGridField[] { nameField, dateField });
		personGrid.setData(pd.getDiplomaRecords());
		
		return personGrid;
	}

	private ListGrid diplomaListGrid(String specialtie) {
		final ListGrid diplomaGrid = new ListGrid();
		diplomaGrid.setWidth(650);
		diplomaGrid.setHeight(224);
		diplomaGrid.setShowAllRecords(true);
//		diplomaGrid.setCanEdit(true);
//		diplomaGrid.setEditEvent(ListGridEditEvent.CLICK);
//		diplomaGrid.setModalEditing(true);

		DiplomaData dd = new DiplomaData(FM, specialtie);

		ListGridField nameField = new ListGridField("name", "Project Name");
		ListGridField diplomantsField = new ListGridField("diplomants",
				"Diplomants");
		ListGridField leaderField = new ListGridField("leader", "Leader");
		ListGridField reviewerField = new ListGridField("reviewer", "Reviewer");
		ListGridField typeField = new ListGridField("type", "Type");
		typeField.setWidth(130);
		ListGridField dateField = new ListGridField("date", "Date");
		diplomaGrid.setFields(new ListGridField[] { nameField, diplomantsField,
				leaderField, reviewerField, typeField, dateField });
		diplomaGrid.setData(dd.getDiplomaRecords());
		
		return diplomaGrid;
	}
}