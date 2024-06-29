package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

//                boolean isInserted = databaseHelper.insertComparisonSettings(yearlySalaryInt, yearlyBonusInt, trainingFundInt, leaveTimeInt, teleworkDaysInt);
//                if (isInserted) {
//                    Toast.makeText(EnterComparisonSettings.this, "Comparison settings saved Successfully.", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(EnterComparisonSettings.this, "Comparison settings not Saved", Toast.LENGTH_LONG).show();
//                }
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

}
