package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JobDbHelper dbHelper = new JobDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        /* Query Job table for any existing jobOffer (jobType == 1).
         * If there's no job offer, disable Compare Job Offer button.
         */
        String[] projection = {
                DatabaseContract.Jobs._ID,
        };

        String selection = DatabaseContract.Jobs.COLUMN_NAME_JOB_TYPE + " = 1";

        Cursor cursor = db.query(
                DatabaseContract.Jobs.TABLE_NAME,   // FROM
                projection,                         // SELECT
                selection,                          // WHERE columns
                null,                      // WHERE values
                null,                       // GROUP BY
                null,                        // filter by row groups
                null                        // SORT BY
        );

        if (cursor == null) {
            Button compareJobOfferButton = findViewById(R.id.compareJobOffersButtonID);
            compareJobOfferButton.setEnabled(false);
        }

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
}