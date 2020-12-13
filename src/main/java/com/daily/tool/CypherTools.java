package com.daily.tool;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class CypherTools {
    /**
     * sha1加密方法.
     *
     * @param inStr 加密字符串
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String shaEncode(final String inStr) {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (final Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = new byte[0];
        try {
            byteArray = inStr.getBytes("UTF-8");
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final byte[] md5Bytes = sha.digest(byteArray);
        final StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            final int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args) {
        System.out.println(shaEncode("123456"));
    }
}
