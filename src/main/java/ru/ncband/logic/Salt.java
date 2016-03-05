package ru.ncband.logic;

import org.bouncycastle.jcajce.provider.digest.SHA3.Digest256;
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;

import java.security.MessageDigest;

public class Salt {
    public static String salting(Integer salt, String password){
        return sha3(salt.toString()+password);
    }

    public static String createSalt(){
        return null;
    }

    public static String sha3(final String input) {
        final DigestSHA3 sha3 = new Digest256();

        sha3.update(input.getBytes());
        return hashToString(sha3);
    }

    public static String hashToString(MessageDigest hash) {
        return hashToString(hash.digest());
    }

    public static String hashToString(byte[] hash) {
        StringBuffer buff = new StringBuffer();

        for (byte b : hash) {
            buff.append(String.format("%02x", b & 0xFF));
        }

        return buff.toString();
    }
}
