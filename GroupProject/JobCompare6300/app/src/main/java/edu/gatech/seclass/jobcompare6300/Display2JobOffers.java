package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Display2JobOffers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display2_job_offers);
    }

    //    TODO: refactor this method
    public void handleClickBackToCompareJobOffersScreen(View view) {
        startActivity(new Intent(Display2JobOffers.this, CompareJobOffers.class));
    }

    public void handleClickBackToMainMenu(View view) {
        startActivity(new Intent(Display2JobOffers.this, MainActivity.class));
    }
}