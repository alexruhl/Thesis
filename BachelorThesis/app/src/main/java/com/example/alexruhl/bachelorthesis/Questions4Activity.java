package com.example.alexruhl.bachelorthesis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;

public class Questions4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions4);
    }

    public void goToNextPage(View view) {
        Intent intent = new Intent(this, Questions5Activity.class);

        //Read Bundle
        Bundle extras = getIntent().getExtras();
        Integer anzahlSportarten = null;
        Integer ratingRegelmaessig = null;
        String festeZeiten = null;
        if (extras != null) {
            ratingRegelmaessig = extras.getInt("ratingRegelmaessig");
            anzahlSportarten = extras.getInt("anzahlSportarten");
            festeZeiten = extras.getString("festeZeiten");
        }



        //Write Bundle
        intent.putExtra("anzahlSportarten", anzahlSportarten);
        intent.putExtra("ratingRegelmaessig", ratingRegelmaessig);
        intent.putExtra("festeZeiten",festeZeiten);
        RatingBar ratingBar = findViewById(R.id.ratingBarStrukturiert);
        Integer rating = (int) ratingBar.getRating();
        Log.i("struktur",rating.toString());
        intent.putExtra("struktur",rating);

        startActivity(intent);
    }
}
