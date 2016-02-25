package ru.ncband.web.binders.login;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface LoginResource extends ClientBundle {
    public interface MyCss extends CssResource {

        String greenText();

        String blackText();

        String redText();

        String background();

        String loginButton();

        String box();
    }

    @Source("myStyle.css")
    MyCss style();
}
