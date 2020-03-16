package com.example.alexruhl.bachelorthesis.askCatalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alexruhl.bachelorthesis.R;

import java.io.FileOutputStream;

public class PostcodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_postcode);
    }

    public void goToNextPage(View view) {
        Intent intent = new Intent(this, StructureActivity.class);
        EditText editText = findViewById(R.id.plz);
        String plz = editText.getText().toString();

        if (plz.length() != 5) {
            Toast.makeText(this, "Bitte eine PLZ eingeben", Toast.LENGTH_SHORT).show();
        } else {
            //Save PLZ
            try {
                FileOutputStream out = openFileOutput("plz.txt", Context.MODE_PRIVATE);
                out.write(plz.getBytes());
                out.close();
            } catch (Exception e) {
                e.printStackTrace();

            }

            Log.i("PLZ", plz);
            //Read Bundle
            Bundle extras = getIntent().getExtras();
            SharedData sharedData = null;
            if (extras != null) {
                sharedData = (SharedData) extras.get("sharedData");
            }
            //Write Bundle
            intent.putExtra("sharedData", sharedData);
            startActivity(intent);
        }

    }
}
