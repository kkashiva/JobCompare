package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JobDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "JobDatabase.db";

    // Singleton pattern
    private static JobDbHelper instance;
    // making the constructor private to ensure singleton; only one instance of JobDbHelper
    private JobDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // public method to get the instance of JobDbHelper
    public static synchronized JobDbHelper getInstance(Context context) {
        // if no instance exists, create one
        if (instance == null) {
            instance = new JobDbHelper(context.getApplicationContext());
        }
        // if instance exists, return it instead of creating another
        return instance;
    }

    // CREATE TABLE query for the Job table
    private static final String SQL_CREATE_JOB_ENTRIES =
            "CREATE TABLE " + DatabaseContract.Jobs.TABLE_NAME + " (" +
                    DatabaseContract.Jobs._ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.Jobs.COLUMN_NAME_TITLE + " TEXT," +
                    DatabaseContract.Jobs.COLUMN_NAME_COMPANY + " TEXT," +
                    DatabaseContract.Jobs.COLUMN_NAME_LOCATION_STATE + " TEXT," +
                    DatabaseContract.Jobs.COLUMN_NAME_LOCATION_CITY + " TEXT," +
                    DatabaseContract.Jobs.COLUMN_NAME_COST_OF_LIVING + " INTEGER," +
                    DatabaseContract.Jobs.COLUMN_NAME_YEARLY_SALARY + " INTEGER," +
                    DatabaseContract.Jobs.COLUMN_NAME_YEARLY_BONUS + " INTEGER," +
                    DatabaseContract.Jobs.COLUMN_NAME_TRAINING_DEVELOPMENT_FUND + " INTEGER," +
                    DatabaseContract.Jobs.COLUMN_NAME_LEAVE_TIME + " INTEGER," +
                    DatabaseContract.Jobs.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK + " INTEGER," +
                    DatabaseContract.Jobs.COLUMN_NAME_JOB_TYPE + " INTEGER," +
                    DatabaseContract.Jobs.COLUMN_NAME_AYS + " REAL," +
                    DatabaseContract.Jobs.COLUMN_NAME_AYB + " REAL," +
                    DatabaseContract.Jobs.COLUMN_NAME_SCORE + " REAL)";

    // CREATE TABLE query for the ComparisonSetting table in the same db
    String SQL_CREATE_COMPARISON_SETTING_ENTRIES =
            "CREATE TABLE " + DatabaseContract.ComparisonSetting.TABLE_NAME + " (" +
                    DatabaseContract.ComparisonSetting._ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_SALARY_WEIGHT + " INTEGER," +
                    DatabaseContract.ComparisonSetting.COLUMN_NAME_YEARLY_BONUS_WEIGHT + " INTEGER," +
                    DatabaseContract.ComparisonSetting.COLUMN_NAME_TRAINING_AND_DEVELOPMENT_FUND_WEIGHT + " INTEGER," +
                    DatabaseContract.ComparisonSetting.COLUMN_NAME_LEAVE_TIME_WEIGHT + " INTEGER," +
                    DatabaseContract.ComparisonSetting.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK_WEIGHT + " INTEGER)";

    private static final String SQL_DELETE_JOB =
            "DROP TABLE IF EXISTS " + DatabaseContract.Jobs.TABLE_NAME;

    private  static final String SQL_DELETE_COMPARISON_SETTING =
            "DROP TABLE IF EXISTS " + DatabaseContract.ComparisonSetting.TABLE_NAME;

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_JOB_ENTRIES);
        db.execSQL(SQL_CREATE_COMPARISON_SETTING_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database's upgrade policy to simply discard the data and start over
        db.execSQL(SQL_DELETE_JOB);
        db.execSQL(SQL_DELETE_COMPARISON_SETTING);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
