package com.example.alexruhl.bachelorthesis.askCatalog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.alexruhl.bachelorthesis.R;

public class FixedTimesActivity extends AppCompatActivity {


    TextView chooseTimeMo;
    TextView chooseTimeDi;
    TextView chooseTimeMi;
    TextView chooseTimeDo;
    TextView chooseTimeFr;
    TextView chooseTimeSa;
    TextView chooseTimeSo;
    TimePickerDialog timePickerDialog;
    int currentHour;
    int currentMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_fixed_times);

        chooseTimeMo = findViewById(R.id.etChooseTimeMo);
        timePicker(chooseTimeMo);
        chooseTimeDi = findViewById(R.id.etChooseTimeDi);
        timePicker(chooseTimeDi);
        chooseTimeMi = findViewById(R.id.etChooseTimeMi);
        timePicker(chooseTimeMi);
        chooseTimeDo = findViewById(R.id.etChooseTimeDo);
        timePicker(chooseTimeDo);
        chooseTimeFr = findViewById(R.id.etChooseTimeFr);
        timePicker(chooseTimeFr);
        chooseTimeSa = findViewById(R.id.etChooseTimeSa);
        timePicker(chooseTimeSa);
        chooseTimeSo = findViewById(R.id.etChooseTimeSo);
        timePicker(chooseTimeSo);


    }

    public void goToNextPage(View view) {
        Intent intent = new Intent(this, PostcodeActivity.class);
        //Read Bundle
        Bundle extras = getIntent().getExtras();
        SharedData sharedData = null;
        if (extras != null) {
            sharedData = (SharedData) extras.get("sharedData");
        }


        //Write Bundle
        String festeZeiten = timeToString();
        assert sharedData != null;
        sharedData.setFesteZeiten(festeZeiten);
        intent.putExtra("sharedData", sharedData);
        startActivity(intent);
    }

    public void timePicker(final TextView chooseTime) {


        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timePickerDialog = new TimePickerDialog(FixedTimesActivity.this ,new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("DefaultLocale")
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                        chooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes));
                    }
                }, currentHour, currentMinute, true);


               // timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                timePickerDialog.show();

            }


        });

    }

    public String timeToString() {
        String string = "";
        string = string + "Mo:" + chooseTimeMo.getText().toString();
        string = string + "Di:" + chooseTimeDi.getText().toString();
        string = string + "Mi:" + chooseTimeMi.getText().toString();
        string = string + "Do:" + chooseTimeDo.getText().toString();
        string = string + "Fr:" + chooseTimeFr.getText().toString();
        string = string + "Sa:" + chooseTimeSa.getText().toString();
        string = string + "So:" + chooseTimeSo.getText().toString();
        Log.i("festeZeiten", string);

        return string;
    }
}
