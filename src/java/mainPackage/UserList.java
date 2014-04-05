/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mainPackage;

import java.util.ArrayList;

/**
 * Collection of users, and associated methods for data management
 * @author elfie
 */
public class UserList {
    
    private static ArrayList<User> users = new ArrayList<User>() {{
        add(new User("dara", "dara"));
        add(new User("emma", "emma"));
        add(new User("patrick", "emma"));
        add(new User("niko", "emma"));
    }};     
    /**
     * Find a user, given the username.
     * @param username
     * @return the user object matching the username, or null 
     */
    public static User findUser(String username){
        User user = null;
        loop:
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getUsername().equals(username)){
                user = users.get(i);
                break loop;
            }
        }
        return user;
    }
    
    /**
     * Determines whether the username exists.
     * @param username
     * @return the user object matching the username, or null 
     */
    public static boolean contains(String username){
        Boolean exists = false;
        if (users.isEmpty()) return false;
        loop:
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getUsername().equals(username)){
                exists=true;
                break loop;
            }
        }
        return exists;
    }
    
    /**
     * Get the entire user list
     * @return the users stored in the list 
     */
    public static ArrayList<User> getUserList(){
        return users;
    }
    
    /**
     * verify that the password matches the user
     * @param username
     * @param password
     * @return true if the username and password match, false otherwise
     */
    public static boolean verifyUser(String username, String password){
        User user = findUser(username);
        if (user != null){
            return (user.getPwdHash().equals(User.hash(user.getSalt()+password)));
        }
        else{
            return false;
        }
    }
    
    /**
     * Add a new user to the UserList
     * @param username
     * @param password 
     */
    public void addUser(String username, String password){
        if (!contains(username)){
            users.add(new User(username, password));
        }
    }
       
    /**
     * Print out each user in turn.
     * @return String representation of the UserList object
     */
    @Override
    public String toString(){
        String str = "";
        for (int i=0; i<users.size(); i++){
            str += users.get(i).toString()+"\n";
        }
        return str;
    }
}
