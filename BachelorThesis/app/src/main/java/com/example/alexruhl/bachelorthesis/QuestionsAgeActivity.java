package com.example.alexruhl.bachelorthesis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class QuestionsAgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_age);
    }

    public void goToNextPage(View view) {
        Intent intent = new Intent(this, QuestionsAnzahlSportartenActivity.class);
        EditText editText = findViewById(R.id.alter);
        String string = editText.getText().toString();

        if (string.length() == 0) {
            Toast.makeText(this, "Bitte eine Zahl eingeben", Toast.LENGTH_SHORT).show();
        } else {
            SharedData sharedData = new SharedData();
            sharedData.setAlter(string);
            intent.putExtra("sharedData", sharedData);
            startActivity(intent);
        }
    }
}
