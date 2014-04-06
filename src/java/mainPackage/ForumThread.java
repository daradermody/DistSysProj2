package mainPackage;
import java.util.*;

/**
 * This class is based on the module labs, but some modification have been made.
 * @author reiner.dojen
 * @author elfie
 */
public class ForumThread {
    ArrayList<Message> messages;
    String title;
    
    /**
     * Constructor to create a forum thread  with a given title.
     * 
     * @param forumTitle Title for forum thread.
     */
    public ForumThread(String forumTitle) {
        this.messages = new ArrayList<>();
       
        this.title = forumTitle;
    }

    
    /**
     * Adds a new message at the end of the thread
     * 
     * @param msg New message for thread.
     * @param pstr Username of person that submitted this message to the thread
     */
    public void addMessage(String msg, String pstr) {
        messages.add(new Message(msg, pstr));
    }
    
    /**
     * Returns the index-th message from the thread.
     * 
     * @param index Number of message to be returned.
     * @return The index-th message from the thread if index is less or equal 
     * than number of messages in thread, null otherwise.
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
