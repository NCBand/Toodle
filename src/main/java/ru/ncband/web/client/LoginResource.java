package ru.ncband.web.client;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Created by rewweRrr on 16.02.2016.
 */
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
