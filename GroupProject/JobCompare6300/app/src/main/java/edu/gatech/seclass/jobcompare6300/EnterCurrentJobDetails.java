package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class EnterCurrentJobDetails extends AppCompatActivity {

    private EditText inputTitle, inputCompany, inputState, inputCity, inputLivingCost,
            inputYearlySalary, inputYearlyBonus, inputTrainingDevelopment, inputLeaveTime, inputTelework;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_current_job_details);

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

    private boolean checkIfValid(String title, String company, String locationState, String locationCity, String livingCost,
                                 String yearlySalary, String yearlyBonus, String trainingFund, String leaveTime, String teleworkDays) {
        boolean isValid = true;
        if (title.isEmpty()) {
            inputTitle.setError("Title is empty");
            isValid = false;
        }
        if (company.isEmpty()) {
            inputCompany.setError("Company is empty");
            isValid = false;
        }
        if (locationState.isEmpty()) {
            inputState.setError("Location - State is empty");
            isValid = false;
        }
        if (locationCity.isEmpty()) {
            inputCity.setError("Location - City is empty");
            isValid = false;
        }
        if (livingCost.isEmpty()) {
            inputLivingCost.setError("Cost of Living Index is empty");
            isValid = false;
        }
        if (yearlySalary.isEmpty()) {
            inputYearlySalary.setError("Yearly Salary is empty");
            isValid = false;
        }
        if (yearlyBonus.isEmpty()) {
            inputYearlyBonus.setError("Yearly Bonus is empty");
            isValid = false;
        }
        if (trainingFund.isEmpty()) {
            inputTrainingDevelopment.setError("Training Fund is empty");
            isValid = false;
        }
        if (Integer.parseInt(trainingFund) < 0 || Integer.parseInt(trainingFund) > 18000) {
            inputTrainingDevelopment.setError("$0 to $18000 inclusive annually");
            isValid = false;
        }
        if (leaveTime.isEmpty()) {
            inputLeaveTime.setError("Leave Time is empty");
            isValid = false;
        }
        if (Integer.parseInt(leaveTime) < 0 || Integer.parseInt(leaveTime) > 100) {
            inputTrainingDevelopment.setError("0 - 100 inclusive annually");
            isValid = false;
        }
        if (teleworkDays.isEmpty()) {
            inputTelework.setError("Telework Days is empty");
            isValid = false;
        }
        if (Integer.parseInt(teleworkDays) < 0 || Integer.parseInt(teleworkDays) > 5) {
            inputTrainingDevelopment.setError("0 - 5 inclusive weekly");
            isValid = false;
        }
        return isValid;
    }


    public void handleClickBackToMainMenu(View view) {
        startActivity(new Intent(EnterCurrentJobDetails.this, MainActivity.class));
    }

    public void handleClickSaveJob(View view) {
        String title = inputTitle.getText().toString().trim();
        String company = inputCompany.getText().toString().trim();
        String locationState = inputState.getText().toString().trim();
        String locationCity = inputCity.getText().toString().trim();
        String costOfLiving = inputLivingCost.getText().toString().trim();
        String yearlySalary = inputYearlySalary.getText().toString().trim();
        String yearlyBonus = inputYearlyBonus.getText().toString().trim();
        String trainDevFund = inputTrainingDevelopment.getText().toString().trim();
        String leaveTime = inputLeaveTime.getText().toString().trim();
        String teleworkDay = inputTelework.getText().toString().trim();

        boolean isValid = checkIfValid(title, company, locationState, locationCity,
                costOfLiving, yearlySalary, yearlyBonus, trainDevFund, leaveTime, teleworkDay);
        if (isValid) {
            Integer costOfLivingInt = Integer.parseInt(costOfLiving);
            Integer yearlySalaryInt = Integer.parseInt(yearlySalary);
            Integer yearlyBonusInt = Integer.parseInt(yearlyBonus);
            Integer trainDevFundInt = Integer.parseInt(trainDevFund);
            Integer leaveTimeInt = Integer.parseInt(leaveTime);
            Integer teleworkDayInt = Integer.parseInt(teleworkDay);

            // get singleton instance of CurrentJob
            int jobType = 0;
            CurrentJob currentJob = CurrentJob.getInstance(title, company, locationState, locationCity, costOfLivingInt,
                    yearlySalaryInt, yearlyBonusInt, trainDevFundInt, leaveTimeInt, teleworkDayInt, jobType); 

            // save the current job in the database
            currentJob.saveCurrentJob(title, company, locationState, locationCity, costOfLivingInt,
                    yearlySalaryInt, yearlyBonusInt, trainDevFundInt, leaveTimeInt, teleworkDayInt);

            // show the message in a toast
             Toast.makeText(this, "Successfully saved current job!", Toast.LENGTH_SHORT).show();

            // back to MainActivity after 1 second delay
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(EnterCurrentJobDetails.this, MainActivity.class));
                }
            }, 1000);
        }
        else{
            // show the failing message in a toast
            Toast.makeText(this, "Please adjust your inputs. ", Toast.LENGTH_SHORT).show();
        }

    }

}
