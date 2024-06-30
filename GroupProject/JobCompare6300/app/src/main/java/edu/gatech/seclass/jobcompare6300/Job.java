package edu.gatech.seclass.jobcompare6300;

import java.util.Arrays;
import java.util.List;

public class Job {
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

    private Integer jobType;

    // Hardcoded parameters; need to change in next Phase
    private final List<Integer> adjustedParameter = Arrays.asList(1, 1, 1, 1, 1);

    public Job(String title, String company, String locationState, String locationCity, Integer costOfLiving,
               Integer yearlySalary, Integer yearlyBonus, Integer trainDevFund,
               Integer leaveDay, Integer teleworkDaysPerWeek, Integer jobType) {
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
        this.AYS = calculateAdjustedYearlySalary(this.yearlySalary, this.costOfLiving);
        this.AYB = calculateAdjustedYearlyBonus(this.yearlyBonus, this.costOfLiving);
        this.score = calculateJobScore(adjustedParameter, this.AYS, this.AYB, this.trainDevFund, this.leaveDay, this.teleworkDaysPerWeek);
        this.jobType = jobType;
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
    public void setAYS(Double AYS){
        this.AYS = AYS;
    }
    public void setAYB(Double AYB){
        this.AYB = AYB;
    }
    public void setJobScore(Double score){
        this.score = score;
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
    public String getLocation() {
        return location;
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

    public Double getAYS(){
        return AYS;
    }
    public Double getAYB(){
        return AYB;
    }
    public Double getJobScore(){
        return score;
    }
    public Integer getJobType(){
        return jobType;
    }

    // Derived Variable functions - Calculations
    private void updateLocation() {
        this.location = this.locationState + ", " + this.locationCity;
    }

    public Double calculateAdjustedYearlySalary(Integer yearlySalary, Integer costOfLiving){
        return (double) ((yearlySalary * 100)/ costOfLiving);
    }
    public Double calculateAdjustedYearlyBonus(Integer yearlyBonus, Integer costOfLiving){
        return (double) ((yearlyBonus * 100)/ costOfLiving);
    }

    public Double calculateJobScore(List<Integer> adjustedParameter, Double AYS, Double AYB,
                                    Integer trainDevFund, Integer leaveDay, Integer teleworkDaysPerWeek){
        double valueOfEmpHour = (Double) (AYS / 260) / 8;
        double yearlyCommuterHour = (260 - 52 * teleworkDaysPerWeek) * 1.0;
        double travelTimeCost = (Double) valueOfEmpHour * yearlyCommuterHour;

        int totalParam = 0;
        for (int param: adjustedParameter){
            totalParam += param;
        }
        double AYSParam = (double) (adjustedParameter.get(0)/totalParam);
        double AYBParam = (double) (adjustedParameter.get(1)/totalParam);
        double TDFParam = (double) (adjustedParameter.get(2)/totalParam);
        double LTParam = (double) (adjustedParameter.get(3)/totalParam);
        double commuteCostParam = (double) (adjustedParameter.get(4)/totalParam);

//        return (double) AYSParam * AYS + AYBParam * AYB + TDFParam * trainDevFund +
//                LTParam * leaveDay * valueOfEmpHour * 8 - commuteCostParam * travelTimeCost;
        // put the non-weighted version as of now. Need more changes
        return (double) AYS + AYB + trainDevFund + leaveDay * valueOfEmpHour * 8 - travelTimeCost;
    }
}
