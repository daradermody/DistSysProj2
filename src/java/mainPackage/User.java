/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mainPackage;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * A User data type, containing the details of one user from the database
 * @author elfie
 */
public class User {
    private final String username;
    private String sessionID; //for security class
    private final String creationDate; // for Security class
    private long sessionTime;
    private final int salt;
    private final String pwdHash;
    /**
     * Default constructor - creates a new forum thread with default title "no title"
     * @param usnm the name for the new user 
     * @param password the user's password
     */
    public User(String usnm, String password) {
        this.username = usnm;
        
        DateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        this.creationDate = dateFormatter.format(new Date());
  
        Random r = new Random();
        this.salt = r.nextInt();
        this.pwdHash = hash(salt+password);
        // TODO: created users should be added to a list of some sort
        sessionID = null; // for default
        sessionTime = 0; // for default
    }
    
    /**
     * Constructor for "resurrecting" a User from a file.
     * @param username user's name
     * @param hashPW hashed password
     * @param salt salt 
     * @param date creation date
     */
    public User(String username, String hashPW, int salt, String date){
        this.username=username;
        this.pwdHash=hashPW;
        this.salt=salt;
        this.creationDate = date;
    }
    public String getUsername(){
        return this.username;
    }
    
    public String getCreationDate(){
        return this.creationDate;
    }
    
    public long getTimestamp() {
        return this.sessionTime;
    }
    
    public void setTimestamp(long st) {
        this.sessionTime=st;
    }
    
    public int getSalt(){
        return this.salt;
    }
    
    public String getSessionID(){
        return this.sessionID;
    }
    
    public void setSessionID(String id){
        this.sessionID=id;
    }
    
    public String getPwdHash(){
        return this.pwdHash;
    }
    
    public static String hash(String plain){    
        String cipher = null;
         
        if(plain == null) return null;
         
        try {
       
            MessageDigest digest = MessageDigest.getInstance("SHA");
        //Update input string in message digest
            digest.update(plain.getBytes(), 0, plain.length());
 
        //Converts message digest value in base 16 (hex)
            cipher = new BigInteger(1, digest.digest()).toString(16);
 
        } catch (NoSuchAlgorithmException e) {
 
            e.printStackTrace();
        }
        return cipher;
    }
    
    @Override
    public String toString(){
        return(String.format("%s, %s, %s, %s", this.username, this.pwdHash, this.salt, this.creationDate));
    }
    
}
