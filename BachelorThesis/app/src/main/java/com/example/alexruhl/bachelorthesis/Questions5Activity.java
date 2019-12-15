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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Questions5Activity extends AppCompatActivity {

    public CheckBox checkBoxMorgens;
    public CheckBox checkBoxMittags;
    public CheckBox checkBoxAbends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_questions5);
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
                Integer anzahlSportarten = null;
                Integer ratingRegelmaessig = null;
                String festeZeiten = null;
                Integer struktur = null;
                if (extras != null) {
                    ratingRegelmaessig = extras.getInt("ratingRegelmaessig");
                    anzahlSportarten = extras.getInt("anzahlSportarten");
                    festeZeiten = extras.getString("festeZeiten");
                    struktur = extras.getInt("struktur");
                }
                String tageszeitraum = checkBoxToString();

                //create CSV -> first Line / Header

                String csv =
                        "AnzahlSportarten: " + anzahlSportarten + "|"
                                + "regelmaessig:" + ratingRegelmaessig + "|"
                                + festeZeiten + ","
                                + "Strukturiert:" + struktur + "|"
                                + tageszeitraum;

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
            string += "Morgens+";
        }

        if (checkBoxMittags.isChecked()) {
            string += "Mittags+";
        }

        if (checkBoxAbends.isChecked()) {
            string += "Abends";
        }
        Log.e("Tageszeitraum", string);
        return string;
    }
}
