package accountServer;

/**
 * @author v.chibrikov
 */
public interface ResourceServer {
    String getName();

    int getAge();

    void setName(String name);

    void setAge(int age);

    void readResource(String path);
}
