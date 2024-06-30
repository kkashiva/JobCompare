package edu.gatech.seclass.jobcompare6300;

public class JobOffer{
    private final String title;
    private final String company;
    private final int rank;
    private final boolean isCurrentJob;

    public JobOffer(String title, String company, int rank, boolean isCurrentJob) {
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

    public boolean isCurrentJob() {
        return isCurrentJob;
    }
}
