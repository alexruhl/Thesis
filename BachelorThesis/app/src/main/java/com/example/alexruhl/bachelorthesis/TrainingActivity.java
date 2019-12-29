package com.example.alexruhl.bachelorthesis;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TrainingActivity extends AppCompatActivity {

    TextView chooseTimeStart;
    TextView chooseTimeEnd;
    TimePickerDialog timePickerDialog;
    int currentHour;
    int currentMinute;
    public String csv = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        chooseTimeStart = findViewById(R.id.etChooseTimeStart);
        timePicker(chooseTimeStart);
        chooseTimeEnd = findViewById(R.id.etChooseTimeEnd);
        timePicker(chooseTimeEnd);
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

                    stringBuilder.append((char)n);
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

            String add = "\n" + datum + "," + time + "," + "Start:" + startTime + "," + "End:" + endTime + "," + radioString;
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
