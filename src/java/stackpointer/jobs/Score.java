package stackpointer.jobs;

/**
 * @author Andrew
 */
public class Score {

    private int score;
    private JobPosting jobPosting;

    public Score(JobPosting jobPosting) {
        this.score = 0;
        this.jobPosting = jobPosting;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public JobPosting getJobPosting() {
        return this.jobPosting;
    }
    
    public void compute(IJobPostingScorer scorer) {
        this.score = scorer.computeScore(jobPosting);
    }
    
}
