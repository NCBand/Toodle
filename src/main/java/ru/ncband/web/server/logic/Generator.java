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

    public String createNum(int sz){
        byte[] num = new byte[sz];
        random.nextBytes(num);
        return new String(num);
    }

    public String createNum(){
        byte[] num = new byte[32];
        random.nextBytes(num);
        return new String(num);
    }
}
