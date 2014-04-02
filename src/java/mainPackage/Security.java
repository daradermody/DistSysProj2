/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

import java.util.ArrayList;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

/**
 *
 * @author Dara Dermody, Niko Flores, Emma Foley and Patrick Stapleton
 */
public class Security {

    private static ArrayList<User> users = new ArrayList<>();
    final private static long TIMEOUT = 900;

    /**
     * Checks the validity of the username and corresponding password entered by
     * the user.
     *
     * @param username Username to check
     * @param password Password to check
     * @return Returns true if the user exists and the password is the
     * corresponding password for this user and false otherwise
     */
    public static boolean verifyUser(String username, String password) {
        boolean validity = false;
        // Uncomment this when Emma decides on her class name.
        /**
         * if(username != null && password != null) validity =
         * forumBoard.verifyUser(username, password);
         */
        return validity;
    }

    /**
     * Starts the session for the specified user, with the current time and a
     * universally unique ID
     *
     * @param username Username of the user for which the session is to be
     * started
     * @return Returns the unique session ID associated with the user the
     * session was created for; null, otherwise
     */
    public static String startSession(String username) {
        boolean exists = false;
        String ID = null; // ID value to return; if person already has session, return null

        if (username != null) {
            // Cycle through sessions recorded for existence of user
            for (User user : users) {
                if (user.username.equals(username)) {
                    exists = true;
                    break;
                }
            }

            // If no session exists for user, create session ID and add to user sessions list
            if (exists == false) {
                UUID uniqueID = UUID.randomUUID(); // Creates the universally unique session ID
                Integer seconds = (int) (long) (System.currentTimeMillis() / 1000); // Retrieves the current time in milliseconds and converts into an integer
                users.add(new User(username, String.valueOf(uniqueID), seconds)); // Adds the user into the list of logged in users
                ID = String.valueOf(uniqueID); // Return session ID
            }
        }
        return ID;
    }

    /**
     * Verifies the session by checking that the user is logged in and that the
     * timer for this user is less than 15 minutes
     *
     * @param sessionID Session ID to be verified
     * @return Returns the username associated with the sessionID if the session
     * exists; null, otherwise
     */
    public static String verifySession(String sessionID) {
        String username = null;
        
        if (sessionID != null) {
            // Cycle through each user to find User object associated with given session ID
            for (User user : users)
                if (user.sessionID.equals(sessionID)) {
                    // Retrieves the current time in milliseconds and converts into an integer
                    Integer currentSeconds = (int) (long) (System.currentTimeMillis() / 1000);
                    // Calculates the elapsed time by subtracting the current time (in seconds) from the user's timestamp
                    long elapsedTime = currentSeconds - user.getTimestamp(); 
                    
                    // If the elapsed time is greater than 15 minutes (15 * 60 seconds = 900)
                    if (elapsedTime <= TIMEOUT) {
                        user.setTimestamp(currentSeconds); // Replaces the old user with the current one, with the new timestamp
                        username =  user.username;
                    } else
                        endSession(sessionID); // Ends the session if the timer is longer than 15 minutes
                }
        }
        return username;
    }

    /**
     * Ends the session with the specified session ID
     *
     * @param sessionID Session ID of the session to be ended
     * @return Returns true of the session was properly closed; false, if not
     */
    public static boolean endSession(String sessionID) {
        boolean success = false;
        
        if (sessionID != null) {
            // Cycle through each user to find User object associated with given session ID
            for (User user : users) {
                if (user.sessionID.equals(sessionID)) { // User found
                    users.remove(users.indexOf(user)); // Removes the user session with the specified session ID
                    success = true;
                }
            }
        }
        
        return success;
    }

    /**
     *
     * @param request
     * @return
     */
    public static String[] authoriseRequest(HttpServletRequest request) {
        int USER = 0;
        int ID = 1;
        String userInfo[] = {null, null};

        // Check if there is a session cookie
        Cookie[] cookies = request.getCookies();
        userInfo[ID] = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    userInfo[ID] = sanitise(cookie.getValue());
                }
            }
        }
        // If no session cookie, check for session parameter in URL
        if (userInfo[ID] == null || userInfo[ID].equals("")) {
            userInfo[ID] = sanitise(request.getParameter("id"));
        }

        // If no session ID, check for username and password details
        userInfo[USER] = verifySession(userInfo[ID]);
        if (userInfo[USER] == null) {
            userInfo[USER] = sanitise(request.getParameter("username"));
            String password = sanitise(request.getParameter("password"));
            System.out.println(userInfo[USER]);
            System.out.println(password);
            // If no username or password, redirect to login page
            if (verifyUser(userInfo[USER], password)) {
                userInfo[ID] = startSession(userInfo[USER]);
            } else {
                userInfo[ID] = null;
            }
        }

        return userInfo;
    }
    
    public static String sanitise(String str) {
        String sanitisedStr;

        /* Example of building custome policy:
        HtmlPolicyBuilder policyBuilder1 = new HtmlPolicyBuilder();
        new HtmlPolicyBuilder()
            .allowElements("a")
            .allowUrlProtocols("https")
            .allowAttributes("href").onElements("a")
            .requireRelNofollowOnLinks()
        
        PolicyFactory policy = policyBuilder1.toFactory();
        policy.sanitize(inputString);
        */
        
        PolicyFactory stripAllTagsPolicy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        sanitisedStr = stripAllTagsPolicy.sanitize(str);
        
        return sanitisedStr;
    }
}
