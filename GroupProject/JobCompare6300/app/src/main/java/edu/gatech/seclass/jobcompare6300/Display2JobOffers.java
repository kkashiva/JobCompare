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
        Intent intent = getIntent();
        Job job1 = (Job)intent.getSerializableExtra("offer1");
        Job job2 = (Job)intent.getSerializableExtra("offer2");
        System.out.println("job1 " + job1.getTitle());
        System.out.println("job2 " + job2.getTitle());
    }

    public void handleClickBackToMainMenu(View view) {
        startActivity(new Intent(Display2JobOffers.this, MainActivity.class));
    }

    public void handleClickCompareActionButton(View view) {
        startActivity(new Intent(Display2JobOffers.this, CompareJobOffers.class));
    }
}