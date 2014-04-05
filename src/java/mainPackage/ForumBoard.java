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
 * @author elfie
 */
public class ForumBoard {
    private ArrayList<ForumThread> threads;    
    private String filepath; // path to database
    static int numberOfThreads;
    /**
     * Default constructor to initialize an empty board.
     */
    public ForumBoard(){
        numberOfThreads = 0;
        threads =  new ArrayList(numberOfThreads);
        // TODO add a new database
    }
    
    /**
     * Load forum data from a file
     * @param filename path to the file containing thread data
     */
    public ForumBoard(String filename){
        this.filepath = filename; 
        //TODO Implement this.
        // NOTE: algorithm needs adjustment
        //       this will be done when the database layout is finalized.
        //For each entry in a file:
        //    read the thread title
        //    if the thread is "recent"
        //        add a thread of that title
        //        for each message in the thread
        //            add the message, poster and date
        // DONE
    }
    
    /**
     * Returns a thread by index.
     *
     * @param index Index of the sought thread
     * @return index-th thread in the database if index <= number of threads,
     * null otherwise.
     */
    public ForumThread getThread(int index) {        
        return this.threads.get(index);
    }
    
    /**
     * Create a new thread, with the given title, and add it to the "board".
     * @param title The name of the new thread.
     * @return the number of threads in the board
     */    
    public int addThread(String title){
        threads.add(new ForumThread(title));
        return threads.size();        
    }
    
    public static int getNumberOfThreads(){
        return ForumBoard.numberOfThreads;
    }
    
}
