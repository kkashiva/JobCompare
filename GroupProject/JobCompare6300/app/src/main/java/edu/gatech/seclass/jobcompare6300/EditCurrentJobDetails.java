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
        dbHelper = JobDbHelper.getInstance(this); // get singleton instance
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseContract.Jobs.COLUMN_NAME_TITLE,
                DatabaseContract.Jobs.COLUMN_NAME_COMPANY,
                DatabaseContract.Jobs.COLUMN_NAME_LOCATION_CITY,
                DatabaseContract.Jobs.COLUMN_NAME_LOCATION_STATE,
                DatabaseContract.Jobs.COLUMN_NAME_COST_OF_LIVING,
                DatabaseContract.Jobs.COLUMN_NAME_YEARLY_SALARY,
                DatabaseContract.Jobs.COLUMN_NAME_YEARLY_BONUS,
                DatabaseContract.Jobs.COLUMN_NAME_TRAINING_DEVELOPMENT_FUND,
                DatabaseContract.Jobs.COLUMN_NAME_LEAVE_TIME,
                DatabaseContract.Jobs.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK
        };

        String selection = DatabaseContract.Jobs.COLUMN_NAME_JOB_TYPE + " = ?";
        String[] selectionArgs = { "0" };

        Cursor cursor = db.query(
                DatabaseContract.Jobs.TABLE_NAME, // FROM
                projection, // SELECT
                selection, // WHERE columns
                selectionArgs, // WHERE values
                null, // GROUP BY
                null, // filter by row groups
                null // SORT BY
        );

        if (cursor != null && cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_TITLE));
            String company = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_COMPANY));
            String city = cursor
                    .getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_LOCATION_CITY));
            String state = cursor
                    .getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_LOCATION_STATE));
            int costOfLiving = cursor
                    .getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_COST_OF_LIVING));
            int yearlySalary = cursor
                    .getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_YEARLY_SALARY));
            int yearlyBonus = cursor
                    .getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_YEARLY_BONUS));
            int trainingDevelopmentFund = cursor
                    .getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_TRAINING_DEVELOPMENT_FUND));
            int leaveTime = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_LEAVE_TIME));
            int teleworkDaysPerWeek = cursor
                    .getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK));

            inputTitle.setText(String.valueOf(title));
            inputCompany.setText(String.valueOf(company));
            inputCity.setText(String.valueOf(city));
            inputState.setText(String.valueOf(state));
            inputLivingCost.setText(String.valueOf(costOfLiving));
            inputYearlySalary.setText(String.valueOf(yearlySalary));
            inputYearlyBonus.setText(String.valueOf(yearlyBonus));
            inputTrainingDevelopment.setText(String.valueOf(trainingDevelopmentFund));
            inputLeaveTime.setText(String.valueOf(leaveTime));
            inputTelework.setText(String.valueOf(teleworkDaysPerWeek));

        } else {
            Toast.makeText(this, "No current job details found.", Toast.LENGTH_LONG).show();
        }

        if (cursor != null) {
            cursor.close();
        }
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