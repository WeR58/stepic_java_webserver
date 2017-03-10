package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import org.eclipse.jetty.http.MimeTypes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by WeDin on 08.03.2017.
 */
public class ServletHelper {

    public static boolean isValidateUserProfile
            (UserProfile userProfile, AccountService accountService, HttpServletResponse response) throws ServletException, IOException {
        return isValidateUserAccount(userProfile, accountService, response);
    }

    public static boolean isUserProfileIsNull(UserProfile userProfile, HttpServletResponse response) {
        return isLoginOrAccountNull(userProfile, response);
    }

    public static boolean isLoginOrAccountNull(UserProfile userProfile, HttpServletResponse response) {
        if (userProfile.getLogin() == null || userProfile.getPassword() == null) {
            response.setContentType(MimeTypes.Type.TEXT_HTML_UTF_8.asString());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return true;
        }
        return false;
    }

    public static boolean isValidateUserAccount
            (UserProfile userProfile, AccountService accountService, HttpServletResponse response) throws ServletException, IOException {
        if (accountService.containsUserByLogin(userProfile.getLogin())
                && accountService.getUserByLogin(userProfile.getLogin()).getPassword().equals(userProfile.getPassword())) {
            response.setContentType(MimeTypes.Type.TEXT_HTML_UTF_8.asString());
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print("Authorized: " + userProfile.getLogin());
            return true;
        }
        return false;
    }

    public static void unauthorizedView(HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(MimeTypes.Type.TEXT_HTML_UTF_8.asString());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().print("Unauthorized");

    }

    public static UserProfile CreateUserProfile(HttpServletRequest request) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        return new UserProfile(login, password);
    }
}
