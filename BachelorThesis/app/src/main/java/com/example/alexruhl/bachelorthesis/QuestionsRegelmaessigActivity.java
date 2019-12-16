package com.example.alexruhl.bachelorthesis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;

public class QuestionsRegelmaessigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_regelmaessig);
    }

    public void goToNextPage(View view) {
        Intent intent = new Intent(this, QuestionsSportProWocheActivity.class);
        //Read Bundle
        Bundle extras = getIntent().getExtras();
        SharedData sharedData = null;
        if (extras != null) {
            sharedData = (SharedData) extras.get("sharedData");
        }


        RatingBar ratingBar = findViewById(R.id.ratingBarRegelmaessig);
        Integer ratingRegelmaessig = (int) ratingBar.getRating();
        Log.i("ratingRegelmaessig", ratingRegelmaessig.toString());

        //Write Bundle
        assert sharedData != null;
        sharedData.setRatingRegelmaessig(String.valueOf(ratingRegelmaessig));
        intent.putExtra("sharedData", sharedData);
        startActivity(intent);
    }
}
