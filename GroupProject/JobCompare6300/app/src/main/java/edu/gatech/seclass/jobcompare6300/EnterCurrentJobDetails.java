package edu.gatech.seclass.jobcompare6300;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EnterCurrentJobDetails extends AppCompatActivity {
    private EditText inputTitle, inputCompany, inputState, inputCity,inputLivingCost,
            inputYearlySalary,inputYearlyBonus,inputTrainingDevelopment,inputLeaveTime, inputTelework;

    private String title, company, locationState, locationCity, costOfLiving, yearlySalary, yearlyBonus, trainDevFund, leaveTime, teleworkDay;
    private Integer costOfLivingInt, yearlySalaryInt, yearlyBonusInt, trainDevFundInt, leaveTimeInt, teleworkDayInt;
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
        Button buttonSaveJob = findViewById(R.id.saveJobButtonID);
//        Button backToMainMenuButtonID = findViewById(R.id.backToMainMenuButtonID);

        buttonSaveJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = inputTitle.getText().toString().trim();
                company = inputCompany.getText().toString().trim();
                locationState = inputState.getText().toString().trim();
                locationCity = inputCity.getText().toString().trim();
                costOfLiving = inputLivingCost.getText().toString().trim();
                yearlySalary = inputYearlySalary.getText().toString().trim();
                yearlyBonus = inputYearlyBonus.getText().toString().trim();
                trainDevFund = inputTrainingDevelopment.getText().toString().trim();
                leaveTime = inputLeaveTime.getText().toString().trim();
                teleworkDay = inputTelework.getText().toString().trim();

                boolean isValid = checkIfValid(title, company, locationState, locationCity,
                        costOfLiving, yearlySalary, yearlyBonus, trainDevFund,leaveTime, teleworkDay);
                if(!isValid)
                {
                    return;
                }

                costOfLivingInt = Integer.parseInt(costOfLiving);
                yearlySalaryInt = Integer.parseInt(yearlySalary);
                yearlyBonusInt = Integer.parseInt(yearlyBonus);
                trainDevFundInt = Integer.parseInt(trainDevFund);
                leaveTimeInt = Integer.parseInt(leaveTime);
                teleworkDayInt = Integer.parseInt(teleworkDay);

                handleClickSaveJob(v);
            }
        });
    }

    private boolean checkIfValid(String title, String company, String locationState, String locationCity, String livingCost,
                                 String yearlySalary, String yearlyBonus, String trainingFund, String leaveTime, String teleworkDays) {
        boolean isValid = true;
        if(title.isEmpty()){
            inputTitle.setError("Title is empty");
            isValid = false;
        }
        if(company.isEmpty()){
            inputCompany.setError("Company is empty");
            isValid = false;
        }
        if(locationState.isEmpty()){
            inputState.setError("Location - State is empty");
            isValid = false;
        }
        if(locationCity.isEmpty()){
            inputCity.setError("Location - City is empty");
            isValid = false;
        }
        if(livingCost.isEmpty()){
            inputLivingCost.setError("Cost of Living Index is empty");
            isValid = false;
        }
        if(yearlySalary.isEmpty()){
            inputYearlySalary.setError("Yearly Salary is empty");
            isValid = false;
        }
        if(yearlyBonus.isEmpty()){
            inputYearlyBonus.setError("Yearly Bonus is empty");
            isValid = false;
        }
        if(trainingFund.isEmpty()){
            inputTrainingDevelopment.setError("Training Fund is empty");
            isValid = false;
        }
        if(Integer.parseInt(trainingFund) < 0 || Integer.parseInt(trainingFund) > 18000){
            inputTrainingDevelopment.setError("$0 to $18000 inclusive annually");
            isValid = false;
        }
        if(leaveTime.isEmpty()){
            inputLeaveTime.setError("Leave Time is empty");
            isValid = false;
        }
        if(Integer.parseInt(leaveTime) < 0 || Integer.parseInt(leaveTime) > 100){
            inputTrainingDevelopment.setError("0 - 100 inclusive annually");
            isValid = false;
        }
        if(teleworkDays.isEmpty()){
            inputTelework.setError("Telework Days is empty");
            isValid = false;
        }
        if(Integer.parseInt(teleworkDays) < 0 || Integer.parseInt(teleworkDays) > 5){
            inputTrainingDevelopment.setError("0 - 5 inclusive weekly");
            isValid = false;
        }
        return isValid;
    }




    public void handleClickBackToMainMenu(View view) {
        startActivity(new Intent(EnterCurrentJobDetails.this, MainActivity.class));
    }

    public void handleClickSaveJob(View view) {
        // Instantiate our subclass of SQLiteOpenHelper
        JobDbHelper dbHelper = new JobDbHelper(this);

        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //Create a new job object
        Job job = new Job(title, company, locationState, locationCity, costOfLivingInt,
                yearlySalaryInt, yearlyBonusInt, trainDevFundInt, leaveTimeInt, teleworkDayInt);

        // Create a new map of values, where column names are the keys
        // hardcoding values to test, once input fields are added these values will be dynamic based on user input
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Jobs.COLUMN_NAME_TITLE, job.getTitle());
        values.put(DatabaseContract.Jobs.COLUMN_NAME_COMPANY, job.getCompany());
        values.put(DatabaseContract.Jobs.COLUMN_NAME_LOCATION_CITY, job.getCity());
        values.put(DatabaseContract.Jobs.COLUMN_NAME_LOCATION_STATE, job.getState());
        values.put(DatabaseContract.Jobs.COLUMN_NAME_COST_OF_LIVING, job.getCostOfLiving());
        values.put(DatabaseContract.Jobs.COLUMN_NAME_YEARLY_SALARY, job.getYearlySalary());
        values.put(DatabaseContract.Jobs.COLUMN_NAME_YEARLY_BONUS, job.getYearlyBonus());
        values.put(DatabaseContract.Jobs.COLUMN_NAME_TRAINING_DEVELOPMENT_FUND, job.getTrainingDevelopmentFund());
        values.put(DatabaseContract.Jobs.COLUMN_NAME_LEAVE_TIME, job.getLeaveTime());
        values.put(DatabaseContract.Jobs.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK, job.getTeleworkDaysPerWeek());
        values.put(DatabaseContract.Jobs.COLUMN_NAME_JOB_TYPE, 0); // 0 for CurrentJob, 1 for JobOffer
        
        // some derived variables
        values.put(DatabaseContract.Jobs.COLUMN_NAME_AYS, job.getAYS());
        values.put(DatabaseContract.Jobs.COLUMN_NAME_AYB, job.getAYB());
        values.put(DatabaseContract.Jobs.COLUMN_NAME_SCORE, job.getJobScore());

        // Insert the new row, returning the primary key value of the new row
        long jobId = db.insert(DatabaseContract.Jobs.TABLE_NAME, null, values);

        // show the jobId in a toast
        Toast.makeText(this, "Job ID: " + jobId, Toast.LENGTH_SHORT).show();

    }
}
