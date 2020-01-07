package com.example.alexruhl.bachelorthesis;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TrainingActivity extends AppCompatActivity {

    TextView chooseTimeStart;
    TextView chooseTimeEnd;
    TimePickerDialog timePickerDialog;
    int currentHour;
    int currentMinute;
    public String csv = "";
    TextView textView;
    Calendar myCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        chooseTimeStart = findViewById(R.id.etChooseTimeStart);
        timePicker(chooseTimeStart);
        chooseTimeEnd = findViewById(R.id.etChooseTimeEnd);
        timePicker(chooseTimeEnd);

        myCalendar = Calendar.getInstance();

        textView = findViewById(R.id.trainingDate);
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
                new DatePickerDialog(TrainingActivity.this, date, myCalendar
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

        TextView start = findViewById(R.id.etChooseTimeStart);
        TextView end = findViewById(R.id.etChooseTimeEnd);
        String startTime = start.getText().toString();
        String endTime = end.getText().toString();

        if (startTime.length() == 0 || endTime.length() == 0) {
            Toast.makeText(this, "Bitte Zeiten angeben", Toast.LENGTH_SHORT).show();
        } else {


            Intent intent = new Intent(this, MainActivity.class);
            Date date = new Date();

            //Datum
            String datum = textView.getText().toString();
            if (datum.length() == 0) {
                datum = textView.getHint().toString();
            }

           /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
            String datum = simpleDateFormat.format(date);
            Log.i("datum", datum);

            //Uhrzeit
            simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.GERMAN);
            String time = simpleDateFormat.format(date);
            Log.i("Uhrzeit", time); */


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
            String radioString = "";
            RadioButton radioButtonYes = findViewById(R.id.radioButtonYes);
            RadioButton radioButtonNo = findViewById(R.id.radioButtonNo);
            if (radioButtonYes.isChecked()) {
                radioString = "Geplannt";
            } else if (radioButtonNo.isChecked()) {
                radioString = "Nicht geplant";
            }

            //CSV New Line Edit here for Data Research

            String add = "\nSport:" + datum + "," + "Start:" + startTime + "," + "End:" + endTime + "," + radioString;
            csv = csv + add;


            //Write csv

            try {
                FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
                out.write(csv.getBytes());
                out.close();
            } catch (Exception e) {
                e.printStackTrace();

            }


            Toast.makeText(this, "Sport hizugefügt", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }

    public void timePicker(final TextView chooseTime) {


        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timePickerDialog = new TimePickerDialog(TrainingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("DefaultLocale")
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                        chooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes));
                    }
                }, currentHour, currentMinute, true);

                timePickerDialog.show();

            }


        });

    }

}
