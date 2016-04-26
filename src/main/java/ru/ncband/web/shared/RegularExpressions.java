package ru.ncband.web.shared;

import com.google.gwt.regexp.shared.RegExp;

public class RegularExpressions {
    public static final String NAME = "^[a-zA-Z]+$";
    public static final String REQUIREMENT_FOR_NAME = "Contains uppercase and lowercase letters";

    public static final String PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";
    public static final String REQUIREMENT_FOR_PASSWORD = "Password contains uppercase and lowercase letters, numbers";

    public static final String LOGIN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
    public static final String REQUIREMENT_FOR_LOGIN = "Login contains 2-20 characters, which can be letters or numbers. the first character must be a letter";

    public static final String AGE = "^[1-9]{1}[0-9]+$";
    public static final String REQUIREMENT_FOR_AGE = "Age contains numbers";

    public static final String EMAIL = "^[^@\";:\'\\|=+]{5,}@[a-z]{3,}.[a-z]{2,3}";
    public static final String REQUIREMENT_FOR_EMAIL = "Invalid e-mail";

    public static boolean test(String pattern, String testString) {
        RegExp regExp = RegExp.compile(pattern);
        return regExp.test(testString);
    }
}
