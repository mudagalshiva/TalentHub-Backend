package com.talenthub.dto;

public class CandidateDashboardResponse {

    private long totalApplications;
    private long inProgress;
    private long shortlisted;
    private long savedJobs;

    public CandidateDashboardResponse() {
    }

    public CandidateDashboardResponse(
            long totalApplications,
            long inProgress,
            long shortlisted,
            long savedJobs) {

        this.totalApplications = totalApplications;
        this.inProgress = inProgress;
        this.shortlisted = shortlisted;
        this.savedJobs = savedJobs;
    }

    public long getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(long totalApplications) {
        this.totalApplications = totalApplications;
    }

    public long getInProgress() {
        return inProgress;
    }

    public void setInProgress(long inProgress) {
        this.inProgress = inProgress;
    }

    public long getShortlisted() {
        return shortlisted;
    }

    public void setShortlisted(long shortlisted) {
        this.shortlisted = shortlisted;
    }

    public long getSavedJobs() {
        return savedJobs;
    }

    public void setSavedJobs(long savedJobs) {
        this.savedJobs = savedJobs;
    }
}