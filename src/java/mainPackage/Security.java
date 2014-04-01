/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mainPackage;

import java.util.ArrayList;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author daradermody
 */
public class Security {
    private static ArrayList<User> users = new ArrayList<>();
    
    public static boolean verifyUser(String username, String password){
        boolean valid = false;
        System.out.printf("username: %s | password: %s\n", username, password);
        if(username != null && password != null && (username.equals("jamie") && password.equals("isgay")))
            valid = true; // Return success if user is verified
            
        return valid;
    }
    
    public static String startSession(String username) {
        return "id1234"; // Return session ID
    }
    
    public static String verifySession(String sessionID) {
        System.out.println("Session ID: "+ sessionID);
        if(sessionID != null && sessionID.equals("id1234"))
            return "jamie"; // Returns username or 'null' if session unverified
        else
            return null;
    }
    
    public static boolean endSession(String sessionID) {
        return true; // Return true if session ID successfully removed from array list
    } 
    
    /**
     *
     * @param request
     * @return 
     */
    public static String[] authoriseRequest(HttpServletRequest request) {
        int USER = 0; int ID = 1;
        String userInfo[] = {null, null};
        
        // Check if there is a session cookie
        Cookie[] cookies = request.getCookies();
        userInfo[ID] = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    userInfo[ID] = cookie.getValue();
                }
            }
        }
        // If no session cookie, check for session parameter in URL
        if(userInfo[ID] == null || userInfo[ID].equals(""))
            userInfo[ID] = request.getParameter("id");

        // If no session ID, check for username and password details
        userInfo[USER] = verifySession(userInfo[ID]);
        if(userInfo[USER] == null) {
            userInfo[USER] = request.getParameter("username");
            String password = request.getParameter("password");

            // If no username or password, redirect to login page
            if(verifyUser(userInfo[USER], password))
                userInfo[ID] = startSession(userInfo[USER]);
            else
                userInfo[ID] = null;
        }
        
        return userInfo;
    }
    
}