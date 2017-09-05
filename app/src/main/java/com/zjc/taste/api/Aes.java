package com.zjc.taste.api;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Administrator on 2016/10/21.
 */
public class Aes {
    private Cipher encrypt;

    private Cipher decrypt;

    private String key;


    /**
     * @param key
     * @throws
     */
    public Aes(String key) throws Exception {
        super();

        if (key == null || key.length() != 16) {
//            throw new MedicalException("key must 16 chars");
        }


        try {
            {
                byte[] raw = key.getBytes("ASCII");
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

                encrypt = cipher;
            }

            {
                byte[] raw = key.getBytes("ASCII");
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE, skeySpec);

                decrypt = cipher;
            }

            this.key = key;
        } catch (Exception e) {
            throw new Exception("系统错误！");
        }

    }


    public byte[] decrypt(byte[] sSrc) throws Exception {


        try {
            byte[] original = decrypt.doFinal(sSrc);
            return original;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("系统错误！");
        }
    }

    public byte[] encrypt(byte[] ssrc) throws Exception {


        try {
            byte[] encrypted = encrypt.doFinal(ssrc);
            return encrypted;
        } catch (Exception e) {
            throw new Exception("系统错误！");
        }

    }

    public static byte[] hexSring2bytes(String str) {
        if (str.length() % 2 == 1) {
            str = str + 'F';
        }

        byte[] ret = new byte[str.length() / 2];

        for (int i = 0; i < ret.length; ++i) {
            String bs = str.substring(2 * i, 2 * i + 2);
            ret[i] = (byte) Integer.parseInt(bs, 16);
        }

        return ret;
    }
}
