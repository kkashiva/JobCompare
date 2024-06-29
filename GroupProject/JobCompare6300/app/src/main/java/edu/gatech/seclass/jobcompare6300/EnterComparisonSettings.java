package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;

public class EnterComparisonSettings extends AppCompatActivity {
    private EditText yearlySalaryEditText, yearlyBonusEditText, trainingAndDevEditText, leaveTimeEditText,teleworkDaysEditText;
    private Button buttonSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_comparison_settings);

        yearlySalaryEditText = findViewById(R.id.yearlySalaryEditTextId);
        yearlyBonusEditText = findViewById(R.id.yearlyBonusEditTextId);
        trainingAndDevEditText = findViewById(R.id.trainingAndDevEditTextId);
        leaveTimeEditText = findViewById(R.id.leaveTimeEditTextId);
        teleworkDaysEditText = findViewById(R.id.teleworkDaysEditTextId);
        buttonSubmit = findViewById(R.id.submitComparisonSettingsButtonID);

        // Instantiate our subclass of SQLiteOpenHelper
        JobDbHelper dbHelper = new JobDbHelper(this);

        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yearlySalary = yearlySalaryEditText.getText().toString().trim();
                String yearlyBonus = yearlyBonusEditText.getText().toString().trim();
                String trainingFund = trainingAndDevEditText.getText().toString().trim();
                String leaveTime = leaveTimeEditText.getText().toString().trim();
                String teleworkDays = teleworkDaysEditText.getText().toString().trim();

                boolean isValid = checkIfValid(yearlySalary, yearlyBonus,trainingFund, leaveTime, teleworkDays);
                if(!isValid)
                {
                    return;
                }

                int yearlySalaryInt = Integer.parseInt(yearlySalary);
                int yearlyBonusInt = Integer.parseInt(yearlyBonus);
                int trainingFundInt = Integer.parseInt(trainingFund);
                int leaveTimeInt = Integer.parseInt(leaveTime);
                int teleworkDaysInt = Integer.parseInt(teleworkDays);

                ContentValues values = new ContentValues();
                values.put(DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_SALARY_WEIGHT, yearlySalaryInt);
                values.put(DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_BONUS_WEIGHT, yearlyBonusInt);
                values.put(DatabaseContract.ComparisonSetting.COLUMN_NAME_LEAVE_TIME_WEIGHT, leaveTimeInt);
                values.put(DatabaseContract.ComparisonSetting.COLUMN_NAME_TRAINING_AND_DEVELOPMENT_FUND_WEIGHT, trainingFundInt);
                values.put(DatabaseContract.ComparisonSetting.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK_WEIGHT, teleworkDaysInt);

                // Insert the new row, returning the primary key value of the new row
                long comparisonSettingsId = db.insert(DatabaseContract.ComparisonSetting.TABLE_NAME, null, values);

                // show the jobId in a toast
                Toast.makeText(EnterComparisonSettings.this, "Comparison Settings Id: " + comparisonSettingsId, Toast.LENGTH_SHORT).show();
            }
        }


        );

    }

    private boolean checkIfValid(String yearlySalary, String yearlyBonus, String trainingFund, String leaveTime, String teleworkDays) {
        boolean isValid = true;
        if(yearlySalary.isEmpty()){
            yearlySalaryEditText.setError("Yearly Salary is empty");
            isValid = false;
        }
        if(yearlyBonus.isEmpty()){
            yearlyBonusEditText.setError("Yearly Bonus is empty");
            isValid = false;
        }
        if(trainingFund.isEmpty()){
            trainingAndDevEditText.setError("Training Fund is empty");
            isValid = false;
        }
        if(leaveTime.isEmpty()){
            leaveTimeEditText.setError("Leave Time is empty");
            isValid = false;
        }
        if(teleworkDays.isEmpty()){
            teleworkDaysEditText.setError("Telework Days is empty");
            isValid = false;
        }


        return isValid;
    }

    public void handleClickBackToMainMenuFromComparisonSettings(View view) {
        startActivity(new Intent(EnterComparisonSettings.this, MainActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayCurrentComparisonSettings();
    }

    private void displayCurrentComparisonSettings() {
        JobDbHelper dbHelper = new JobDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_SALARY_WEIGHT,
                DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_BONUS_WEIGHT,
                DatabaseContract.ComparisonSetting.COLUMN_NAME_LEAVE_TIME_WEIGHT,
                DatabaseContract.ComparisonSetting.COLUMN_NAME_TRAINING_AND_DEVELOPMENT_FUND_WEIGHT,
                DatabaseContract.ComparisonSetting.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK_WEIGHT

        };



        Cursor cursor = db.query(
                DatabaseContract.ComparisonSetting.TABLE_NAME,   // FROM
                projection,                         // SELECT
                null,
                null,        // WHERE values
                null,                       // GROUP BY
                null,                        // filter by row groups
                null                        // SORT BY
        );
        Toast.makeText(EnterComparisonSettings.this, "Cursor results: " + cursor, Toast.LENGTH_SHORT).show();
//
//        if (cursor != null && cursor.moveToFirst()) {
//            int yearlySalaryWeight = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_SALARY_WEIGHT));
//            int yearlyBonusWeight = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_BONUS_WEIGHT));
//            int trainingDevelopmentFundWeight = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ComparisonSetting.COLUMN_NAME_TRAINING_AND_DEVELOPMENT_FUND_WEIGHT));
//            int leaveTimeWeight = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ComparisonSetting.COLUMN_NAME_LEAVE_TIME_WEIGHT));
//            int teleworkDaysPerWeekWeight = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ComparisonSetting.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK_WEIGHT));
//
//
//            cursor.close();
//            yearlySalaryEditText.setText(yearlySalaryWeight);
//            yearlyBonusEditText.setText(yearlyBonusWeight);
//            trainingAndDevEditText.setText(trainingDevelopmentFundWeight);
//            leaveTimeEditText.setText(leaveTimeWeight);
//            teleworkDaysEditText.setText(teleworkDaysPerWeekWeight);
//
//        } else {
//            int defaultWeight = 1;
//            yearlySalaryEditText.setText(defaultWeight);
//            yearlyBonusEditText.setText(defaultWeight);
//            trainingAndDevEditText.setText(defaultWeight);
//            leaveTimeEditText.setText(defaultWeight);
//            teleworkDaysEditText.setText(defaultWeight);
//        }
    }
}
