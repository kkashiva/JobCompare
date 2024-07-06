package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

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

            // Insert the new row, returning the primary key value of the new row
            long jobId = db.insert(DatabaseContract.Jobs.TABLE_NAME, null, values);

    }

    // method to update current job in DB
    public void updateCurrentJob(String title, String company, String locationState, String locationCity,
                                 Integer costOfLiving, Integer yearlySalary, Integer yearlyBonus,
                                 Integer trainDevFund, Integer leaveDay, Integer teleworkDaysPerWeek) {
            
            JobDbHelper dbHelper = JobDbHelper.getInstance(null);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

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

            // some derived variables
            double AYS = this.calculateAdjustedYearlySalary(yearlySalary, costOfLiving);
            double AYB = this.calculateAdjustedYearlyBonus(yearlyBonus, costOfLiving);
            List<Integer> adjustedParameter = super.getAdjustedParameter();
            double score = this.calculateJobScore(adjustedParameter, AYS, AYB, trainDevFund, leaveDay, teleworkDaysPerWeek);

            values.put(DatabaseContract.Jobs.COLUMN_NAME_AYS, AYS);
            values.put(DatabaseContract.Jobs.COLUMN_NAME_AYB, AYB);
            values.put(DatabaseContract.Jobs.COLUMN_NAME_SCORE, score);

            // debug AYS, AYB, score
            System.out.println("AYS: " + AYS);
            System.out.println("AYB: " + AYB);
            System.out.println("Score: " + score);

            // WHERE clause to filter jobType = 0, to only update the current jobs in Jobs table
            String selection = DatabaseContract.Jobs.COLUMN_NAME_JOB_TYPE + " = ?";
            String[] selectionArgs = { "0" };

            db.update(
                    DatabaseContract.Jobs.TABLE_NAME,
                    values,
                    selection,
                    selectionArgs);
    }

    // method to get current job from DB
    public void getCurrentJobDetails() {
        JobDbHelper dbHelper = JobDbHelper.getInstance(null);
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
                DatabaseContract.Jobs.TABLE_NAME, // FROM
                projection, // SELECT
                selection, // WHERE columns
                selectionArgs, // WHERE values
                null, // GROUP BY
                null, // filter by row groups
                null // SORT BY
        );

        if (cursor != null && cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_TITLE));
            String company = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_COMPANY));
            String city = cursor
                    .getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_LOCATION_CITY));
            String state = cursor
                    .getString(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_LOCATION_STATE));
            int costOfLiving = cursor
                    .getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_COST_OF_LIVING));
            int yearlySalary = cursor
                    .getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_YEARLY_SALARY));
            int yearlyBonus = cursor
                    .getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_YEARLY_BONUS));
            int trainingDevelopmentFund = cursor
                    .getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_TRAINING_DEVELOPMENT_FUND));
            int leaveTime = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_LEAVE_TIME));
            int teleworkDaysPerWeek = cursor
                    .getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Jobs.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK));

            this.setTitle(title);
            this.setCompany(company);
            this.setCity(city);
            this.setState(state);
            this.setCostOfLiving(costOfLiving);
            this.setYearlySalary(yearlySalary);
            this.setYearlyBonus(yearlyBonus);
            this.setTrainingDevelopmentFund(trainingDevelopmentFund);
            this.setLeaveTime(leaveTime);
            this.setTeleworkDaysWeekly(teleworkDaysPerWeek);
            
        }

        if (cursor != null) {
            cursor.close();
        }
    }
}
