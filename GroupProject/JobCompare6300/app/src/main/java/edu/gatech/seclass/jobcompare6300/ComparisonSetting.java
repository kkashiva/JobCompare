package edu.gatech.seclass.jobcompare6300;

public class ComparisonSetting {
    private final int yearlySalaryWeight;
    private final int yearlyBonusWeight;
    private final int trainingAndDevelopmentFundWeight;
    private final int leaveTimeWeight;
    private final int teleworkDaysPerWeekWeight;

    public ComparisonSetting(int yearlySalaryWeight, int yearlyBonusWeight, int trainingAndDevelopmentFundWeight, int leaveTimeWeight, int teleworkDaysPerWeekWeight) {
        this.yearlySalaryWeight = yearlySalaryWeight;
        this.yearlyBonusWeight = yearlyBonusWeight;
        this.trainingAndDevelopmentFundWeight = trainingAndDevelopmentFundWeight;
        this.leaveTimeWeight = leaveTimeWeight;
        this.teleworkDaysPerWeekWeight = teleworkDaysPerWeekWeight;
    }

    public int getYearlySalaryWeight() {
        return yearlySalaryWeight;
    }

    public int getYearlyBonusWeight() {
        return yearlyBonusWeight;
    }

    public int getTrainingAndDevelopmentFundWeight() {
        return trainingAndDevelopmentFundWeight;
    }

    public int getLeaveTimeWeight() {
        return leaveTimeWeight;
    }

    public int getTeleworkDaysPerWeekWeight() {
        return teleworkDaysPerWeekWeight;
    }
}
