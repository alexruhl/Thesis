package com.example.alexruhl.bachelorthesis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class QuestionsStrukturActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_struktur);
    }

    public void goToNextPage(View view) {
        Intent intent = new Intent(this, QuestionsZeitraumActivity.class);

        //Read Bundle
        Bundle extras = getIntent().getExtras();
        SharedData sharedData = null;
        if (extras != null) {
            sharedData = (SharedData) extras.get("sharedData");
        }


        //Write Bundle
        RatingBar ratingBar = findViewById(R.id.ratingBarStrukturiert);
        String rating =  String.valueOf(ratingBar.getRating());
        assert sharedData != null;
        sharedData.setStruktur(rating);
        intent.putExtra("sharedData",sharedData);

        startActivity(intent);
    }
}
