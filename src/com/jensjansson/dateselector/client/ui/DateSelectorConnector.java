package com.jensjansson.dateselector.client.ui;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.jensjansson.dateselector.DateSelector;
import com.jensjansson.dateselector.client.ui.VDateSelector.DateChangeHandler;
import com.vaadin.terminal.gwt.client.VConsole;
import com.vaadin.terminal.gwt.client.communication.RpcProxy;
import com.vaadin.terminal.gwt.client.communication.ServerRpc;
import com.vaadin.terminal.gwt.client.communication.StateChangeEvent;
import com.vaadin.terminal.gwt.client.communication.StateChangeEvent.StateChangeHandler;
import com.vaadin.terminal.gwt.client.ui.AbstractComponentConnector;
import com.vaadin.terminal.gwt.client.ui.Connect;

@Connect(DateSelector.class)
public class DateSelectorConnector extends AbstractComponentConnector implements
        StateChangeHandler {
    private DateSelectorRPC rpc;

    public interface DateSelectorRPC extends ServerRpc {
        public void setDate(IntDate date);
    }

    @Override
    public void init() {
        rpc = RpcProxy.create(DateSelectorRPC.class, this);
        getWidget().setDateChangeHandler(new DateChangeHandler() {
            public void dateChanged(IntDate date, VDateSelector dateSelector) {
                VConsole.log("In Connector");
                VConsole.log(date.toString());

                rpc.setDate(date);
            }
        });
    }

    //
    // /**
    // * Called whenever an update is received from the server
    // */
    // @Override
    // public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
    // super.updateFromUIDL(uidl, client);
    // if (!isRealUpdate(uidl)) {
    // return;
    // }
    // IntDate date = getState().getDate();
    // if (date != null && date.getDay() != 0 && date.getMonth() != 0
    // && date.getYear() != 0) {
    // getWidget().setValue(date);
    // }
    // }

    // @Override
    // protected ComponentState createState() {
    // return GWT.create(DateSelectorState.class);
    // }

    @Override
    public DateSelectorState getState() {
        return (DateSelectorState) super.getState();
    }

    @Override
    protected Widget createWidget() {
        return GWT.create(VDateSelector.class);
    }

    @Override
    public VDateSelector getWidget() {
        return (VDateSelector) super.getWidget();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);

        IntDate date = getState().getDate();
        getWidget().setDate(date);
    }
}
