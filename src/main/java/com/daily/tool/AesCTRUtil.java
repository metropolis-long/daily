package com.daily.tool;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AesCTRUtil {
    private final static String sourceStr = "1234567890abcdef";
    private final static String key = "XLMf5R6brONG0INa";
    private final static String counter = "OJLRN0wzvE7cTZn2";

    public static void main(String[] args) {

//        System.out.println("Begin:" + System.currentTimeMillis());
//        System.out.println("sourceStr:" + sourceStr);

        //字符串加密机密
//        byte[] targetByteArr = encryptCTR(sourceStr.getBytes(), key, counter);
//        System.out.println("targetStr:" + new String(targetByteArr));
//        byte[] target_source = decryptCTR(targetByteArr, key, counter);
//        System.out.println("target_source:" + new String(target_source));

        //文件加密解密
//        String inFilePath = "E:\\Test\\ase\\a.json";
//        String outFilePath = "E:\\Test\\ase\\b.json";
//        String inFilePath = "D:\\temp\\lq_match\\examZip\\exam_87.bin";
//        String outFilePath = "D:\\2.zip";
//        String inFilePath = "E:\\Test\\ase\\big-test-encode-golang.rar";
//        String outFilePath = "E:\\Test\\ase\\big-test-decode-uJava4Golang.rar";
//        decryptCTR(inFilePath, outFilePath);

//        String encodeStr = encryptCTRAndBase64String(sourceStr);
//        System.out.println("encodeStr:"+encodeStr);
//        String decodeStr = decryptCTRAndBase64String(encodeStr);
//        System.out.println("decodeStr:"+decodeStr);
//
//        System.out.println("End:" + System.currentTimeMillis());
    }

    public static void decryptCTR(String inFilePath, String outFilePath) {
        File inFile = new File(inFilePath);
        File outFile = new File(outFilePath);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            byte[] buffer = new byte[1024];
            bis = new BufferedInputStream(new FileInputStream(inFile));
            bos = new BufferedOutputStream(new FileOutputStream(outFile));
            int read;
            while ((read = bis.read(buffer)) != -1) {
                byte[] coutent = Arrays.copyOfRange(buffer, 0, read);
                byte[] result = decryptCTR(coutent, key, counter);
                if (result != null)
                    bos.write(result);
            }
            bos.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) bos.close();
                if (bis != null) bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("解密 End:" + outFilePath);

        }
    }

    public static void encryptCTR(String inFilePath, String outFilePath) {
        File inFile = new File(inFilePath);
        File outFile = new File(outFilePath);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            byte[] buffer = new byte[1024];

            bis = new BufferedInputStream(new FileInputStream(inFile));
            bos = new BufferedOutputStream(new FileOutputStream(outFile));

            int read;
            while ((read = bis.read(buffer)) != -1) {

                byte[] coutent = Arrays.copyOfRange(buffer, 0, read);
                byte[] result = encryptCTR(coutent, key, counter);
                if (result != null)
                    bos.write(result);
            }
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) bos.close();
                if (bis != null) bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("加密End:" + outFilePath);

        }
    }

    private static byte[] decryptCTR(byte[] cipherByteArr, String key, String counter) {
        try {
            // 获取解密密钥
            byte[] keyBytes = key.getBytes();
            Key keySpec = new SecretKeySpec(keyBytes, "AES");
            // 获取初始矢量
            byte[] ivBytes = counter.getBytes();
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            // 获取Cipher实例
            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
            // 初始化Cipher实例，设置执行模式，解密密钥和初始计数器
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            // 解密
//            byte[] cipherTextBytes = hex2byte(cipherByteArr);
            return cipher.doFinal(cipherByteArr);
        } catch (Exception e) {
            System.out.println("CTR解密异常");
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] encryptCTR(byte[] cipherByteArr, String key, String counter) {
        try {
            // 获取解密密钥
            byte[] keyBytes = key.getBytes();
            Key keySpec = new SecretKeySpec(keyBytes, "AES");
            // 获取初始矢量
            byte[] ivBytes = counter.getBytes();
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            // 获取Cipher实例
            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
            // 初始化Cipher实例，设置执行模式，解密密钥和初始计数器
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

            // 解密
//            byte[] cipherTextBytes = hex2byte(cipherByteArr);
            return cipher.doFinal(cipherByteArr);
            // 返回明文
        } catch (Exception e) {
            System.out.println("CTR解密异常");
            e.printStackTrace();
        }
        return null;
    }


    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    /**
     * Base64Url 和 AesCTR解密.
     *
     * @param source
     * @return
     */
    public static String decryptCTRAndBase64String(String source) {

        byte[] decodeBase64Url = Base64.getUrlDecoder().decode(source);

        byte[] decodeAesCtr = decryptCTR(decodeBase64Url, key, counter);

        if (decodeAesCtr != null) {
            return new String(decodeAesCtr);
        }
        return null;
    }

    /**
     * AesCTR 和 Base64Url加密.
     *
     * @param source
     * @return
     */
    public static String encryptCTRAndBase64String(String source) {
        if (source == null)
            return null;

        byte[] encodeAesCtr = decryptCTR(source.getBytes(), key, counter);
        if (encodeAesCtr == null) {
            return null;
        }
        byte[] encodeBase64Url = Base64.getUrlEncoder().encode(encodeAesCtr);
        return new String(encodeBase64Url);

    }
}
