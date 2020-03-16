package com.example.alexruhl.bachelorthesis.mainMenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;

import com.example.alexruhl.bachelorthesis.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToMorningPage(View view) {
        if (isOnline()) {
            Intent intent = new Intent(this, MorningActivity.class);
            startActivity(intent);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final Context context = this;

            builder.setTitle("Bitte mit dem Internet verbinden");
            // builder.setMessage("Möchten Sie bestätigen? ");

            View.OnClickListener dialog;
            builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Do nothing
                    // dialog.dismiss();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
    }

    public void goToFoodPage(View view) {
        Intent intent = new Intent(this, MealActivity.class);
        startActivity(intent);
    }

    public void goToTrainingPage(View view) {
        Intent intent = new Intent(this, SportActivity.class);
        startActivity(intent);
    }

    public void goToInformationPage(View view) {
        Intent intent = new Intent(this, HelpAndUploadActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // do nothing
    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        return (networkInfo != null && networkInfo.isConnected());
    }
}
