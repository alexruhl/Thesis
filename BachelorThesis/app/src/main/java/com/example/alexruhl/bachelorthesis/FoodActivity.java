package com.example.alexruhl.bachelorthesis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FoodActivity extends AppCompatActivity {

    public String csv = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
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

            //Performance StringBuilder
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(csv);
            while ((n = fileInputStream.read()) != -1) {
                stringBuilder.append((char) n);
                //csv = csv + (char) n;
            }
            csv = stringBuilder.toString();
            fileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Edit CSV
        RatingBar ratingBar = findViewById(R.id.ratingBarFood);
        float rating = ratingBar.getRating();

        //CSV New Line Edit here for Data Research

        String add = "\n" + datum + "," + time + "," + "Essen:" + rating;
        csv = csv + add;


        //Write csv

        try {
            FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
            out.write(csv.getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();

        }


        Toast.makeText(this, "Essen hizugefügt", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
