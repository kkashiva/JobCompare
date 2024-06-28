package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class EditCurrentJobDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_current_job_details);
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
        JobDbHelper dbHelper = new JobDbHelper(this);
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
                DatabaseContract.Jobs.TABLE_NAME,   // FROM
                projection,                         // SELECT
                selection,                          // WHERE columns
                selectionArgs,                      // WHERE values
                null,                       // GROUP BY
                null,                        // filter by row groups
                null                        // SORT BY
        );
    
        if (cursor != null && cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_TITLE));
            String company = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_COMPANY));
            String city = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_LOCATION_CITY));
            String state = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_LOCATION_STATE));
            int costOfLiving = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_COST_OF_LIVING));
            double yearlySalary = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_YEARLY_SALARY));
            double yearlyBonus = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_YEARLY_BONUS));
            double trainingDevelopmentFund = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_TRAINING_DEVELOPMENT_FUND));
            int leaveTime = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_LEAVE_TIME));
            int teleworkDaysPerWeek = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK));
                
            // Construct the message
            String message = "Title: " + title + ", Company: " + company + ", City: " + city + ", State: " + state + ", COL: " + costOfLiving + ", Salary: " + yearlySalary + ", Bonus: " + yearlyBonus + ", TDF: " + trainingDevelopmentFund + ", Leave: " + leaveTime + ", Telework: " + teleworkDaysPerWeek;
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            cursor.close();
        } else {
            Toast.makeText(this, "No current job details found.", Toast.LENGTH_LONG).show();
        }
    }


}