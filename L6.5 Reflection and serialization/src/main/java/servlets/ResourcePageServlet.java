package servlets;

import accountServer.ResourceServer;
import org.eclipse.jetty.http.MimeTypes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author v.chibrikov
 */
public class ResourcePageServlet extends HttpServlet {
    public static final String PAGE_URL = "/resources";
    private final ResourceServer resourceServer;

    public ResourcePageServlet(ResourceServer resourceServer) {
        this.resourceServer = resourceServer;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path");
        response.setContentType(MimeTypes.Type.TEXT_HTML_UTF_8.asString());

        String prefixPath = "./L6.5 Reflection and serialization/src/resources/";
        String fullPath = prefixPath + path;
        String forTest = prefixPath + "resource.xml";
        resourceServer.readResource(path);
        response.getWriter().println("Resource loaded from path: " + path);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
