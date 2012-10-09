/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package stackpointer.database;

/**
 *
 * @author Andrew
 */
public class DatabaseConnectionInfo {
    String url;
    String username;
    String password;

    public DatabaseConnectionInfo(String url,
            String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "URL: " + url;
    }
    
    public static DatabaseConnectionInfo createDefault() {
        return new DatabaseConnectionInfo(
                "jdbc:mysql://mysql.philhurwitz.com/stackpointer",
                "philhurwitzcom",
                "zv4NLgN6");
    }
}
