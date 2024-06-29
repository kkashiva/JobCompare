package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;

public class EnterComparisonSettings extends AppCompatActivity {
    private JobDbHelper dbHelper;
    private EditText yearlySalaryEditText, yearlyBonusEditText, trainingAndDevEditText, leaveTimeEditText,
            teleworkDaysEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_comparison_settings);

        yearlySalaryEditText = findViewById(R.id.yearlySalaryEditTextId);
        yearlyBonusEditText = findViewById(R.id.yearlyBonusEditTextId);
        trainingAndDevEditText = findViewById(R.id.trainingAndDevEditTextId);
        leaveTimeEditText = findViewById(R.id.leaveTimeEditTextId);
        teleworkDaysEditText = findViewById(R.id.teleworkDaysEditTextId);

    }

    private boolean checkIfValid(String yearlySalary, String yearlyBonus, String trainingFund, String leaveTime,
                                 String teleworkDays) {
        boolean isValid = true;
        if (yearlySalary.isEmpty()) {
            yearlySalaryEditText.setError("Yearly Salary is empty");
            isValid = false;
        }
        if (yearlyBonus.isEmpty()) {
            yearlyBonusEditText.setError("Yearly Bonus is empty");
            isValid = false;
        }
        if (trainingFund.isEmpty()) {
            trainingAndDevEditText.setError("Training Fund is empty");
            isValid = false;
        }
        if (leaveTime.isEmpty()) {
            leaveTimeEditText.setError("Leave Time is empty");
            isValid = false;
        }
        if (teleworkDays.isEmpty()) {
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
        dbHelper = JobDbHelper.getInstance(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_SALARY_WEIGHT,
                DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_BONUS_WEIGHT,
                DatabaseContract.ComparisonSetting.COLUMN_NAME_LEAVE_TIME_WEIGHT,
                DatabaseContract.ComparisonSetting.COLUMN_NAME_TRAINING_AND_DEVELOPMENT_FUND_WEIGHT,
                DatabaseContract.ComparisonSetting.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK_WEIGHT

        };

        // Query to fetch the latest comparison settings
        Cursor cursor = db.query(
                DatabaseContract.ComparisonSetting.TABLE_NAME, // The table to query
                projection, // The array of columns to return (pass null to get all)
                null, // The columns for the WHERE clause
                null, // The values for the WHERE clause
                null, // don't group the rows
                null, // don't filter by row groups
                DatabaseContract.ComparisonSetting._ID + " DESC", // The sort order
                "1" // Limit to 1 row
        );

        if (cursor != null && cursor.moveToFirst()) {
            int yearlySalaryWeight = cursor.getInt(
                    cursor.getColumnIndexOrThrow(DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_SALARY_WEIGHT));
            int yearlyBonusWeight = cursor.getInt(
                    cursor.getColumnIndexOrThrow(DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_BONUS_WEIGHT));
            int trainingDevelopmentFundWeight = cursor.getInt(cursor.getColumnIndexOrThrow(
                    DatabaseContract.ComparisonSetting.COLUMN_NAME_TRAINING_AND_DEVELOPMENT_FUND_WEIGHT));
            int leaveTimeWeight = cursor.getInt(
                    cursor.getColumnIndexOrThrow(DatabaseContract.ComparisonSetting.COLUMN_NAME_LEAVE_TIME_WEIGHT));
            int teleworkDaysPerWeekWeight = cursor.getInt(cursor.getColumnIndexOrThrow(
                    DatabaseContract.ComparisonSetting.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK_WEIGHT));

            yearlySalaryEditText.setText(String.valueOf(yearlySalaryWeight));
            yearlyBonusEditText.setText(String.valueOf(yearlyBonusWeight));
            trainingAndDevEditText.setText(String.valueOf(trainingDevelopmentFundWeight));
            leaveTimeEditText.setText(String.valueOf(leaveTimeWeight));
            teleworkDaysEditText.setText(String.valueOf(teleworkDaysPerWeekWeight));

            // Toast to debug
            Toast.makeText(EnterComparisonSettings.this,
                    "Yearly Salary Weight: " + yearlySalaryWeight + ", Yearly Bonus Weight: " + yearlyBonusWeight
                            + ", Training and Development Fund Weight: " + trainingDevelopmentFundWeight
                            + ", Leave Time Weight: " + leaveTimeWeight + ", Telework Days Per Week Weight: "
                            + teleworkDaysPerWeekWeight,
                    Toast.LENGTH_SHORT).show();

        } else {
            // no saved weights found in db so set default weight 1
            // TODO: this should be created as a db ComparisonSetting row from MainActivity
            int defaultWeight = 1;
            yearlySalaryEditText.setText(String.valueOf(defaultWeight));
            yearlyBonusEditText.setText(String.valueOf(defaultWeight));
            trainingAndDevEditText.setText(String.valueOf(defaultWeight));
            leaveTimeEditText.setText(String.valueOf(defaultWeight));
            teleworkDaysEditText.setText(String.valueOf(defaultWeight));
            Toast.makeText(getApplicationContext(), "Set default. No saved comparison settings.", Toast.LENGTH_LONG).show();
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    public void handleClickSaveWeights(View view) {
        String yearlySalary = yearlySalaryEditText.getText().toString().trim();
        String yearlyBonus = yearlyBonusEditText.getText().toString().trim();
        String trainingFund = trainingAndDevEditText.getText().toString().trim();
        String leaveTime = leaveTimeEditText.getText().toString().trim();
        String teleworkDays = teleworkDaysEditText.getText().toString().trim();

        boolean isValid = checkIfValid(yearlySalary, yearlyBonus, trainingFund, leaveTime, teleworkDays);
        if (!isValid) {
            return;
        }

        int yearlySalaryInt = Integer.parseInt(yearlySalary);
        int yearlyBonusInt = Integer.parseInt(yearlyBonus);
        int trainingFundInt = Integer.parseInt(trainingFund);
        int leaveTimeInt = Integer.parseInt(leaveTime);
        int teleworkDaysInt = Integer.parseInt(teleworkDays);

        // get singleton instance
        dbHelper = JobDbHelper.getInstance(this);

        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_SALARY_WEIGHT, yearlySalaryInt);
        values.put(DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_BONUS_WEIGHT, yearlyBonusInt);
        values.put(DatabaseContract.ComparisonSetting.COLUMN_NAME_LEAVE_TIME_WEIGHT, leaveTimeInt);
        values.put(DatabaseContract.ComparisonSetting.COLUMN_NAME_TRAINING_AND_DEVELOPMENT_FUND_WEIGHT,
                trainingFundInt);
        values.put(DatabaseContract.ComparisonSetting.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK_WEIGHT,
                teleworkDaysInt);

        // Update the weights in the existing row in ComparisonSetting table
        db.update(DatabaseContract.ComparisonSetting.TABLE_NAME, values, null,
                null);

        Toast.makeText(this, "Weights updated", Toast.LENGTH_LONG).show();
    }
}
