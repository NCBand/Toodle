package ru.ncband.web.shared.properties;

import com.google.gwt.i18n.client.DateTimeFormat;

public class BasicProperty {
    public static String fault(){
        return "fault";
    }

    public static String done(){
        return "done";
    }

    public static String dateSeparator(){
        return "/";
    }

    public static DateTimeFormat dateFormat(){
        return DateTimeFormat.getFormat("dd"+ BasicProperty.dateSeparator()+
                "MM"+ BasicProperty.dateSeparator() +
                "yyyy");
    }
}
