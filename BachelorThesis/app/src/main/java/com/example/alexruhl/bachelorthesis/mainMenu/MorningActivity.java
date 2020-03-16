package com.example.alexruhl.bachelorthesis.mainMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.androdocs.httprequest.HttpRequest;
import com.example.alexruhl.bachelorthesis.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MorningActivity extends AppCompatActivity {
    public String csv = "";
    static String PLZ = "34497";
    static String API = "960f06c2851d05d3b235a039781106fc\n";
    public static String weatherString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morning);

        //Wetter input
        new weather().execute();

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

        //PLZ lesen
        try {
            FileInputStream fileInputStream = openFileInput("plz.txt");
            StringBuilder stringBuilder = new StringBuilder();
            int n;
            stringBuilder.append(PLZ);
            while ((n = fileInputStream.read()) != -1) {
                stringBuilder.append(n);
            }
            String plzTest = stringBuilder.toString();
            if (plzTest.length() == 5) {
                PLZ = stringBuilder.toString();
            } //Default PLZ: 34497

            Log.i("plz", PLZ);

            fileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


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

        //CSV New Line Edit here for Data Research
        String add = "\n" + datum + "," + time + "," + "Sleep:" + rating + "," + radioString;
        //Add Weather
        csv = csv + add + weatherString;
        Log.i("Wetter", weatherString);


        //Write csv

        try {
            FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
            out.write(csv.getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();

        }


        Toast.makeText(this, "Daten hinzugefügt", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    static class weather extends AsyncTask<String, Void, String> {


        protected String doInBackground(String... args) {
            return HttpRequest.excuteGet("http://api.openweathermap.org/data/2.5/forecast?zip=" + PLZ + ",de&units=metric&lang=en&appid=" + API);
        }

        @Override
        protected void onPostExecute(String result) {


            try {
                //read JSON and fill weather String

                JSONObject jsonObj = new JSONObject(result);
                JSONObject city = jsonObj.getJSONObject("city");
                long sunrise = city.getLong("sunrise");
                long sunset = city.getLong("sunset");
                JSONArray list = jsonObj.getJSONArray("list");

                /* Sunrise and Sunset not needed
                weatherString = weatherString +
                        "Sonnenaufgang:" +
                        new SimpleDateFormat("HH:mm", Locale.GERMAN).format(new Date(sunrise * 1000)) + ","
                        + "Sonnenuntergang:" +
                        new SimpleDateFormat("HH:mm", Locale.GERMAN).format(new Date(sunset * 1000)) + ",";

                        Log.i("Sunrise", "Sonnenaufgang:" +
                        new SimpleDateFormat("HH:mm", Locale.GERMAN).format(new Date(sunrise * 1000)) + ",");
                        Log.i("sunset", "Sonnenuntergang: " +
                        new SimpleDateFormat("HH:mm", Locale.GERMAN).format(new Date(sunset * 1000)) + ",");
*/

                //add weather in 3h intervalls of today from now to midnight

                Calendar time = Calendar.getInstance();
                int hour = time.get(Calendar.HOUR_OF_DAY);
                int i = 0;
                StringBuilder stringBuilder = new StringBuilder();
                while (hour <= 24) {
                    //Temp.
                    stringBuilder.append("Temperatur:");
                    stringBuilder.append(list.getJSONObject(i).getJSONObject("main").getString("temp")).append(",");
                    //Feels Like
                    stringBuilder.append("Feels Like:");
                    stringBuilder.append(list.getJSONObject(i).getJSONObject("main").getString("feels_like")).append(",");
                    //Weather_description
                    stringBuilder.append("Wetter:");
                    stringBuilder.append(list.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("description")).append(",");
                    // Log.i("Desc.",list.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("description"));
                    //Time
                    stringBuilder.append(list.getJSONObject(i).getString("dt_txt")).append(",");
                    i++;
                    hour += 3;
                }


                weatherString = "\n" + stringBuilder.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
