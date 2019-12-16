package com.example.alexruhl.bachelorthesis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class QuestionsAnzahlSportartenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_anzahl_sportarten);
    }

    public void goToNextPage(View view) {
        Intent intent = new Intent(this, QuestionsRegelmaessigActivity.class);
        EditText editText = findViewById(R.id.anzahlSportarten);
        String string = editText.getText().toString();

        if (string.length() == 0) {
            Toast.makeText(this, "Bitte eine Zahl eingeben", Toast.LENGTH_SHORT).show();
        } else {
            //Read Bundle
            Bundle extras = getIntent().getExtras();
            SharedData sharedData = null;
            if (extras != null) {
                sharedData = (SharedData) extras.get("sharedData");
            }

            assert sharedData != null;
            sharedData.setAnzahlSportarten(string);
            intent.putExtra("sharedData", sharedData);
            startActivity(intent);
        }
    }
}
