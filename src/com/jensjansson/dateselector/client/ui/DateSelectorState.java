package com.jensjansson.dateselector.client.ui;

import com.vaadin.terminal.gwt.client.ComponentState;

public class DateSelectorState extends ComponentState {
    private IntDate date = new IntDate(-1, -1, -1);

    public IntDate getDate() {
        return date;
    }

    public void setDate(IntDate date) {
        this.date = date;
    }
}