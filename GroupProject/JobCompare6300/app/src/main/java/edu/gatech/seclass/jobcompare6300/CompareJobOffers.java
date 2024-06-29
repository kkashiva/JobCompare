package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class CompareJobOffers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_job_offers);

        JobDbHelper dbHelper = JobDbHelper.getInstance(this); //get singleton instance
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ListView listView = findViewById(R.id.listViewId);

        String[] projection = {
                DatabaseContract.Jobs.COLUMN_NAME_TITLE,
                DatabaseContract.Jobs.COLUMN_NAME_COMPANY,
                DatabaseContract.Jobs.COLUMN_NAME_SCORE,
                DatabaseContract.Jobs.COLUMN_NAME_JOB_TYPE,
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

        ArrayList<JobOffer> jobOffersList = new ArrayList<>();
        int rank = 0;
        if (cursor != null) {
            cursor.moveToFirst();
            while (cursor != null) {
                String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_TITLE));
                String company = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_COMPANY));
                int jobType = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_JOB_TYPE));
                jobOffersList.add(new JobOffer(title, company, rank++, jobType==0));
                cursor.moveToNext();
            }

//        TODO: Right now there's no indication for current job. How do we achieve that via UI?
            JobOfferListAdapter adapter = new JobOfferListAdapter(this, R.layout.adapter_view_ranking_table_layout, jobOffersList);
            listView.setAdapter(adapter);

            cursor.close();
        }
    }

    public void handleClickBackToMainMenu(View view) {
        startActivity(new Intent(CompareJobOffers.this, MainActivity.class));
    }

    public void handleClickCompareActionButton(View view) {
        startActivity(new Intent(CompareJobOffers.this, Display2JobOffers.class));
    }
}