package com.example.alexruhl.bachelorthesis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class InformationActivity extends AppCompatActivity {
    static String str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
    }

    public void export(View view) {

        try {
            //Navigate to File Here
            File file = getFileStreamPath("data.csv");
            FileReader fileReader = new FileReader(file);
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

        try {
            FileOutputStream out = openFileOutput("data2.csv", Context.MODE_PRIVATE);
            out.write(encrypt(str, "Bbj@.tpKHQ>dm8dc").getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

       /* try {
            FileWriter out = new FileWriter("/Users/alex/Bachelorarbeit/Bachelor/Bachelorarbeit/dec.txt");
            out.write(encrypt(str, "Bbj@.tpKHQ>dm8dc"));
            out.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

*/


        Context context = getApplicationContext();
        File filelocation = new File(getFilesDir(), "data2.csv");
        Uri path = FileProvider.getUriForFile(context, "com.example.alexruhl.bachelorthesis", filelocation);
        Intent fileIntent = new Intent(Intent.ACTION_SEND);
        fileIntent.setType("text/csv");
        fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Data");
        fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        fileIntent.putExtra(Intent.EXTRA_STREAM, path);
        startActivity(Intent.createChooser(fileIntent, "Hochladen"));
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
