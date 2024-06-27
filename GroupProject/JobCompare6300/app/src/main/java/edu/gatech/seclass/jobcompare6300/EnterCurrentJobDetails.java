package edu.gatech.seclass.jobcompare6300;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class EnterCurrentJobDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_current_job_details);
    }

    public void handleClickBackToMainMenu(View view) {
        startActivity(new Intent(EnterCurrentJobDetails.this, MainActivity.class));
    }

    public void handleClickTestSave(View view) {
        // Instantiate our subclass of SQLiteOpenHelper
        JobDbHelper dbHelper = new JobDbHelper(this);

        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        // hardcoding values to test, onlce input fields are added these values will be dynamic based on user input
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Jobs.COLUMN_NAME_TITLE, "SDE");
        values.put(DatabaseContract.Jobs.COLUMN_NAME_COMPANY, "Test Company");
        values.put(DatabaseContract.Jobs.COLUMN_NAME_LOCATION_CITY, "Atlanta");
        values.put(DatabaseContract.Jobs.COLUMN_NAME_LOCATION_STATE, "GA");
        values.put(DatabaseContract.Jobs.COLUMN_NAME_COST_OF_LIVING, 200);
        values.put(DatabaseContract.Jobs.COLUMN_NAME_YEARLY_SALARY, 90000.00);
        values.put(DatabaseContract.Jobs.COLUMN_NAME_YEARLY_BONUS, 10000.00);
        values.put(DatabaseContract.Jobs.COLUMN_NAME_TRAINING_DEVELOPMENT_FUND, 5000.00);
        values.put(DatabaseContract.Jobs.COLUMN_NAME_LEAVE_TIME, 15);
        values.put(DatabaseContract.Jobs.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK, 2);
        values.put(DatabaseContract.Jobs.COLUMN_NAME_JOB_TYPE, 0); // 0 for CurrentJob, 1 for JobOffer
        
        // need to calculate AYS, AYB and Score

        // Insert the new row, returning the primary key value of the new row
        long jobId = db.insert(DatabaseContract.Jobs.TABLE_NAME, null, values);

        // show the jobId in a toast
        Toast.makeText(this, "Job ID: " + jobId, Toast.LENGTH_SHORT).show();

    }
}