package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class CurrentJob extends Job{
    
    // singleton instance
    private static CurrentJob instance;

    // private constructor
    private CurrentJob(String title, String company, String locationState, String locationCity, Integer costOfLiving,
                       Integer yearlySalary, Integer yearlyBonus, Integer trainDevFund,
                       Integer leaveDay, Integer teleworkDaysPerWeek, Integer jobType) {
        super(title, company, locationState, locationCity, costOfLiving, yearlySalary, yearlyBonus, trainDevFund, leaveDay, teleworkDaysPerWeek, jobType);
    }

    // public method to get the instance
    public static CurrentJob getInstance(String title, String company, String locationState, String locationCity, Integer costOfLiving,
                                         Integer yearlySalary, Integer yearlyBonus, Integer trainDevFund,
                                         Integer leaveDay, Integer teleworkDaysPerWeek, Integer jobType) {
        if (instance == null) {
            instance = new CurrentJob(title, company, locationState, locationCity, costOfLiving, yearlySalary, yearlyBonus, trainDevFund, leaveDay, teleworkDaysPerWeek, jobType);
        }
        return instance;
    }

    // method save current job in DB
    public void saveCurrentJob(String title, String company, String locationState, String locationCity,
                               Integer costOfLiving, Integer yearlySalary, Integer yearlyBonus,
                               Integer trainDevFund, Integer leaveDay, Integer teleworkDaysPerWeek) {
            
            JobDbHelper dbHelper = JobDbHelper.getInstance(null);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            int jobType = 0;

            ContentValues values = new ContentValues();
            values.put(DatabaseContract.Jobs.COLUMN_NAME_TITLE, title);
            values.put(DatabaseContract.Jobs.COLUMN_NAME_LOCATION_STATE, locationState);
            values.put(DatabaseContract.Jobs.COLUMN_NAME_LOCATION_CITY, locationCity);
            values.put(DatabaseContract.Jobs.COLUMN_NAME_COMPANY, company);
            values.put(DatabaseContract.Jobs.COLUMN_NAME_COST_OF_LIVING, costOfLiving);
            values.put(DatabaseContract.Jobs.COLUMN_NAME_YEARLY_SALARY, yearlySalary);
            values.put(DatabaseContract.Jobs.COLUMN_NAME_YEARLY_BONUS, yearlyBonus);
            values.put(DatabaseContract.Jobs.COLUMN_NAME_TRAINING_DEVELOPMENT_FUND, trainDevFund);
            values.put(DatabaseContract.Jobs.COLUMN_NAME_LEAVE_TIME, leaveDay);
            values.put(DatabaseContract.Jobs.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK, teleworkDaysPerWeek);
            values.put(DatabaseContract.Jobs.COLUMN_NAME_JOB_TYPE, jobType); // 0 for CurrentJob, 1 for JobOffer

            // some derived variables
            values.put(DatabaseContract.Jobs.COLUMN_NAME_AYS, getAYS());
            values.put(DatabaseContract.Jobs.COLUMN_NAME_AYB, getAYB());
            values.put(DatabaseContract.Jobs.COLUMN_NAME_SCORE, getJobScore());

            // Insert the new row, returning the primary key value of the new row
            long jobId = db.insert(DatabaseContract.Jobs.TABLE_NAME, null, values);

    }
}
