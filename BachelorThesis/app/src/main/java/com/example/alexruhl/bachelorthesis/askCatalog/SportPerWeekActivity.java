package com.example.alexruhl.bachelorthesis.askCatalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alexruhl.bachelorthesis.R;

public class SportPerWeekActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_sport_per_week);
    }

    public void goToNextPage(View view) {
        Intent intent = new Intent(this, FixedTimesActivity.class);

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
