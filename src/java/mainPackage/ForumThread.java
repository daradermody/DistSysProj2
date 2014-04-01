package mainPackage;

/* ___ 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;

/**
 *
 * @author reiner.dojen
 */
public class ForumThread {
    ArrayList<Message> messages;
    ArrayList<String> keywords;
    String title;
    
    /**
     * Default constructor - creates a new forum thread with default title "no title"
     */
    public ForumThread() {
        messages = new ArrayList<Message>();
        keywords = new ArrayList<String>();
        title = "no title";
    }
    
    /**
     * Constructor to create a forum thread  with a given title.
     * 
     * @param forumTitle Title for forum thread.
     */
    public ForumThread(String forumTitle) {
        messages = new ArrayList<Message>();
        keywords = new ArrayList<String>();
        title = forumTitle;
    }

    /**
     * Constructor to create a forum thread  with a given title and
     * list of keywords
     * 
     * @param forumTitle Title for forum thread.
     * @param kwords Array of keywords.
     */
    public ForumThread(String forumTitle, String[] kwords) {
        messages = new ArrayList<Message>();
        keywords = new ArrayList<String>();
        title = forumTitle;
        keywords.addAll(Arrays.asList(kwords));
    }
    
    /**
     * Establishes whether thread has a given keyword.
     * 
     * @param kword Keyword to search for.
     * @return true if kword is a keyword of thread, false otherwise.
     */
    public boolean hasKeyword(String kword) {
        boolean hasKWord=false;
        for (int i=0; i<keywords.size(); i++) {
            if(kword.equalsIgnoreCase(keywords.get(i))){
                hasKWord = true;
                break;
            }
        }
        return hasKWord;
    }
    
    /**
     * Adds a new message at the end of the thread
     * 
     * @param msg New message for thread.
     */
    public void addMessage(String msg, String pstr) {
        messages.add(new Message(msg, pstr));
    }
    
    /**
     * Returns the index-th message from the thread.
     * 
     * @param index Number of message to be returned.
     * @return The index-th message from the thread if index is <=
     * number of messages in thread, null otherwise.
     */
    public String getIndividualMessage(int index) {
        String messageText = "";
        Message message = messages.get(index);
        if (message != null) messageText = message.getContent();
        return messageText;
    }
    
    /**
     * Returns all messages of the thread as a single string.
     * 
     * @return String containing all messages of the thread.
     */
    public ArrayList<Message> getAllMessages() {
        return messages;
    }
    
    /**
     * Returns the title of the thread.
     * 
     * @return Title of thread.
     */
    public String getTitle() {
        return title;
    }
}
