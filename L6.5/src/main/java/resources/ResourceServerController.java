package resources;

import resources.AccountServerControllerMBean;
import resources.ITestResource;

/**
 * @author a.akbashev
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class ResourceServerController implements AccountServerControllerMBean {
    private final ITestResource testResource;

    public ResourceServerController(ITestResource testResource) {
        this.testResource = testResource;
    }

    @Override
    public int getAge() {
        return testResource.getAge();
    }

    @Override
    public String getName() {
        return testResource.getName();
    }

}
