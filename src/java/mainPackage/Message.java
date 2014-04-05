 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author reiner.dojen
 */
public class Message {

    private String content;
    private String poster;
    final private String date;

    /**
     * Default constructor to create a forum message with time
     *
     */
    public Message() {
        DateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        date = dateFormatter.format(new Date());
    }

    /**
     * Constructor to create a forum message with a given content
     *
     * @param msg content of forum message
     * @param pstr poster of message
     */
    public Message(String msg, String pstr) {
        this.content = msg;
        this.poster = pstr;

        DateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        this.date = dateFormatter.format(new Date());
    }
    
     /**
     * Constructor for resurrecting a message from a CSV file.
     * In this case, the date is already specified, and won't be auto-generated.
     * @param text the message content.
     * @param poster the message poster.
     * @param date the date the message was originally posted.
     */
    public Message(String text, String poster, String date){
        this.content = text;
        this.poster = poster;
        this.date = date;
    }
    
   
    /**
     * Returns the content of the forum message
     *
     * @return Content of the forum message
     */
    public String getContent() {
        return this.content;
    }


    /**
     * Returns the content of the forum message
     *
     * @return Content of the forum message
     */
    public String getPoster() {
        return this.poster;
    }

    /**
     * Returns the content of the forum message
     *
     * @return Content of the forum message
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Evaluates whether the forum content contains a given string.
     *
     * @param text String to search for in message content
     * @return true if text is contained in message content, false otherwise
     */
    public boolean contains(String text) {
        return this.content.contains(text);
    }
    
}
