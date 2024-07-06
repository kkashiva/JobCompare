package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Handler;

public class EditCurrentJobDetails extends AppCompatActivity {

    private JobDbHelper dbHelper;
    private EditText inputTitle, inputCompany, inputState, inputCity, inputLivingCost,
            inputYearlySalary, inputYearlyBonus, inputTrainingDevelopment, inputLeaveTime, inputTelework;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_current_job_details);
        inputTitle = findViewById(R.id.inputTitleID);
        inputCompany = findViewById(R.id.inputCompanyID);
        inputState = findViewById(R.id.inputStateID);
        inputCity = findViewById(R.id.inputCityID);
        inputLivingCost = findViewById(R.id.inputLivingCostID);
        inputYearlySalary = findViewById(R.id.inputYearlySalaryID);
        inputYearlyBonus = findViewById(R.id.inputYearlyBonusID);
        inputTrainingDevelopment = findViewById(R.id.inputTrainingDevelopmentID);
        inputLeaveTime = findViewById(R.id.inputLeaveTimeID);
        inputTelework = findViewById(R.id.inputTeleworkID);
    }

    public void handleClickBackToMainMenu(View view) {
        startActivity(new Intent(EditCurrentJobDetails.this, MainActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayCurrentJobDetails();
    }

    private void displayCurrentJobDetails() {

        // get singleton instance of CurrentJob
        CurrentJob currentJob = CurrentJob.getInstance(null, null, null, null, 0, 0, 0, 0, 0, 0, 0);

        // get current job details from DB
        currentJob.getCurrentJobDetails();
        
        inputTitle.setText(String.valueOf(currentJob.getTitle()));
        inputCompany.setText(String.valueOf(currentJob.getCompany()));
        inputCity.setText(String.valueOf(currentJob.getLocationCity()));
        inputState.setText(String.valueOf(currentJob.getLocationState()));
        inputLivingCost.setText(String.valueOf(currentJob.getCostOfLiving()));
        inputYearlySalary.setText(String.valueOf(currentJob.getYearlySalary()));
        inputYearlyBonus.setText(String.valueOf(currentJob.getYearlyBonus()));
        inputTrainingDevelopment.setText(String.valueOf(currentJob.getTrainingDevelopmentFund()));
        inputLeaveTime.setText(String.valueOf(currentJob.getLeaveTime()));
        inputTelework.setText(String.valueOf(currentJob.getTeleworkDaysPerWeek()));

    }

    public void handleClickEditJob(View view) {

        String title = inputTitle.getText().toString();
        String company = inputCompany.getText().toString();
        String locationState = inputState.getText().toString();
        String locationCity = inputCity.getText().toString();
        Integer costOfLiving = Integer.parseInt(inputLivingCost.getText().toString());
        Integer yearlySalary = Integer.parseInt(inputYearlySalary.getText().toString());
        Integer yearlyBonus = Integer.parseInt(inputYearlyBonus.getText().toString());
        Integer trainDevFund = Integer.parseInt(inputTrainingDevelopment.getText().toString());
        Integer leaveDay = Integer.parseInt(inputLeaveTime.getText().toString());
        Integer teleworkDaysPerWeek = Integer.parseInt(inputTelework.getText().toString());
        
        // get singleton instance of CurrentJob
        CurrentJob currentJob = CurrentJob.getInstance(title, company, locationState, locationCity, costOfLiving, yearlySalary, yearlyBonus, trainDevFund, leaveDay, teleworkDaysPerWeek, 0);

        // update current job in DB
        currentJob.updateCurrentJob(title, company, locationState, locationCity, costOfLiving, yearlySalary, yearlyBonus, trainDevFund, leaveDay, teleworkDaysPerWeek);

        Toast.makeText(this, "Current job details updated.", Toast.LENGTH_LONG).show();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(EditCurrentJobDetails.this, MainActivity.class));
            }
        }, 1000);

    }


}