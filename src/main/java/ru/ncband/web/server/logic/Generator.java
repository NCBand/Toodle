package ru.ncband.web.server.logic;

import java.security.SecureRandom;
import java.util.Random;

public class Generator {
    private static Random random = null;
    private static Generator ourInstance = new Generator();

    public static Generator getInstance() {
        return ourInstance;
    }

    private Generator() {
        random = new SecureRandom();
    }

    public int createNumInt(){
        return random.nextInt();
    }
}
