package edu.gatech.seclass.jobcompare6300;

public class JobOffer {
    private final String title;
    private final String company;
    private final int rank;
    private final int id;
    private final boolean isCurrentJob;

    public JobOffer(int id, String title, String company, int rank, boolean isCurrentJob) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.rank = rank;
        this.isCurrentJob = isCurrentJob;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public int getRank() {
        return rank;
    }

    public int getId() {
        return id;
    }

    public boolean isCurrentJob() {
        return isCurrentJob;
    }
}
