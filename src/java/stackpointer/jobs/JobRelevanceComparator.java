package stackpointer.jobs;

import java.util.Comparator;
import java.util.List;

/**
 * @author Andrew
 */
public class JobRelevanceComparator implements Comparator {

    private List<String> keywords;
    
    public JobRelevanceComparator(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public int compare(Object o1, Object o2) {
        Score a = (Score) o1;
        Score b = (Score) o2;
        return b.getScore() - a.getScore();
    }
    
}
