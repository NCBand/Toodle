package ru.ncband.web.client.widgets.menu.calendar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import java.util.Date;


public class UiCalendar extends Composite {
    interface UiCalendarUiBinder extends UiBinder<HTMLPanel, UiCalendar> {
    }

    private static UiCalendarUiBinder ourUiBinder = GWT.create(UiCalendarUiBinder.class);

    @UiField
    VerticalPanel field;

    private DatePicker datePicker;
    private Label text;

    public UiCalendar() {
        initWidget(ourUiBinder.createAndBindUi(this));
        datePicker = new DatePicker();
        text = new Label();

        datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                Date date = event.getValue();
                String dateString =
                        DateTimeFormat.getFormat("MM/dd/yyyy").format(date);
                text.setText(dateString);
            }
        });
        datePicker.setValue(new Date(), true);

        field.setSpacing(10);
        field.add(text);
        field.add(datePicker);
    }
}