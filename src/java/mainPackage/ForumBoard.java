/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mainPackage;

import java.util.ArrayList;

/**
 * A container for the threads in a forum.
 * Deals with creating and managing Forum Threads.
 * @author Emma Foley
 * @author Dara Dermody
 * @author Niko Flores
 * @author Patrick O Keeffe
 */
public class ForumBoard {
    private static ArrayList<ForumThread> threads = new ArrayList<ForumThread>();
    private static int numberOfThreads;
    
    /**
     * Returns a thread by index.
     *
     * @param index Index of the sought thread
     * @return index-th thread in the database if index <= number of threads,
     * null otherwise.
     */
    public static ForumThread getThread(int index) {        
        return threads.get(index);
    }
    
    /**
     * Create a new thread, with the given title, and add it to the "board".
     * @param title The name of the new thread.
     * @return the number of threads in the board
     */    
    public static int addThread(String title){
        threads.add(new ForumThread(title));
        return threads.size();        
    }
    
    public static int addThread(ForumThread t) {
        threads.add(t);
        return threads.size();
    }
    
    /**
     * Get the number of threads 
     * @return number of thread in a forum
     */
    public static int getNumberOfThreads(){
        return threads.size();
    }
    
}
