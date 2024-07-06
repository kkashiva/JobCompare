package edu.gatech.seclass.jobcompare6300;

public interface Observer {

    void updateWeights(int yearlySalaryWeight, int yearlyBonusWeight, int trainingAndDevWeight, int leaveTimeWeight, int teleworkDaysWeight);
}
