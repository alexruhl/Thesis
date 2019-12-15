package com.example.alexruhl.bachelorthesis;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class Questions3Activity extends AppCompatActivity {


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
        setContentView(R.layout.activity_questions3);

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
        Intent intent = new Intent(this, Questions4Activity.class);
        //Read Bundle
        Bundle extras = getIntent().getExtras();
        Integer anzahlSportarten = null;
        Integer ratingRegelmaessig = null;
        if (extras != null) {
            ratingRegelmaessig = extras.getInt("ratingRegelmaessig");
            anzahlSportarten = extras.getInt("anzahlSportarten");
        }



        //Write Bundle
        intent.putExtra("anzahlSportarten", anzahlSportarten);
        intent.putExtra("ratingRegelmaessig", ratingRegelmaessig);
        String festeZeiten = timeToString();
        intent.putExtra("festeZeiten",festeZeiten);
        startActivity(intent);
    }

    public void timePicker(final TextView chooseTime) {


        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timePickerDialog = new TimePickerDialog(Questions3Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("DefaultLocale")
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                        chooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes));
                    }
                }, currentHour, currentMinute, true);

                timePickerDialog.show();

            }


        });

    }

    public String timeToString(){
        String string="";
        string = string + "Mo:" + chooseTimeMo.getText().toString() + ",";
        string = string + "Di:" + chooseTimeDi.getText().toString()+ ",";
        string = string + "Mi:" + chooseTimeMi.getText().toString()+ ",";
        string = string + "Do:" + chooseTimeDo.getText().toString()+ ",";
        string = string + "Fr:" + chooseTimeFr.getText().toString()+ ",";
        string = string + "Sa:" + chooseTimeSa.getText().toString()+ ",";
        string = string + "So:" + chooseTimeSo.getText().toString();
        Log.i("festeZeiten",string);

        return string;
    }
}
