package servlets;

import main.ReadXMLFileSAX;
import resources.ITestResource;
import org.eclipse.jetty.http.MimeTypes;
import resources.TestResource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author a.akbashev
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class HomePageServlet extends HttpServlet {
    public static final String PAGE_URL = "/resources";
    private ITestResource testResource;

    public HomePageServlet(ITestResource testResource) {
        this.testResource = testResource;
    }

    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        response.setContentType(MimeTypes.Type.TEXT_HTML_UTF_8.asString());

        String path = request.getParameter("path");

        testResource = (TestResource) ReadXMLFileSAX.readXML(path);

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
