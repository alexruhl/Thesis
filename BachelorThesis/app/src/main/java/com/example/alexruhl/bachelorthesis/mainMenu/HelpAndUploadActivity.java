package com.example.alexruhl.bachelorthesis.mainMenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.alexruhl.bachelorthesis.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class HelpAndUploadActivity extends AppCompatActivity {
    static String str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_upload);
    }

    //For Debugging enable uncomment ImageView in information_xml
    public void delete(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final Context context = this;

        builder.setTitle("Alle Daten Löschen?");
        builder.setMessage("Bitte nur ausführen, wenn Sie explizit darum gebeten werden.");


        View.OnClickListener dialog;
        builder.setPositiveButton("abbrechen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Do nothing
                // dialog.dismiss();
            }
        });

        builder.setNegativeButton("LÖSCHEN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Save CSV
                try {
                    String csv = "";
                    FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
                    out.write(csv.getBytes());
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

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
            //Key for Encrypt/Decrypt randomly generated
            String key = "Bbj@.tpKHQ>dm8dc";
            out.write(encrypt(str, key).getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

        //upload file
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
