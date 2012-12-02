package stackpointer.jobs;

/**
 * @author Andrew
 */
public interface IJobPostingScorer {

    public int computeScore(JobPosting jobPosting);
    
}
