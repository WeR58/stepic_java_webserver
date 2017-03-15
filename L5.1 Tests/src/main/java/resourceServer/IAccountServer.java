package resourceServer;

/**
 * @author v.chibrikov
 *         <p/>
 *         Пример кода для курса на https://stepic.org/
 *         <p/>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public interface IAccountServer {
    void addNewUser();

    void removeUser();

    int getUsersLimit();

    void setUsersLimit(int usersLimit);

    int getUsersCount();
}
