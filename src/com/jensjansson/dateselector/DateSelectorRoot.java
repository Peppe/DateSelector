package com.jensjansson.dateselector;

import java.util.Random;

import com.jensjansson.dateselector.DateSelector.ValueChangeHandler;
import com.jensjansson.dateselector.client.ui.IntDate;
import com.vaadin.terminal.WrappedRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Root;
import com.vaadin.ui.VerticalLayout;

public class DateSelectorRoot extends Root {

    private DateSelector date;

    @Override
    protected void init(WrappedRequest request) {
        VerticalLayout view = new VerticalLayout();
        view.setMargin(true);
        setCaption("Awesome widget creation platform");
        view.addComponent(new Label("Foo"));
        setContent(view);
        final Label label = new Label();
        date = new DateSelector();
        date.setValueChangeHandler(new ValueChangeHandler() {

            public void valueChanged(IntDate value, DateSelector dateSelector) {
                if (value != null) {
                    label.setValue("Date selected: " + value.toString());
                }
            }
        });
        view.addComponent(date);
        view.addComponent(label);
        final Label buttonLabel = new Label();
        Button button = new Button("Change to a random date",
                new Button.ClickListener() {

                    public void buttonClick(ClickEvent event) {
                        Random generator = new Random();
                        int day = generator.nextInt(30) + 1;
                        int month = generator.nextInt(12) + 1;
                        int year = generator.nextInt(100) + 1900;
                        date.setValue(new IntDate(day, month, year));
                        label.setValue("Date selected: " + date.toString());
                    }
                });
        view.addComponent(button);
        view.addComponent(buttonLabel);
    }
}