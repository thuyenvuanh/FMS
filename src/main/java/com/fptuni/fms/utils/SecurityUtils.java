package com.fptuni.fms.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.spec.KeySpec;

public class SecurityUtils {

    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int HASH_ITERATIONS = 1000;
    private static final int HASH_BYTES = 128;

    public static String createHash(String target, String salt) throws Exception {

        byte[] saltBytes = fromHex(salt);
        byte[] hashed = pbkdf2(target.toCharArray(), saltBytes);

        return toHex(hashed);
    }

    public static boolean validateHash(String target, String salt, String goodHash) throws Exception {

        byte[] saltBytes = fromHex(salt);
        byte[] hashed = pbkdf2(target.toCharArray(), saltBytes);

        return toHex(hashed).equals(goodHash);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt) throws Exception {

        KeySpec spec = new PBEKeySpec(password, salt, SecurityUtils.HASH_ITERATIONS, SecurityUtils.HASH_BYTES);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);

        return factory.generateSecret(spec).getEncoded();
    }

    private static byte[] fromHex(String hex) {

        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    private static  String toHex(byte[] binary) {
        BigInteger bigInteger = new BigInteger(1, binary);
        String hex = bigInteger.toString(16);
        int paddingLength = (binary.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else return hex;
    }

}
