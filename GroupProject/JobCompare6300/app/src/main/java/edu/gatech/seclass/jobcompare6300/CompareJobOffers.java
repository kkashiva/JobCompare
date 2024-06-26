package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CompareJobOffers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_job_offers);
    }

    public void handleClickBackToMainMenu(View view) {
        startActivity(new Intent(CompareJobOffers.this, MainActivity.class));
    }
}