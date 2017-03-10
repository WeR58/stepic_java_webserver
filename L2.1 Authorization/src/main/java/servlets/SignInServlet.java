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
public class SignInServlet extends HttpServlet {
    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getSession().getId();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserProfile profile = accountService.getUserBySessionId(sessionId);

        if (!ServletHelper.isLoginOrAccountNull(login, password, response)) {
            profile = new UserProfile(login, password);
        }

        if (profile == null) {
            ServletHelper.unauthorizedView(response);
            return;
        }

        if (ServletHelper.isUserProfileIsNull(profile, response)) {
            return;
        }

        if (ServletHelper.isValidateUserProfile(profile, accountService, response)) {
            return;
        }

    }
}
