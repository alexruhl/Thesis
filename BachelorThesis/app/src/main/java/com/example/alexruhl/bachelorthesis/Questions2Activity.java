package com.example.alexruhl.bachelorthesis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;

public class Questions2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions2);
    }

    public void goToNextPage(View view) {
        Intent intent = new Intent(this, Questions3Activity.class);
        //Read Bundle
        Bundle extras = getIntent().getExtras();
        Integer anzahlSportarten = null;
        if (extras != null) {
            anzahlSportarten = extras.getInt("anzahlSportarten");
        }


        RatingBar ratingBar = findViewById(R.id.ratingBarRegelmaessig);
        Integer ratingRegelmaessig = (int) ratingBar.getRating();
        Log.i("ratingRegelmaessig",ratingRegelmaessig.toString());

        //Write Bundle
        intent.putExtra("anzahlSportarten", anzahlSportarten);
        intent.putExtra("ratingRegelmaessig", ratingRegelmaessig);
        startActivity(intent);
    }
}
