package ru.ncband.web.shared;

import com.google.gwt.regexp.shared.RegExp;

public class RegularExpressions {
    private static final String NAME = "^[a-zA-Z]+$";
    private static final String REQUIREMENT_FOR_NAME = "contains uppercase and lowercase letters";
    private static final String PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";
    private static final String REQUIREMENT_FOR_PASSWORD = "Password contains uppercase and lowercase letters, numbers";
    private static final String LOGIN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
    private static final String REQUIREMENT_FOR_LOGIN = "login contains 2-20 characters, which can be letters or numbers. the first character must be a letter";

    public String getRequirementForName() {
        return REQUIREMENT_FOR_NAME;
    }

    public String getNAME() {
        return NAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getRequirementForPassword() {
        return REQUIREMENT_FOR_PASSWORD;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public String getRequirementForLogin() {
        return REQUIREMENT_FOR_LOGIN;
    }

    public boolean test(String pattern, String testString) {
        RegExp regExp = RegExp.compile(pattern);
        return regExp.test(testString);
    }
}
