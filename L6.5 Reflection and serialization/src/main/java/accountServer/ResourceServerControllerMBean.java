package accountServer;

/**
 * @author v.chibrikov
 */
@SuppressWarnings("UnusedDeclaration")
public interface ResourceServerControllerMBean {
    String getName();

    int getAge();

    void setName(String name);

    void setAge(int age);

}
