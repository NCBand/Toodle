package ru.ncband.web.shared;

import com.google.gwt.regexp.shared.RegExp;

public class RegularExpressions {
    public static final String NAME = "^[a-zA-Z]+$";
    public static final String REQUIREMENT_FOR_NAME = "contains uppercase and lowercase letters";
    public static final String PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";
    public static final String REQUIREMENT_FOR_PASSWORD = "Password contains uppercase and lowercase letters, numbers";
    public static final String LOGIN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
    public static final String REQUIREMENT_FOR_LOGIN = "login contains 2-20 characters, which can be letters or numbers. the first character must be a letter";

    public static boolean test(String pattern, String testString) {
        RegExp regExp = RegExp.compile(pattern);
        return regExp.test(testString);
    }
}
