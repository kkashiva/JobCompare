package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JobDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "JobDatabase.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DatabaseContract.Jobs.TABLE_NAME + " (" +
                    DatabaseContract.Jobs._ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.Jobs.COLUMN_NAME_JOB_ID + " TEXT," +
                    DatabaseContract.Jobs.COLUMN_NAME_TITLE + " TEXT," +
                    DatabaseContract.Jobs.COLUMN_NAME_COMPANY + " TEXT," +
                    DatabaseContract.Jobs.COLUMN_NAME_LOCATION_STATE + " TEXT," +
                    DatabaseContract.Jobs.COLUMN_NAME_LOCATION_CITY + " TEXT," +
                    DatabaseContract.Jobs.COLUMN_NAME_COST_OF_LIVING + " INTEGER," +
                    DatabaseContract.Jobs.COLUMN_NAME_YEARLY_SALARY + " REAL," +
                    DatabaseContract.Jobs.COLUMN_NAME_YEARLY_BONUS + " REAL," +
                    DatabaseContract.Jobs.COLUMN_NAME_TRAINING_DEVELOPMENT_FUND + " REAL," +
                    DatabaseContract.Jobs.COLUMN_NAME_LEAVE_TIME + " INTEGER," +
                    DatabaseContract.Jobs.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK + " INTEGER," +
                    DatabaseContract.Jobs.COLUMN_NAME_JOB_TYPE + " INTEGER," +
                    DatabaseContract.Jobs.COLUMN_NAME_AYS + " REAL," +
                    DatabaseContract.Jobs.COLUMN_NAME_AYB + " REAL," +
                    DatabaseContract.Jobs.COLUMN_NAME_SCORE + " REAL)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseContract.Jobs.TABLE_NAME;

    public JobDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database's upgrade policy to simply discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
