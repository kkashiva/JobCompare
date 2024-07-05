package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Display2JobOffers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display2_job_offers);
        Intent intent = getIntent();
        Job job1 = (Job)intent.getSerializableExtra("offer1");
        Job job2 = (Job)intent.getSerializableExtra("offer2");

        // Setting title row
        TextView title1 = findViewById(R.id.titleOffer1);
        title1.setText(String.valueOf(job1.getTitle()));
        TextView title2 = findViewById(R.id.titleOffer2);
        title2.setText(String.valueOf(job2.getTitle()));

        // Setting company row
        TextView company1 = findViewById(R.id.companyOffer1);
        company1.setText(String.valueOf(job1.getCompany()));
        TextView company2 = findViewById(R.id.companyOffer2);
        company2.setText(String.valueOf(job2.getCompany()));

        // Setting location row
        TextView location1 = findViewById(R.id.locationOffer1);
        location1.setText(String.valueOf(job1.getLocation()));
        TextView location2 = findViewById(R.id.locationOffer2);
        location2.setText(String.valueOf(job2.getLocation()));

        // Setting cost of living
        TextView cost1 = findViewById(R.id.costOfLivingOffer1);
        cost1.setText(String.valueOf(job1.getCostOfLiving()));
        TextView cost2 = findViewById(R.id.costOfLivingOffer2);
        cost2.setText(String.valueOf(job2.getCostOfLiving()));

        // Setting yearly bonus
        TextView bonus1 = findViewById(R.id.bonusOffer1);
        bonus1.setText(String.valueOf(job1.getYearlyBonus()));
        TextView bonus2 = findViewById(R.id.bonusOffer2);
        bonus2.setText(String.valueOf(job2.getYearlyBonus()));

        // Setting yearly salary
        TextView salary1 = findViewById(R.id.salaryOffer1);
        salary1.setText(String.valueOf(job1.getYearlySalary()));
        TextView salary2 = findViewById(R.id.salaryOffer2);
        salary2.setText(String.valueOf(job2.getYearlySalary()));

        // Setting fund
        TextView fund1 = findViewById(R.id.fundOffer1);
        fund1.setText(String.valueOf(job1.getTrainingDevelopmentFund()));
        TextView fund2 = findViewById(R.id.fundOffer2);
        fund2.setText(String.valueOf(job2.getTrainingDevelopmentFund()));

        // Setting leave time
        TextView leave1 = findViewById(R.id.leaveOffer1);
        leave1.setText(String.valueOf(job1.getLeaveTime()));
        TextView leave2 = findViewById(R.id.leaveOffer2);
        leave2.setText(String.valueOf(job2.getLeaveTime()));

        // Setting teleworkday
        TextView tele1 = findViewById(R.id.teleOffer1);
        tele1.setText(String.valueOf(job1.getTeleworkDaysPerWeek()));
        TextView tele2 = findViewById(R.id.teleOffer2);
        tele2.setText(String.valueOf(job2.getTeleworkDaysPerWeek()));
    }

    public void handleClickBackToMainMenu(View view) {
        startActivity(new Intent(Display2JobOffers.this, MainActivity.class));
    }

    public void handleClickCompareActionButton(View view) {
        startActivity(new Intent(Display2JobOffers.this, CompareJobOffers.class));
    }
}