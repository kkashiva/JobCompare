package edu.gatech.seclass.jobcompare6300;

import android.provider.BaseColumns;

public final class DatabaseContract {
    // singleton with private constructor to avoid someone from accidentally instantiating the contract class
    private DatabaseContract () {}

    /* Inner class that defines the table contents */
    public static class Jobs implements BaseColumns {
        public static final String TABLE_NAME = "Job";
        public static final String COLUMN_NAME_JOB_ID = "jobID";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_COMPANY = "company";
        public static final String COLUMN_NAME_LOCATION_STATE = "locationState";
        public static final String COLUMN_NAME_LOCATION_CITY = "locationCity";
        public static final String COLUMN_NAME_COST_OF_LIVING = "costOfLiving";
        public static final String COLUMN_NAME_YEARLY_SALARY = "yearlySalary";
        public static final String COLUMN_NAME_YEARLY_BONUS = "yearlyBonus";
        public static final String COLUMN_NAME_TRAINING_DEVELOPMENT_FUND = "trainingDevelopmentFund";
        public static final String COLUMN_NAME_LEAVE_TIME = "leaveTime";
        public static final String COLUMN_NAME_TELEWORK_DAYS_PER_WEEK = "teleworkDaysPerWeek";
        public static final String COLUMN_NAME_JOB_TYPE = "jobType"; // Store as INTEGER (0 for CurrentJob, 1 for JobOffer)
        public static final String COLUMN_NAME_AYS = "AYS"; // Adjusted Yearly Salary
        public static final String COLUMN_NAME_AYB = "AYB"; // Adjusted Yearly Bonus
        public static final String COLUMN_NAME_SCORE = "Score";

    }

    /* class to define the table ComparisonSetting */
    public static class ComparisonSetting implements BaseColumns {
        public static final String TABLE_NAME = "ComparisonSetting";
        public static final String COLUMN_NAME_YEARLY_SALARY_WEIGHT = "yearlySalaryWeight";
        public static final String COLUMN_NAME_YEARLY_BONUS_WEIGHT = "yearlyBonusWeight";
        public static final String COLUMN_NAME_TRAINING_AND_DEVELOPMENT_FUND_WEIGHT = "trainingAndDevelopmentFundWeight";
        public static final String COLUMN_NAME_LEAVE_TIME_WEIGHT = "leaveTimeWeight";
        public static final String COLUMN_NAME_TELEWORK_DAYS_PER_WEEK_WEIGHT = "teleworkDaysPerWeekWeight";
    }
}
