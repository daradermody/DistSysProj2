/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mainPackage;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author daradermody
 */
public class User {
    final protected String username;
    final protected String sessionID;
    protected int currentTimestamp;
    
    /**
     * Default constructor - creates a new forum thread with default title "no title"
     */
    public User(String usnm, String id, int time) {
        username = usnm;
        sessionID = id;
        currentTimestamp = time;
    }
}