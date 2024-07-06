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
        
        // get singleton instance of ComparisonSettings
        ComparisonSettings currentSettings = ComparisonSettings.getInstance();
        currentSettings.getWeightsFromDB(); // get the saved weights from db

        yearlySalaryEditText.setText(String.valueOf(currentSettings.getYearlySalaryWeight()));
        yearlyBonusEditText.setText(String.valueOf(currentSettings.getYearlyBonusWeight()));
        trainingAndDevEditText.setText(String.valueOf(currentSettings.getTrainingAndDevWeight()));
        leaveTimeEditText.setText(String.valueOf(currentSettings.getLeaveTimeWeight()));
        teleworkDaysEditText.setText(String.valueOf(currentSettings.getTeleworkDaysWeight()));

        // Toast to debug
        Toast.makeText(EnterComparisonSettings.this,
                "Yearly Salary Weight: " + currentSettings.getYearlySalaryWeight() + ", Yearly Bonus Weight: " + currentSettings.getYearlyBonusWeight()
                        + ", Training and Development Fund Weight: " + currentSettings.getTrainingAndDevWeight()
                        + ", Leave Time Weight: " + currentSettings.getLeaveTimeWeight() + ", Telework Days Per Week Weight: "
                        + currentSettings.getTeleworkDaysWeight(),
                Toast.LENGTH_SHORT).show();

        
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

        // get singleton instance of ComparisonSettings
        ComparisonSettings updatedSettings = ComparisonSettings.getInstance();

        updatedSettings.getObservers(); // register all jobs from DB as observers to be notified of weight changes

        // call method to update weights in DB table ComparisonSettings
        updatedSettings.updateWeightsInDB(yearlySalaryInt, yearlyBonusInt, trainingFundInt, leaveTimeInt, teleworkDaysInt);

        // notify observers
        updatedSettings.notifyObservers();

        Toast.makeText(this, "Weights updated, recalculating job scores...", Toast.LENGTH_LONG).show();
    }
}
