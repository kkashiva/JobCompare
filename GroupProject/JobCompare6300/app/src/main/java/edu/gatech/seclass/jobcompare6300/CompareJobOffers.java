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

    private JobDbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_job_offers);

        dbHelper = JobDbHelper.getInstance(this); //get singleton instance
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ListView listView = findViewById(R.id.listViewId);

        String query = "SELECT title, company, score, jobType FROM Job ORDER BY Score DESC";
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<JobOffer> jobOffersList = new ArrayList<>();

        int rank = 0;
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_TITLE));
            String company = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_COMPANY));
            int jobType = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_JOB_TYPE));
            jobOffersList.add(new JobOffer(title, company, ++rank, jobType==0));
        }

        JobOfferListAdapter adapter = new JobOfferListAdapter(this, R.layout.adapter_view_ranking_table_layout, jobOffersList);
        listView.setAdapter(adapter);

        cursor.close();
    }

    public void handleClickBackToMainMenu(View view) {
        startActivity(new Intent(CompareJobOffers.this, MainActivity.class));
    }

    public void handleClickCompareActionButton(View view) {
        ListView listView = findViewById(R.id.listViewId);


        startActivity(new Intent(CompareJobOffers.this, Display2JobOffers.class));
    }
}