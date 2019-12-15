package com.example.alexruhl.bachelorthesis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Questions1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions1);
    }

    public void goToNextPage(View view) {
        Intent intent = new Intent(this, Questions2Activity.class);
        EditText editText = findViewById(R.id.anzahlSportarten);
        String string = editText.getText().toString();

        if(string.length()==0){
            Toast.makeText(this,"Bitte eine Zahl eingeben",Toast.LENGTH_SHORT).show();
        } else {
            intent.putExtra("anzahlSportarten", string);
            Log.i("anzahlSportarten", string);
            startActivity(intent);
        }
    }
}
