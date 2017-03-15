package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import dbService.DBException;
import dbService.DBService;
import org.eclipse.jetty.http.MimeTypes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by WeDin on 08.03.2017.
 */
public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        UserProfile profile = ServletHelper.CreateUserProfile(request);

        if (ServletHelper.isLoginOrAccountNull(profile, response)) {
            return;
        }


        if (ServletHelper.isValidateUserAccount(profile, accountService, response)) {
            return;
        }

        String sessionId = request.getSession().getId();

        accountService.addNewUser(profile);
        accountService.addSession(sessionId, profile);

        DBService dbService = new DBService();
        dbService.printConnectInfo();

        try {
            dbService.addUser(profile);
            System.out.println("Added user id: " + profile);
        } catch (DBException e) {
            e.printStackTrace();
        }

        try {
            List<UserProfile> dataSet = dbService.getUsers();
            System.out.println("User data set: " + dataSet);
        } catch (DBException e) {
            System.out.println(e.getMessage());
        }
        response.setContentType(MimeTypes.Type.TEXT_HTML_UTF_8.asString());
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("SignedUp");

    }

}
