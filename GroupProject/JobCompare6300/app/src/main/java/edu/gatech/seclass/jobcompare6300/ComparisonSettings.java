package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ComparisonSettings implements Subject{
    
    private static ComparisonSettings instance; // Singleton instance
    
    private int yearlySalaryWeight;
    private int yearlyBonusWeight;
    private int trainingAndDevWeight;
    private int leaveTimeWeight;
    private int teleworkDaysWeight;

    private List<Observer> observers; // list to store all observer (Jobs)

    // private constructor
    private ComparisonSettings() {
        observers = new ArrayList<Observer>();
        getWeightsFromDB();
    }

    // single pattern getInstance method
    public static ComparisonSettings getInstance() {
        if (instance == null) {
            instance = new ComparisonSettings();
        }
        return instance;
    }
    
    public void getWeightsFromDB(){
        JobDbHelper dbHelper = JobDbHelper.getInstance(null);
        dbHelper = JobDbHelper.getInstance(null);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_SALARY_WEIGHT,
                DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_BONUS_WEIGHT,
                DatabaseContract.ComparisonSetting.COLUMN_NAME_LEAVE_TIME_WEIGHT,
                DatabaseContract.ComparisonSetting.COLUMN_NAME_TRAINING_AND_DEVELOPMENT_FUND_WEIGHT,
                DatabaseContract.ComparisonSetting.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK_WEIGHT

        };

        // Query to fetch saved comparison settings from db
        Cursor cursor = db.query(
                DatabaseContract.ComparisonSetting.TABLE_NAME, // The table to query
                projection, // The array of columns to return (pass null to get all)
                null, // The columns for the WHERE clause
                null, // The values for the WHERE clause
                null, // don't group the rows
                null, // don't filter by row groups
                DatabaseContract.ComparisonSetting._ID + " DESC", // The sort order
                "1" // Limit to 1 row
        );

        if (cursor != null && cursor.moveToFirst()) {
            yearlySalaryWeight = cursor.getInt(
                    cursor.getColumnIndexOrThrow(DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_SALARY_WEIGHT));
            yearlyBonusWeight = cursor.getInt(
                    cursor.getColumnIndexOrThrow(DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_BONUS_WEIGHT));
            trainingAndDevWeight = cursor.getInt(cursor.getColumnIndexOrThrow(
                    DatabaseContract.ComparisonSetting.COLUMN_NAME_TRAINING_AND_DEVELOPMENT_FUND_WEIGHT));
            leaveTimeWeight = cursor.getInt(
                    cursor.getColumnIndexOrThrow(DatabaseContract.ComparisonSetting.COLUMN_NAME_LEAVE_TIME_WEIGHT));
            teleworkDaysWeight = cursor.getInt(cursor.getColumnIndexOrThrow(
                    DatabaseContract.ComparisonSetting.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK_WEIGHT));
        } else {
            // if no saved weights found in db so display default weight 1
            int defaultWeight = 1;
            yearlySalaryWeight = defaultWeight;
            yearlyBonusWeight = defaultWeight;
            trainingAndDevWeight = defaultWeight;
            leaveTimeWeight = defaultWeight;
            teleworkDaysWeight = defaultWeight;
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    public void updateWeightsInDB(int yearlySalaryInt, int yearlyBonusInt, int trainingFundInt, int leaveTimeInt, int teleworkDaysInt){
        JobDbHelper dbHelper = JobDbHelper.getInstance(null);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_SALARY_WEIGHT, yearlySalaryInt);
        values.put(DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_BONUS_WEIGHT, yearlyBonusInt);
        values.put(DatabaseContract.ComparisonSetting.COLUMN_NAME_LEAVE_TIME_WEIGHT, leaveTimeInt);
        values.put(DatabaseContract.ComparisonSetting.COLUMN_NAME_TRAINING_AND_DEVELOPMENT_FUND_WEIGHT, trainingFundInt);
        values.put(DatabaseContract.ComparisonSetting.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK_WEIGHT, teleworkDaysInt);

        // Update the weights in the existing row in ComparisonSetting table
        db.update(DatabaseContract.ComparisonSetting.TABLE_NAME, values, null, null);

        // push updated weights to all observers
        notifyObservers();

    }

    // method to get all jobs from DB and register them as observers 
    public void getObservers(){
        JobDbHelper dbHelper = JobDbHelper.getInstance(null);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
            DatabaseContract.Job._ID,
            DatabaseContract.Job.COLUMN_NAME_AYS,
            DatabaseContract.Job.COLUMN_NAME_AYB,
            DatabaseContract.Job.COLUMN_NAME_TRAINING_DEVELOPMENT_FUND,
            DatabaseContract.Job.COLUMN_NAME_LEAVE_TIME,
            DatabaseContract.Job.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK
        };

        // Query to fetch all jobs from db
        Cursor cursor = db.query(
                DatabaseContract.Job.TABLE_NAME, // The table to query
                projection, // The array of columns to return (pass null to get all)
                null, // The columns for the WHERE clause
                null, // The values for the WHERE clause
                null, // don't group the rows
                null, // don't filter by row groups
                null // The sort order
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int jobId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Job._ID));
                int AYS = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Job.COLUMN_NAME_AYS));
                int AYB = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Job.COLUMN_NAME_AYB));
                int trainDevFund = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Job.COLUMN_NAME_TRAINING_DEVELOPMENT_FUND));
                int leaveDay = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Job.COLUMN_NAME_LEAVE_TIME));
                int teleworkDaysPerWeek = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Job.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK));

                // create job object and register it as observer
                Job job = new Job(jobId, AYS, AYB, trainDevFund, leaveDay, teleworkDaysPerWeek);
                registerObserver(job);
            }
            cursor.close();
        }
    }

    // getters
    public int getYearlySalaryWeight() {
        return yearlySalaryWeight;
    }

    public int getYearlyBonusWeight() {
        return yearlyBonusWeight;
    }

    public int getTrainingAndDevWeight() {
        return trainingAndDevWeight;
    }

    public int getLeaveTimeWeight() {
        return leaveTimeWeight;
    }

    public int getTeleworkDaysWeight() {
        return teleworkDaysWeight;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers){
            observer.updateWeights(yearlySalaryWeight, yearlyBonusWeight, trainingAndDevWeight, leaveTimeWeight, teleworkDaysWeight);
        }
    }
}
