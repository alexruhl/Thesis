package com.example.alexruhl.bachelorthesis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MorningActivity extends AppCompatActivity {
    public String csv = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morning);
    }

    public void goToNextPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);


        Date date = new Date();

        //Datum
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
        String datum = simpleDateFormat.format(date);
        Log.i("datum", datum);

        //Uhrzeit
        simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.GERMAN);
        String time = simpleDateFormat.format(date);
        Log.i("Uhrzeit", time);


        //Input CSV

        try {
            FileInputStream fileInputStream = openFileInput("data.csv");
            int n;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(csv);
            while ((n = fileInputStream.read()) != -1) {
                stringBuilder.append(n);
               // csv = csv + (char) n;
            }

            fileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        //Edit CSV
        RatingBar ratingBar = findViewById(R.id.ratingBarMorning);
        float rating = ratingBar.getRating();
        String radioString = "";
        RadioButton radioButtonYes = findViewById(R.id.radioButtonSportYes);
        RadioButton radioButtonMaybe = findViewById(R.id.radioButtonSportMaybe);
        RadioButton radioButtonNo = findViewById(R.id.radioButtonSportNo);
        if (radioButtonYes.isChecked()) {
            radioString = "Heute Sport";
        } else if (radioButtonMaybe.isChecked()) {
            radioString = "Heute vielleicht Sport";
        } else if (radioButtonNo.isChecked()) {
            radioString = "Heute kein Sport";
        }

        //CSV New Line Edit here for Data Research TODO Wetter + Daily Total Schrittzähler
        csv = csv + "\n" + datum + "," + time + "," + "Sleep:" + rating + "," + radioString;


        //Write csv

        try {
            FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
            out.write(csv.getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();

        }


        Toast.makeText(this, "Daten hizugefügt", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
