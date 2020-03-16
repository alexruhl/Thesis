package com.example.alexruhl.bachelorthesis.mainMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexruhl.bachelorthesis.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MealActivity extends AppCompatActivity {

    public String csv = "";

    TextView textView;
    Calendar myCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        myCalendar = Calendar.getInstance();

        textView = findViewById(R.id.foodDate);
        initLabel();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(MealActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd.MM.yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.GERMAN);

        textView.setText(sdf.format(myCalendar.getTime()));
    }

    private void initLabel() {
        String myFormat = "dd.MM.yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.GERMAN);
        textView.setHint(sdf.format(myCalendar.getTime()));
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

        //Datum
        String datum2 = textView.getText().toString();
        if (datum2.length() == 0) {
            datum2 = textView.getHint().toString();
        }

        //CSV New Line Edit here for Data Research

        String add = "\nDatum:" + datum2 + ",Eingetragen:" + datum + "," + time + "," + "Essen:" + rating;
        csv = csv + add;


        //Write csv

        try {
            FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
            out.write(csv.getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();

        }


        Toast.makeText(this, "Essen hinzugefügt", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
