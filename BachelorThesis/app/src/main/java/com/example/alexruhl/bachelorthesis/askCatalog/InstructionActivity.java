package com.example.alexruhl.bachelorthesis.askCatalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.alexruhl.bachelorthesis.R;

public class InstructionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_instruction);
    }

    public void goToNextPage(View view) {
        Intent intent = new Intent(this, AgeActivity.class);
        startActivity(intent);
    }
}
