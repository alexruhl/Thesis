package com.example.alexruhl.bachelorthesis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;

public class QuestionsSportProWocheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_sport_pro_woche);
    }

    public void goToNextPage(View view) {
        Intent intent = new Intent(this, QuestionsFesteZeitenActivity.class);

        EditText editText = findViewById(R.id.anzahlProWoche);
        String sportPerWeek = editText.getText().toString();
        SharedData sharedData = null;


        if (sportPerWeek.length() == 0) {
            Toast.makeText(this, "Bitte eine Zahl eingeben", Toast.LENGTH_SHORT).show();
        } else {
            //Read Bundle
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                sharedData = (SharedData) extras.get("sharedData");
            }


            //Write Bundle
            assert sharedData != null;

            sharedData.setSportPerWeek(sportPerWeek);
            intent.putExtra("sharedData", sharedData);

            startActivity(intent);
        }
    }
}
