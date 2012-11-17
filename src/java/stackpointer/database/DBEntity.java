package stackpointer.database;

/**
 * @author Andrew
 */
public interface DBEntity {
    
    /**
     * Prepares the entity for its data to be added or updated in the database.
     * Consistency checks and enforcement are done here such as truncating
     * text, converting nulls to blanks, etc.
     */
    public void prepare();
    
}
