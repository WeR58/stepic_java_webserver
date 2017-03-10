package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import org.eclipse.jetty.http.MimeTypes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by WeDin on 08.03.2017.
 */
public class ServletHelper {

    public static boolean isValidateUserProfile
            (UserProfile userProfile, AccountService accountService, HttpServletResponse response) throws ServletException, IOException {
        return isValidateUserAccount(userProfile.getLogin(), userProfile.getPassword(), accountService, response);
    }

    public static boolean isUserProfileIsNull(UserProfile userProfile, HttpServletResponse response) {
        return isLoginOrAccountNull(userProfile.getLogin(), userProfile.getPassword(), response);
    }

    public static boolean isLoginOrAccountNull(String login, String password, HttpServletResponse response) {
        if (login == null || password == null) {
            response.setContentType(MimeTypes.Type.TEXT_HTML_UTF_8.asString());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return true;
        }
        return false;
    }

    public static boolean isValidateUserAccount
            (String login, String password, AccountService accountService, HttpServletResponse response) throws ServletException, IOException {
        if (accountService.containsUserByLogin(login) && accountService.getUserByLogin(login).getPassword().equals(password)) {
            response.setContentType(MimeTypes.Type.TEXT_HTML_UTF_8.asString());
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print("Authorized: " + login);
            return true;
        }
        return false;
    }

    public static void unauthorizedView(HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(MimeTypes.Type.TEXT_HTML_UTF_8.asString());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().print("Unauthorized");

    }
}
