package com.example.alexruhl.bachelorthesis;

import android.annotation.SuppressLint;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.security.Key;


import java.io.*;

public class Decrypt {
    private static String str;

    public static void main(String[] args) {
        //Read File
        try {
            //Navigate to File Here
            FileReader fileReader = new FileReader("/Users/alex/Downloads/data2.csv");
            int n;
            //Performance StringBuilder
            StringBuilder stringBuilder = new StringBuilder();

            while ((n = fileReader.read()) != -1) {
                stringBuilder.append((char) n);
            }
            str = stringBuilder.toString();
            fileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Write File

        try {    //Navigate to Output Path here
            FileWriter out = new FileWriter("/Users/alex/Downloads/dec.csv");
            //For Debugging
            //out.write(encrypt(str, "Bbj@.tpKHQ>dm8dc"));
            out.write(decrypt(str, "Bbj@.tpKHQ>dm8dc"));
            out.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private static String decrypt(String encrypt, String key) throws Exception {
        String strData;

        try {
            // now convert the string to byte array
            // for decryption
            byte[] bb = new byte[encrypt.length()];
            for (int i = 0; i < encrypt.length(); i++) {
                bb[i] = (byte) encrypt.charAt(i);
            }

            // decrypt the text
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            @SuppressLint("GetInstance") Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            strData = new String(cipher.doFinal(bb));

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return strData;
    }

    private static String encrypt(String eingabe, String key) throws Exception {
        String strData;

        try {
            // Create key and cipher
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            @SuppressLint("GetInstance") Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(eingabe.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : encrypted) {
                sb.append((char) b);
            }
            strData = sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return strData;
    }
}
