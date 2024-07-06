package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ComparisonSettings {
    
    private static ComparisonSettings instance; // Singleton instance
    
    private int yearlySalaryWeight;
    private int yearlyBonusWeight;
    private int trainingAndDevWeight;
    private int leaveTimeWeight;
    private int teleworkDaysWeight;

    // private constructor
    private ComparisonSettings() {
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

}
