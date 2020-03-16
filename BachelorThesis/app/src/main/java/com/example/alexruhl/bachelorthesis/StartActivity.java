package com.example.alexruhl.bachelorthesis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.alexruhl.bachelorthesis.askCatalog.InstructionActivity;
import com.example.alexruhl.bachelorthesis.mainMenu.MainActivity;

import java.io.File;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }


    public void goToNextPage(View view) {
        File filelocation = new File(getFilesDir(), "data.csv");

        if (filelocation.exists()) {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } else {
            Intent intent = new Intent(this, InstructionActivity.class);
            startActivity(intent);
        }
    }
}
