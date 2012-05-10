package org.elsys.salvation.client;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.UnavailableException;

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
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.DateRangeItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

public class Salvation implements EntryPoint {

	private Date startDate = new Date();
	private Date endDate = new Date();
	private HashSet<Person> Leaders = new HashSet<Person>();
	private HashSet<Person> Reviewers = new HashSet<Person>();
	private HashSet<SoftwareWork> SoftwareWorks = new HashSet<SoftwareWork>();
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

		final Button nextButton = new Button("Next");

		nextButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				startDate = dateRange.getStartDate();
				endDate = dateRange.getEndDate();
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

		// dateRangeItem.addChangeHandler(new ChangeHandler(){
		// public void onChange(ChangeEvent event) {
		// if(dateRange.getStartDate().equals(dateRange.getEndDate())
		// && dateRange.getStartDate().equals(RelativeDate.YESTERDAY)){
		// nextButton.setDisabled(true);
		// }
		// }
		// });

		DynamicForm form = new DynamicForm();
		form.setItems(dateRangeItem);
		layout.addMember(form);
		layout.addMember(nextButton);
		layout.addMember(backButton);

		layout.draw();
		RootPanel.get("mainDiv").add(layout);

	}

	private void addPerson() {
		final HashSet<Date> dates = new HashSet<Date>();

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
				getPerson(dates, textBox, listBox);
				RootPanel.get("mainDiv").clear();
				addPerson();
			}
		});

		Button back = new Button("Back");
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("mainDiv").clear();
				Leaders.clear();
				Reviewers.clear();
				SoftwareWorks.clear();
				HardwareWorks.clear();
				NetWorks.clear();
				onModuleLoad();
			}
		});

		Button next = new Button("Next");
		next.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				getPerson(dates, textBox, listBox);
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

	private void getPerson(HashSet<Date> set, TextItem box, ListBox listBox) {
		if (listBox.getSelectedIndex() == 0) {
			Person person = new Person(box.getValueAsString(), set);
			Leaders.add(person);
		} else if (listBox.getSelectedIndex() == 1) {
			Person person = new Person(box.getValueAsString(), set);
			Reviewers.add(person);
		} else if (listBox.getSelectedIndex() == 2) {
			Person person = new Person(box.getValueAsString(), set);
			Leaders.add(person);
			Reviewers.add(person);
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
		final ListBox reviewersListBox = new ListBox();

		Iterator<Person> i = Leaders.iterator();
		while (i.hasNext()) {
			diplomaLeadersListBox.addItem(i.next().getName());

		}

		Iterator<Person> k = Reviewers.iterator();
		while (k.hasNext()) {
			reviewersListBox.addItem(k.next().getName());

		}

		DynamicForm specialtieForm = new DynamicForm();

		final ComboBoxItem specialtiesComboBox = new ComboBoxItem();
		specialtiesComboBox.setTitle("Specialties");
		specialtiesComboBox.setType("comboBox");
		specialtiesComboBox
				.setValueMap("Software", "Hardware", "Communication");

		final ComboBoxItem typeComboBox = new ComboBoxItem();
		typeComboBox.setTitle("Software Type");
		typeComboBox.setType("comboBox");
		typeComboBox.setValueMap("Game", "Media", "Plug-in/Driver", "Web App",
				"Other");
		typeComboBox.setDisabled(true);

		specialtiesComboBox.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(
					com.smartgwt.client.widgets.form.fields.events.ChangedEvent event) {
				if (specialtiesComboBox.getValueAsString().equals("Software")) {
					typeComboBox.setDisabled(false);
				} else {
					typeComboBox.setDisabled(true);
				}
			}
		});

		Button submitButton = new Button("Submit");
		Button oneMoreButton = new Button("One More");
		oneMoreButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// getDiploma(projectNameTextBox,diplomantsNameTextBox,diplomaLeadersListBox,
				// reviewersListBox,specialtiesComboBox, typeComboBox);
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

		specialtieForm.setFields(specialtiesComboBox, typeComboBox);

		mainVerticalPanel.add(projectNameForm);
		mainVerticalPanel.add(diplomantsNameForm);
		mainVerticalPanel.add(specialtieForm);

		buttonHorizontalPanel.add(back);
		buttonHorizontalPanel.add(oneMoreButton);
		buttonHorizontalPanel.add(submitButton);

		listsHorizontalPanel.add(diplomaLeadersListBox);
		listsHorizontalPanel.add(reviewersListBox);

		mainVerticalPanel.add(listsHorizontalPanel);
		mainVerticalPanel.add(buttonHorizontalPanel);

		RootPanel.get("mainDiv").add(mainVerticalPanel);
	}

	protected void getDiploma(TextItem projectName, TextItem diplomants,
			ListBox diplomaLeader, ListBox diplomaReviewer,
			ComboBoxItem specialtie, ComboBoxItem type) {

		Person leader = new Person();
		Person reviewer = new Person();

		Iterator<Person> i = Leaders.iterator();
		while (i.hasNext()) {
			if (i.next()
					.getName()
					.equals(diplomaLeader.getValue(diplomaLeader
							.getSelectedIndex()))) {
				leader = i.next();
			}
		}

		Iterator<Person> k = Reviewers.iterator();
		while (k.hasNext()) {
			if (k.next()
					.getName()
					.equals(diplomaReviewer.getValue(diplomaReviewer
							.getSelectedIndex()))) {
				reviewer = k.next();
			}
		}

		if (specialtie.getValueAsString().equalsIgnoreCase("Software")) {
			SoftwareWorks.add(new SoftwareWork(projectName.getValueAsString(),
					diplomants.getValueAsString(), leader, reviewer, type
							.getValueAsString()));

		} else if (specialtie.getValueAsString().equalsIgnoreCase("Hardware")) {
			HardwareWorks.add(new DiplomaWork(projectName.getValueAsString(),
					diplomants.getValueAsString(), leader, reviewer));

		} else if (specialtie.getValueAsString().equalsIgnoreCase(
				"Communication")) {
			NetWorks.add(new DiplomaWork(projectName.getValueAsString(),
					diplomants.getValueAsString(), leader, reviewer));
		}

	}

	private void generateDefences() {

		Iterator<DiplomaWork> i = HardwareWorks.iterator();
		Defence def = new Defence(HardwareWorks);

		Person firstPerson = i.next().getLeader();
		Person secondPerson = i.next().getLeader();
		Person thirdPerson = i.next().getLeader();
		Person forthPerson = i.next().getLeader();

		/*
		 * while (!HardwareWorks.isEmpty()) {
		 * 
		 * if (firstPerson.getAvailableDates() == secondPerson
		 * .getAvailableDates()) { def.setDay(secondPerson.getAvailableDates());
		 * secondPerson.add(); } if (firstPerson.getAvailableDates() ==
		 * thirdPerson .getAvailableDates()) { secondPerson.add(); } if
		 * (firstPerson.getAvailableDates() == forthPerson .getAvailableDates())
		 * { secondPerson.add(); } }
		 */

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