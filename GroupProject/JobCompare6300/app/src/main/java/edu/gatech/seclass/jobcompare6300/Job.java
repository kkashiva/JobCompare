package edu.gatech.seclass.jobcompare6300;

import java.util.Arrays;
import java.util.List;

public class Job {
    private static int idCounter = 0;
    private int jobID;
    private String title;
    private String company;
    private String locationState;
    private String locationCity;
    private Integer costOfLiving;
    private Integer yearlySalary;
    private Integer yearlyBonus;
    private Integer trainDevFund;
    private Integer leaveDay;
    private Integer teleworkDaysPerWeek;

    // derived characters
    private String location;
    private Double AYS;
    private Double AYB;
    private Double score;

    // Hardcoded parameters; need to change in next Phase
    private final List<Integer> adjustedParameter = Arrays.asList(1, 1, 1, 1, 1);


    public Job(String title, String company, String locationState, String locationCity, Integer costOfLiving,
               Integer yearlySalary, Integer yearlyBonus, Integer trainDevFund,
               Integer leaveDay, Integer teleworkDaysPerWeek) {
        this.jobID = ++idCounter;
        this.title = title;
        this.company = company;
        this.locationState = locationState;
        this.locationCity = locationCity;
        this.costOfLiving = costOfLiving;
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.trainDevFund = trainDevFund;
        this.leaveDay = leaveDay;
        this.teleworkDaysPerWeek = teleworkDaysPerWeek;

        this.location = locationState + ',' + locationCity;
        this.AYS = getAdjustedYearlySalary(yearlySalary, costOfLiving);
        this.AYB = getAdjustedYearlyBonus(yearlyBonus, costOfLiving);
        this.score = calculateJobScore(adjustedParameter, AYS, AYB, trainDevFund, leaveDay, teleworkDaysPerWeek);
    }

    // set functions
    public void setTitle(String title) {
        this.title = title;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public void setState(String locationState) {
        this.locationState = locationState;
    }
    public void setCity(String locationCity) {
        this.locationCity = locationCity;
    }
    public void setCostOfLiving(Integer costOfLiving) {
        this.costOfLiving = costOfLiving;
    }
    public void setYearlySalary(Integer yearlySalary) {
        this.yearlySalary = yearlySalary;
    }
    public void setYearlyBonus(Integer yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }
    public void setTrainingDevelopmentFund(Integer trainDevFund) {
        this.trainDevFund = trainDevFund;
    }
    public void setLeaveTime(Integer leaveDay) {
        this.leaveDay = leaveDay;
    }
    public void setTeleworkDaysWeekly(Integer teleworkDaysPerWeek) {
        this.teleworkDaysPerWeek = teleworkDaysPerWeek;
    }


    // get functions
    public String getTitle() {
        return title;
    }
    public String getCompany() {
        return company;
    }
    public String getState() {
        return locationState;
    }
    public String getCity() {
        return locationCity;
    }
    public Integer getCostOfLiving() {
        return costOfLiving;
    }
    public Integer getYearlySalary() {
        return yearlySalary;
    }
    public Integer getYearlyBonus() {
        return yearlyBonus;
    }
    public Integer getTrainingDevelopmentFund() {
        return trainDevFund;
    }
    public Integer getLeaveTime() {
        return leaveDay;
    }
    public Integer getTeleworkDaysPerWeek() {
        return teleworkDaysPerWeek;
    }

    // Derived Variable functions
    private void updateLocation() {
        this.location = this.locationState + ", " + this.locationCity;
    }
    public String getLocation() {
        return location;
    }
    public Double getAdjustedYearlySalary(Integer yearlySalary, Integer costOfLiving){
        AYS = (double) ((yearlySalary * 100)/ costOfLiving);
        return AYS;
    }
    public Double getAdjustedYearlyBonus(Integer yearlyBonus, Integer costOfLiving){
        AYB = (double) ((yearlyBonus * 100)/ costOfLiving);
        return AYB;
    }

    public Double calculateJobScore(List<Integer> adjustedParameter, Double AYS, Double AYB,
                                  Integer trainDevFund, Integer leaveDay, Integer teleworkDaysPerWeek){
        Double valueOfEmpHour = (Double) (AYS / 260) / 8;
        Double yearlyCommuterHour = (260 - 52 * teleworkDaysPerWeek) * 1.0;
        Double travelTimeCost = (Double) valueOfEmpHour * yearlyCommuterHour;
        score = AYS + AYB + trainDevFund + leaveDay * valueOfEmpHour * 8 - travelTimeCost;
        return score;
    }
    public void setJobScore(Double score){
        this.score = score;
    }
    public Double getJobScore(){
        return score;
    }

}
