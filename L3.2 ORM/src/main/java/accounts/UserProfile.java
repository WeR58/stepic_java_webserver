package accounts;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Random;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
@Entity
@Table(name = "users")
public class UserProfile implements Serializable {
    private static final long serialVersionUID = -8706623434326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public UserProfile() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public UserProfile(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public UserProfile(String login, String password) {
        this.id = Math.abs(new Random().nextLong());
        this.login = login;
        this.password = password;
        this.email = login + "@mail.com";
    }

    @SuppressWarnings("UnusedDeclaration")
    public UserProfile(String login) {
        this.login = login;
        this.password = login;
        this.email = login + "@mail.com";
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "login=" + login +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
