package ru.ncband.web.client.widgets.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;

import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ncband.web.client.events.LogOutEvent;
import ru.ncband.web.client.services.MessageService;
import ru.ncband.web.shared.Property;
import ru.ncband.web.shared.classes.Messages;

import java.util.Date;

public class UiMainSpace extends Composite {
    interface UiMainSpaceUiBinder extends UiBinder<HTMLPanel, UiMainSpace> {
    }

    private static UiMainSpaceUiBinder ourUiBinder = GWT.create(UiMainSpaceUiBinder.class);
    private EventBus eventBus = null;

    @UiField
    VerticalPanel left;

    @UiField
    VerticalPanel right;

    @UiHandler("exit")
    void doExit(ClickEvent event) {
        LogOutEvent logOutEvent = new LogOutEvent();
        eventBus.fireEvent(logOutEvent);
    }

    @UiHandler("tasks")
    void chooseTask(ClickEvent event) {

    }

    @UiHandler("settings")
    void changeSettings(ClickEvent event){

    }


    public UiMainSpace(EventBus bus) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.eventBus = bus;

        DatePicker datePicker = new DatePicker();
        final Label text_date = new Label();
        Label message_tittle = new Label();
        message_tittle.setText("Your messages:");

        datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
                @Override
                public void onValueChange(ValueChangeEvent<Date> event) {
                    Date date = event.getValue();
                    String dateString = DateTimeFormat.getFormat("MM"+ Property.dateSeparator()+
                                                                "dd"+ Property.dateSeparator() +
                                                                "yyyy").format(date);
                    text_date.setText(dateString);

                    MessageService service = GWT.create(MessageService.class);
                    service.getMessages(dateString, new MethodCallback<Messages>() {
                        @Override
                        public void onFailure(Method method, Throwable throwable) {

                        }

                        @Override
                        public void onSuccess(Method method, Messages messages) {

                        }
                    });
                }
            });
        datePicker.setValue(new Date(), true);

        right.setSpacing(10);
        right.add(text_date);
        right.add(datePicker);
        right.add(message_tittle);
    }
}