package com.example.alexruhl.bachelorthesis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToMorningPage(View view) {
        Intent intent = new Intent(this, MorningActivity.class);
        startActivity(intent);
    }

    public void goToFoodPage(View view) {
        Intent intent = new Intent(this, FoodActivity.class);
        startActivity(intent);
    }

    public void goToTrainingPage(View view) {
        Intent intent = new Intent(this, TrainingActivity.class);
        startActivity(intent);
    }

    public void goToInformationPage(View view) {
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // do nothing
    }
}
