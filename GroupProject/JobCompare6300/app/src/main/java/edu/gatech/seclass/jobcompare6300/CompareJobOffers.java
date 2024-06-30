package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class CompareJobOffers extends AppCompatActivity {

    private JobDbHelper dbHelper;
    private static final String TAG = "CompareJobOffers";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_job_offers);
        Log.d(TAG, "onCreate: started");

        dbHelper = JobDbHelper.getInstance(this); //get singleton instance
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ListView listView = findViewById(R.id.listViewId);

        String[] projection = {
                DatabaseContract.Jobs.COLUMN_NAME_TITLE,
                DatabaseContract.Jobs.COLUMN_NAME_COMPANY,
                DatabaseContract.Jobs.COLUMN_NAME_SCORE,
        };

        Cursor cursor = db.query(
                DatabaseContract.Jobs.TABLE_NAME,   // FROM
                projection,                         // SELECT
                null,                          // WHERE columns
                null,                      // WHERE values
                null,                       // GROUP BY
                null,                        // filter by row groups
                DatabaseContract.Jobs.COLUMN_NAME_SCORE  // SORT BY
        );

        JobOffer jobOffer1 = new JobOffer("title1", "company1", "San Jose", "CA", 1, 1, 1,1, 1, 1,1);
        JobOffer jobOffer2 = new JobOffer("title2", "company2", "San Jose", "CA", 1, 1,1, 1, 1, 1,1);
        JobOffer jobOffer3 = new JobOffer("title3", "company4", "San Jose", "CA", 1, 1,1, 1, 1, 1,1);
        JobOffer jobOffer4 = new JobOffer("title4", "company3", "San Jose", "CA", 1, 1,1, 1, 1, 1,1);
        JobOffer jobOffer5 = new JobOffer("title5", "company5", "San Jose", "CA", 1, 1,1, 1, 1, 1,1);

        ArrayList<JobOffer> jobOffersList = new ArrayList<>();
        jobOffersList.add(jobOffer1);
        jobOffersList.add(jobOffer2);
        jobOffersList.add(jobOffer3);
        jobOffersList.add(jobOffer4);
        jobOffersList.add(jobOffer5);

//        TODO: Right now there's no indication for current job. How do we achieve that via UI?
        JobOfferListAdapter adapter = new JobOfferListAdapter(this, R.layout.adapter_view_ranking_table_layout, jobOffersList);
        listView.setAdapter(adapter);
    }

    public void handleClickBackToMainMenu(View view) {
        startActivity(new Intent(CompareJobOffers.this, MainActivity.class));
    }

    public void handleClickCompareActionButton(View view) {
    }

//    TODO: refactor this method
    public void handleClickBackToCompareJobOffersScreen(View view) {
        startActivity(new Intent(CompareJobOffers.this, MainActivity.class));
    }

    // close the database in onDestroy()
    // @Override
    // protected void onDestroy() {
    //     dbHelper.close();
    //     super.onDestroy();
    // }

}