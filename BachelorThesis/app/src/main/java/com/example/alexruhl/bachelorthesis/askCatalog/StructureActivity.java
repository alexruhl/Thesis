package com.example.alexruhl.bachelorthesis.askCatalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

import com.example.alexruhl.bachelorthesis.R;

public class StructureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_structure);
    }

    public void goToNextPage(View view) {
        Intent intent = new Intent(this, DaytimeActivity.class);

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
