package com.example.alexruhl.bachelorthesis;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import java.io.FileOutputStream;

public class QuestionsZeitraumActivity extends AppCompatActivity {

    public CheckBox checkBoxMorgens;
    public CheckBox checkBoxMittags;
    public CheckBox checkBoxAbends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_questions_zeitraum);
    }

    public void goToNextPage(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final Context context = this;

        builder.setTitle("Bestätigen?");
        // builder.setMessage("Möchten Sie bestätigen? ");

        View.OnClickListener dialog;
        builder.setPositiveButton("abbrechen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Do nothing
                // dialog.dismiss();
            }
        });

        builder.setNegativeButton("weiter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //goToNextPage
                Intent intent = new Intent(context, MainActivity.class);

                //Read Bundle
                Bundle extras = getIntent().getExtras();
                SharedData sharedData = null;
                if (extras != null) {
                    sharedData = (SharedData) extras.get("sharedData");
                }
                String tageszeitraum = checkBoxToString();
                assert sharedData != null;
                String anzahlSportarten = sharedData.getAnzahlSportarten();
                String ratingRegelmaessig = sharedData.getRatingRegelmaessig();
                String festeZeiten = sharedData.getFesteZeiten();
                String struktur = sharedData.getStruktur();
                String sportPerWeek = sharedData.getSportPerWeek();
                String alter = sharedData.getAlter();

                //create CSV -> first Line / Header

                String csv =
                        "Alter:" + alter + "|"
                                + "AnzahlSportarten: " + anzahlSportarten + "|"
                                + "Regelmaessig:" + ratingRegelmaessig + "|"
                                + "Pro Woche:" + sportPerWeek + "|"
                                + festeZeiten + "|"
                                + "Strukturiert:" + struktur + "|"
                                + tageszeitraum;

                //Save CSV
                try {
                    FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
                    out.write(csv.getBytes());
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();

                }

                startActivity(intent);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public String checkBoxToString() {
        String string = "";

        checkBoxMorgens = findViewById(R.id.checkBoxMorgens);
        checkBoxMittags = findViewById(R.id.checkBoxMittags);
        checkBoxAbends = findViewById(R.id.checkBoxAbends);

        if (checkBoxMorgens.isChecked()) {
            string += "|Morgens|";
        }

        if (checkBoxMittags.isChecked()) {
            string += "|Mittags|";
        }

        if (checkBoxAbends.isChecked()) {
            string += "|Abends|";
        }
        return string;
    }
}
