/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

import java.util.*;

/**
 *
 * @author reiner.dojen
 */
public class Database {

    static ArrayList<ForumThread> thread = new ArrayList<ForumThread>();


    /**
     * Returns a thread by index.
     *
     * @param index Index of the sought thread
     * @return index-th thread in the database if index <= number of threads,
     * null otherwise.
     */
    public static ForumThread getThread(int index) {        
        return thread.get(index);
    }

    /**
     * Adds a thread to the database.
     *
     * @param thread ForumThread to be added
     * @return true on success, false on failure.
     */
    public static boolean addThread(ForumThread thread) {
        return Database.thread.add(thread);
    }

    /**
     * Returns the number of threads in the database.
     *
     * @return Number of threads in the database
     */
    public static int getNumberOfThreads() {
        return thread.size();
    }
}
