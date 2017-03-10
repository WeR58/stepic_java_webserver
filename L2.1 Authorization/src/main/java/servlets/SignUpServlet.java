package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import org.eclipse.jetty.http.MimeTypes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (ServletHelper.isLoginOrAccountNull(login, password, response)) {
            return;
        }

        if (ServletHelper.isValidateUserAccount(login, password, accountService, response)) {
            return;
        }

        UserProfile userProfile = new UserProfile(login, password);
        String sessionId = request.getSession().getId();

        accountService.addNewUser(userProfile);
        accountService.addSession(sessionId, userProfile);

        response.setContentType(MimeTypes.Type.TEXT_HTML_UTF_8.asString());
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("SignedUp");

    }

}
