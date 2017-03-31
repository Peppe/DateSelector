package com.jensjansson.dateselector;

import com.vaadin.data.HasValue;
import com.vaadin.server.Page;
import com.vaadin.shared.Registration;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateSelector extends CssLayout implements HasValue<LocalDate> {

    private NativeSelect<String> year = new NativeSelect<>();
    private NativeSelect<String> month = new NativeSelect<>();
    private NativeSelect<String> day = new NativeSelect<>();
    private int startYear = -1;
    private int endYear = -1;
    private int yearValue = -1;
    private int monthValue = -1;
    private int dayValue = -1;
    private LocalDate value = null;

    public DateSelector() {
        addStyleName("v-date-selector");
        Page.getCurrent().getStyles().add(".v-date-selector > div {display: inline-block;}");
        Page.getCurrent().getStyles().add(".v-date-selector > .v-label {padding: 0 5px;}");
        int currentYear = LocalDate.now().getYear();
        startYear = currentYear-150;
        endYear = currentYear+100;
        addComponents(year, new Label("-"), month, new Label("-"), day);
        generateOptions();
        year.addValueChangeListener(e -> yearChanged());
        month.addValueChangeListener(e -> monthChanged());
        day.addValueChangeListener(e -> dayChanged());
    }

    public void generateOptions(){
        List<String> years = new ArrayList<String>();
        years.add("YYYY");
        for(int i = endYear; i >= startYear; i--){
            years.add(String.valueOf(i));
        }
        year.setItems(years);
        year.setValue("YYYY");
        year.setEmptySelectionAllowed(false);
        List<String> months = new ArrayList<String>();
        months.add("MM");
        for(int i = 1; i <= 12; i++){
            months.add(String.valueOf(i));
        }
        month.setItems(months);
        month.setValue("MM");
        month.setEmptySelectionAllowed(false);
        List<String> days = new ArrayList<String>();
        days.add("DD");
        for(int i = 1; i <= 31; i++){
            days.add(String.valueOf(i));
        }
        day.setItems(days);
        day.setValue("DD");
        day.setEmptySelectionAllowed(false);
    }

    private void yearChanged(){
        System.out.println("Year changing..");
        try {
            yearValue = Integer.parseInt(this.year.getValue());
            buildValue();
        } catch (NumberFormatException e){
        }
    }


    private void monthChanged(){
        try {
            monthValue = Integer.parseInt(this.month.getValue());
            buildValue();
        } catch (NumberFormatException e){
        }
    }

    private void dayChanged(){
        try {
            dayValue = Integer.parseInt(this.day.getValue());
            buildValue();
        } catch (NumberFormatException e){
        }
    }


    public void setYearRange(int startYear, int endYear){
        if(startYear<=endYear){
            this.startYear = startYear;
            this.endYear = endYear;
            generateOptions();
        } else {
            throw new IllegalArgumentException("startYear can't be later than endYear");
        }
    }

    @Override
    public void setValue(LocalDate date) {
        yearValue = date.getYear();
        monthValue = date.getMonthValue();
        dayValue = date.getDayOfMonth();

        year.setValue(String.valueOf(date.getYear()));
        month.setValue(String.valueOf(date.getMonthValue()));
        day.setValue(String.valueOf(date.getDayOfMonth()));
    }

    private LocalDate buildValue(){
        System.out.println("Building new value");
        if(yearValue != -1 && monthValue != -1 && dayValue != -1){
            LocalDate oldValue = value;
            LocalDate newValue = LocalDate.of(yearValue, monthValue, dayValue);
            if(!newValue.equals(oldValue)){
                value = newValue;
                this.fireEvent(new ValueChangeEvent<LocalDate>(this,oldValue, true));
            }
        }
        return null;
    }

    @Override
    public LocalDate getValue() {
        return value;
    }

    @Override
    public void setRequiredIndicatorVisible(boolean b) {
    }

    @Override
    public boolean isRequiredIndicatorVisible() {
        return false;
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        year.setReadOnly(readOnly);
        month.setReadOnly(readOnly);
        day.setReadOnly(readOnly);
    }

    @Override
    public boolean isReadOnly() {
        return year.isReadOnly();
    }

    @Override
    public Registration addValueChangeListener(ValueChangeListener valueChangeListener) {
        return this.addListener(ValueChangeEvent.class, valueChangeListener, ValueChangeListener.VALUE_CHANGE_METHOD);
    }
}
