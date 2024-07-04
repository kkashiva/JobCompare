package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CompareJobOffers extends AppCompatActivity {
    private final JobDbHelper dbHelper = JobDbHelper.getInstance(this); //get singleton instance;
    private final SQLiteDatabase db = dbHelper.getReadableDatabase();
    private Set<Integer> storeIds; // store the ids of offer to query from table

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        storeIds = new HashSet<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_job_offers);

        ListView listView = findViewById(R.id.listViewId);

        Cursor cursor = db.rawQuery("SELECT _id, title, company, score, jobType FROM Job ORDER BY Score DESC", null);

        ArrayList<JobOffer> jobOffersList = new ArrayList<>();

        int rank = 0;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_TITLE));
            String company = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_COMPANY));
            int jobType = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_JOB_TYPE));
            jobOffersList.add(new JobOffer(id, title, company, ++rank, jobType == 0));
        }

        JobOfferListAdapter adapter = new JobOfferListAdapter(this, R.layout.adapter_view_ranking_table_layout, jobOffersList);
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                for (int i = 0; i < adapter.getCheckedStates().size(); i++) {
                    if (adapter.getCheckedStates().get(i).isChecked()) {
                        storeIds.add(adapter.getCheckedStates().get(i).getId());
                    }
                }
            }
        });
        listView.setAdapter(adapter);

        cursor.close();
    }

    public void handleClickBackToMainMenu(View view) {
        startActivity(new Intent(CompareJobOffers.this, MainActivity.class));
    }

    public void handleClickCompareActionButton(View view) {
        Intent intent = new Intent(CompareJobOffers.this, Display2JobOffers.class);
        // We are going to assume storeIds only contains 2 ids

        int count = 0;
        for (Integer storeId : storeIds) {
            Cursor cursor = db.rawQuery("SELECT * FROM Job WHERE _id = " + storeId, null);
            if (cursor != null && cursor.moveToFirst()){
                String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_TITLE));
                String company = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_COMPANY));
                String locationState = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_LOCATION_STATE));
                String locationCity = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_LOCATION_CITY));
                int costOfLiving = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_COST_OF_LIVING));
                int salary = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_YEARLY_SALARY));
                int bonus = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_YEARLY_BONUS));
                int fund = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_TRAINING_DEVELOPMENT_FUND));
                int leaveTime = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_LEAVE_TIME));
                int tele = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK));

                Job job = new Job(title, company, locationState, locationCity, costOfLiving, salary, bonus, fund, leaveTime, tele, 1);
                intent.putExtra("offer" + ++count, job);
                cursor.close();
            }

        }

        startActivity(intent);
    }
}