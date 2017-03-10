package dbService.dao;

import accounts.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UserProfile get(String login) throws HibernateException {
        return (UserProfile) session.get(UserProfile.class, login);
    }

    public String getUserLogin(String email) throws HibernateException {
        Criteria criteria = session.createCriteria(UserProfile.class);
        return ((UserProfile) criteria.add(Restrictions.eq("email", email)).uniqueResult()).getLogin();
    }

    public long insertUser(UserProfile userProfile) throws HibernateException {
        return (Long) session.save(userProfile);
    }

    public List<UserProfile> getUserProfiles() {
        Criteria criteria = session.createCriteria(UserProfile.class);
        return (List<UserProfile>) criteria.list();
    }
}
