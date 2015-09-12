package hello.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Greeting {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private Date date;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    protected Greeting() {}

    public Greeting(String firstName, Date date) {
        this.firstName = firstName;
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Greetings " + firstName + " from Spring Boot!";
    }
}
