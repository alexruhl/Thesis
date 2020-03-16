package com.example.alexruhl.bachelorthesis.askCatalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;

import com.example.alexruhl.bachelorthesis.R;

public class RegularityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_regularity);
    }

    public void goToNextPage(View view) {
        Intent intent = new Intent(this, SportPerWeekActivity.class);
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
