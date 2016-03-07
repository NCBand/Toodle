package ru.ncband.web.shared;

import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.rpc.core.java.lang.String_CustomFieldSerializer;

/**
 * Created by roman on 07.03.2016.
 */
public class RegularExpressions implements RegularExpressionsImpl{

    static final String EMAIL = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    public static final String PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";
    public static final String REQUIREMENT_FOR_PASSWORD = "Password contains uppercase and lowercase letters, numbers";
    static final String LOGIN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$"; // login contains 2-20 characters, which can be letters or numbers. the first character must be a letter

    public boolean test(String pattern, String testString) {
        RegExp regExp = RegExp.compile(pattern);
        return regExp.test(testString);
    }
}
