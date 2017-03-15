package main;

import resources.ResourceServerController;
import resources.TestResource;
import resources.AccountServerControllerMBean;
import resources.ITestResource;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.HomePageServlet;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * @author a.akbashev
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class Main {

    public static void main(String[] args) throws Exception {

        ITestResource testResource = new TestResource();

        AccountServerControllerMBean serverStatistics = new ResourceServerController(testResource);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=ResourceServerController");
        mbs.registerMBean(serverStatistics, name);

        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new HomePageServlet(testResource)), HomePageServlet.PAGE_URL);


        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{context});
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started");

        server.join();
    }
}
