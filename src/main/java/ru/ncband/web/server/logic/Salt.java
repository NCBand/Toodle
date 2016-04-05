package ru.ncband.web.server.logic;

import org.bouncycastle.jcajce.provider.digest.SHA3.Digest256;
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;

import java.security.MessageDigest;

public class Salt {
    public static String salting(String salt, String password){
        String res = sha3(salt+password);
        if(res.length() > 30){
            res = res.substring(0,30);
        }
        return res;
    }

    public static String sha3(final String input) {
        final DigestSHA3 sha3 = new Digest256();
        sha3.update(input.getBytes());
        return hashToString(sha3);
    }

    private static String hashToString(MessageDigest hash) {
        return hashToString(hash.digest());
    }

    private static String hashToString(byte[] hash) {
        StringBuilder buff = new StringBuilder();

        for (byte b : hash) {
            buff.append(String.format("%02x", b & 0xFF));
        }
        return buff.toString();
    }
}
