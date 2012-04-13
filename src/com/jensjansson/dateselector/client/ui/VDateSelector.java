package com.jensjansson.dateselector.client.ui;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;

public class VDateSelector extends FlowPanel implements ChangeHandler {

    /** Set the CSS class name to allow styling. */
    public static final String CLASSNAME = "v-dateselector";
    private IntDate date;
    private ListBox day;
    private ListBox month;
    private ListBox year;

    private DateChangeHandler dateChangeHandler = null;

    public interface DateChangeHandler {
        public void dateChanged(IntDate value, VDateSelector dateSelector);
    }

    public VDateSelector() {
        day = new ListBox();
        day.addStyleName("day");
        day.addItem("Day");
        for (int i = 1; i <= 31; i++) {
            day.addItem(i + "");
        }
        month = new ListBox();
        month.addItem("Month");
        month.addStyleName("month");
        for (int i = 1; i <= 12; i++) {
            month.addItem(i + "");
        }
        year = new ListBox();
        year.addItem("Year");
        year.addStyleName("year");
        for (int i = 1901; i <= 2012; i++) {
            year.addItem(i + "");
        }
        day.addChangeHandler(this);
        month.addChangeHandler(this);
        year.addChangeHandler(this);
        add(day);
        add(month);
        add(year);
        setStyleName(CLASSNAME);
    }

    public IntDate getDate() {
        return date;
    }

    public void setDate(IntDate date) {
        this.date = date;
        int day = date.getDay();
        int month = date.getMonth();
        int year = date.getYear();
        year = year - 1900;
        if (day < 0 || day > 31) {
            day = 0;
        }
        if (month < 0 || month > 12) {
            month = 0;
        }
        if (year < 0 || year > 100) {
            year = 0;
        }
        this.day.setItemSelected(day, true);
        this.month.setItemSelected(month, true);
        this.year.setItemSelected(year, true);
    }

    public void onChange(ChangeEvent event) {
        if (dateChangeHandler != null) {
            int dayInt = day.getSelectedIndex();
            int monthInt = month.getSelectedIndex();
            int yearInt = year.getSelectedIndex();
            if (dayInt != 0 && monthInt != 0 && yearInt != 0) {
                date = new IntDate(dayInt, monthInt, yearInt + 1900);
                dateChangeHandler.dateChanged(date, VDateSelector.this);
            }
        }
    }

    public DateChangeHandler getDateChangeHandler() {
        return dateChangeHandler;
    }

    public void setDateChangeHandler(DateChangeHandler dateChangeHandler) {
        this.dateChangeHandler = dateChangeHandler;
    }
}
