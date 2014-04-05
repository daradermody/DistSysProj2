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
        content = msg;
        poster = pstr;

        DateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        date = dateFormatter.format(new Date());
    }

    /**
     * Set the content of the forum message
     *
     * @param msg New content for forum message
     */
    public void setContent(String msg) {
        content = msg;
    }

    /**
     * Returns the content of the forum message
     *
     * @return Content of the forum message
     */
    public String getContent() {
        return content;
    }

    /**
     * Set the content of the forum message
     *
     * @param pstr poster of message
     */
    public void setPoster(String pstr) {
        poster = pstr;
    }

    /**
     * Returns the content of the forum message
     *
     * @return Content of the forum message
     */
    public String getPoster() {
        return poster;
    }

    /**
     * Returns the content of the forum message
     *
     * @return Content of the forum message
     */
    public String getDate() {
        return date;
    }

    /**
     * Evaluates whether the forum content contains a given string.
     *
     * @param text String to search for in forum content
     * @return true if text is contained in forum content, false otherwise
     */
    public boolean contains(String text) {
        return content.contains(text);
    }

}
