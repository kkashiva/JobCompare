package edu.gatech.seclass.jobcompare6300;

public class JobOffer extends Job{
    Integer jobType = 1;
    public JobOffer(String title, String company, String city, String state, int costOfLiving, int yearlySalary, int yearlyBonus, int trainingDevelopmentFund, int leaveTime, int teleworkDaysPerWeek, int jobType) {
        super(title, company, city, state, costOfLiving, yearlySalary, yearlyBonus, trainingDevelopmentFund, leaveTime, teleworkDaysPerWeek, jobType);
    }
}
