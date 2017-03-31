package com.jensjansson.dateselector;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.addonhelpers.AbstractTest;

public class BasicDateSelectorUsageUI extends AbstractTest {

    @Override
    public Component getTestComponent() {
        DateSelector dateSelector = new DateSelector();
        dateSelector.setCaption("Select a date");
        dateSelector.addValueChangeListener(e -> {
            Notification.show(dateSelector.getValue().toString());
        });
        VerticalLayout layout = new VerticalLayout(dateSelector);
        return layout;
    }

}
