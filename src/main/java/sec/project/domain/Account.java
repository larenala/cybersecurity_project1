
package sec.project.domain;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Account extends AbstractPersistable<Long>{
    private String firstname;
    private String lastname;
    private String username;
    private String password;  
    private String creditcard;
    
    public Account () {
        this.firstname = "First";
        this.lastname = "Last";
        this.username = "user";
        this.password = "password";
        this.creditcard = "12345678910";
    }
    
    public Account (String firstname, String lastname, String username, String password, String creditcard) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.creditcard = creditcard;
    }
    
    public String getUsername () {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username=username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password=password;
    }
}

