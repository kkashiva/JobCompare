package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private JobDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = JobDbHelper.getInstance(this); // get singleton instance
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        /*
         * Query Job table for any existing current job (jobType == 0).
         * current job = 1, disable enter current job details.
         * current job = 0, disable edit current job details.
         */
        String query = "SELECT * FROM Job WHERE jobType = 0";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() == 1) {
            Button enterCurrentJobButton = findViewById(R.id.enterCurrentJobDetailsButtonID);
            enterCurrentJobButton.setEnabled(false);

            Button editCurrentJobButton = findViewById(R.id.editCurrentJobDetailsButtonID);
            editCurrentJobButton.setEnabled(true);
        }
        if (cursor.getCount() == 0) {
            Button editCurrentJobButton = findViewById(R.id.editCurrentJobDetailsButtonID);
            editCurrentJobButton.setEnabled(false);

            Button enterCurrentJobButton = findViewById(R.id.enterCurrentJobDetailsButtonID);
            enterCurrentJobButton.setEnabled(true);
        }
        else {
            cursor.close();
        }

        /*
         * Query Job table for any existing jobOffer (jobType == 1).
         * If there's no job offer, disable Compare Job Offer button.
         */
        query = "SELECT * FROM Job WHERE jobType = 1";
        cursor = db.rawQuery(query, null);

        if (cursor == null || cursor.getCount() == 0) {
            Button compareJobOfferButton = findViewById(R.id.compareJobOffersButtonID);
            compareJobOfferButton.setEnabled(false);
        } else {
            cursor.close();
        }

        /*
         * Query ComparisonSetting table for existing weights setting
         * If no existing rows in table, then save new row with default weights 1
         */
        String[] projection2 = {
                DatabaseContract.ComparisonSetting._ID };

        Cursor cursor2 = db.query(
                DatabaseContract.ComparisonSetting.TABLE_NAME, // FROM
                projection2, // SELECT
                null, // WHERE columns
                null, // WHERE values
                null, // GROUP BY
                null, // filter by row groups
                null // SORT BY
        );

        if (cursor2 == null || cursor2.getCount() == 0) {
            saveDefaultComparisonSettings();
        } else {
            cursor2.close();
        }
    }

    public void saveDefaultComparisonSettings() {
        dbHelper = JobDbHelper.getInstance(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues defaultWeightValues = new ContentValues();
        defaultWeightValues.put(DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_SALARY_WEIGHT, "1");
        defaultWeightValues.put(DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_BONUS_WEIGHT, "1");
        defaultWeightValues.put(DatabaseContract.ComparisonSetting.COLUMN_NAME_TRAINING_AND_DEVELOPMENT_FUND_WEIGHT, "1");
        defaultWeightValues.put(DatabaseContract.ComparisonSetting.COLUMN_NAME_LEAVE_TIME_WEIGHT, "1");
        defaultWeightValues.put(DatabaseContract.ComparisonSetting.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK_WEIGHT, "1");

        db.insert(DatabaseContract.ComparisonSetting.TABLE_NAME, null, defaultWeightValues);
    }

    public void handleClickEnterCurrentJobDetails(View view) {
        startActivity(new Intent(MainActivity.this, EnterCurrentJobDetails.class));
    }

    public void handleClickEditCurrentJobDetails(View view) {
        startActivity(new Intent(MainActivity.this, EditCurrentJobDetails.class));
    }

    public void handleClickEnterJobOffers(View view) {
        startActivity(new Intent(MainActivity.this, EnterJobOffers.class));
    }

    public void handleClickAdjustComparisonSettings(View view) {
        startActivity(new Intent(MainActivity.this, EnterComparisonSettings.class));
    }

    public void handleClickCompareJobOffer(View view) {
        startActivity(new Intent(MainActivity.this, CompareJobOffers.class));
    }

    // close the database in onDestroy()
    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}