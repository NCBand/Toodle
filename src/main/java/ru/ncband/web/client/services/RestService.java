package ru.ncband.web.client.services;

import com.google.gwt.core.client.GWT;

/**
 * Created by roman on 18.03.2016.
 */
public class RestService {

    private static final UserService userService = GWT.create(UserService.class);

    public static UserService getInstance(){
        return userService;
    }
}
