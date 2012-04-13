package com.jensjansson.dateselector;

import com.jensjansson.dateselector.client.ui.DateSelectorConnector.DateSelectorRPC;
import com.jensjansson.dateselector.client.ui.DateSelectorState;
import com.jensjansson.dateselector.client.ui.IntDate;
import com.vaadin.terminal.gwt.client.ComponentState;
import com.vaadin.terminal.gwt.server.RpcTarget;
import com.vaadin.ui.AbstractComponent;

/**
 * Server side component for the VDateSelector widget.
 */
public class DateSelector extends AbstractComponent implements RpcTarget {

    IntDate value = new IntDate(-1, -1, -1);
    private ValueChangeHandler valueChangeHandler = null;
    private DateSelectorRPC rpc = new DateSelectorRPC() {

        public void setDate(IntDate date) {
            System.out.println(date);
            if (date == null) {
                value = null;
                if (valueChangeHandler != null) {
                    valueChangeHandler.valueChanged(value, DateSelector.this);
                }
            } else if (!date.equals(value)) {
                value = date;
                if (valueChangeHandler != null) {
                    valueChangeHandler.valueChanged(value, DateSelector.this);
                }
            }
        }
    };

    public interface ValueChangeHandler {
        public void valueChanged(IntDate value, DateSelector dateSelector);
    }

    public DateSelector() {
        registerRpc(rpc);
    }

    @Override
    public DateSelectorState getState() {
        return (DateSelectorState) super.getState();
    }

    @Override
    protected ComponentState createState() {
        return new DateSelectorState();
    }

    public IntDate getValue() {
        return value;
    }

    public void setValue(IntDate value) {
        this.value = value;
        getState().setDate(value);
        requestRepaint();
    }

    public ValueChangeHandler getValueChangeHandler() {
        return valueChangeHandler;
    }

    public void setValueChangeHandler(ValueChangeHandler valueChangeHandler) {
        this.valueChangeHandler = valueChangeHandler;
    }
}
