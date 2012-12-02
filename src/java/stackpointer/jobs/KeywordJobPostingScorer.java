package stackpointer.jobs;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Andrew
 */
public class KeywordJobPostingScorer implements IJobPostingScorer{

    public final int HeadlineWeight = 5;
    public final int DescriptionWeight = 1;
    
    private List<String> keywords;

    public KeywordJobPostingScorer(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public int computeScore(JobPosting jobPosting) {
        int score = 0;
        String headline = jobPosting.getHeadline();
        String description = jobPosting.getDescription();
        
        // Check each keyword to see if it's in the headline or description
        for (String k : keywords) {
            Pattern pattern = Pattern.compile(Pattern.quote(k), Pattern.CASE_INSENSITIVE);
            
            // Headline
            if (headline != null && !headline.isEmpty()) {
                if (pattern.matcher(headline).find()) {
                    score += HeadlineWeight;
                }
            }
            
            // Description
            if (description != null && !description.isEmpty()) {
                if (pattern.matcher(description).find()) {
                    score += DescriptionWeight;
                }
            }
        }
        
        return score;
    }
    
}
