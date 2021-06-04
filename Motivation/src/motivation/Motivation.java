/**
 * Programmer: Rayaan Khan
 * Date: 2021-06-04
 * Project: Motivation.java
 * Program Name: motivation
 */

package motivation;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.*;
import java.io.*;

public class Motivation {

    static public Scanner scanS = new Scanner(System.in); // String Scanner
    static public Scanner scanN = new Scanner(System.in); // String Scanner
    
    public static void main(String[] args) throws IOException, InterruptedException {
        
        ArrayList<String> quotes = new ArrayList<>(); // Stores quotes from .txt
        ArrayList<String> author = new ArrayList<>(); // Stores quotes from .txt
        
        String choice; // for what the user wants to do

        readTextFile(quotes, author); 
        
        System.out.println("---------------------------");
        System.out.println("Welcome To Quotehub"
                + "\n---------------------------");
        
        // Formatting of the month, day and year. And current time
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/uuuu " +
                "\nHH:mm:ss");
        
        System.out.println("Time zone: " + ZoneOffset.systemDefault());
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("Date:" + dtf.format(now));
        System.out.println("---------------------------");
        
        System.out.print("\nWhat would you like to do today?"
                + "\na) Add a quote"
                + "\nb) Read a quote: ");
        choice = scanS.nextLine().toLowerCase();
       
        switch (choice) {
            case "a":
                addQuote(quotes, author);
                break;
            case "b":
                pullUpQuote(quotes, author);
                break;
            default:
                break;
        }
    }
    
    /**
     * Method Name: readTextFile
     * Description: This method reads the textfile and stores the quotes as well
     * as the authors into the appropriate ArrayLists
     * @param quotes - arraylist
     * @param author - arraylist
     * @throws IOException - file stuff
     */
    public static void readTextFile(ArrayList<String> quotes, 
            ArrayList<String> author) throws IOException {
        
        // state text file to read
        File file = new File("motivationQ.txt");
        
        // open file to read from
        File myFile = new File(file.getAbsolutePath());
        Scanner readFile = new Scanner(myFile);  
        
        while (readFile.hasNext()) { // while file has a new line do:
            String moveLine = readFile.nextLine(); // move to next line
            String[] part = moveLine.split("/"); // split line into 2 parts
            
            quotes.add(part[0]); // stores name
            author.add(part[1]); // stores name
        }
        readFile.close(); // closes file from reading
    }
    
    /**
     * Method Name: pullUpQuote
     * Description: Asks some info and pulls up a quote
     * @param quotes - arraylist
     * @param author  - arraylist
     */
    public static void pullUpQuote(ArrayList<String> quotes, 
            ArrayList<String> author) {
        
        String TOD; // Time of Day
        String name; // Store name 
        boolean flag; // error trapping
           
        System.out.print("\nWhat time of day is it in your Location?" +
                "\n 1. Morning" +
                "\n 2. AfterNoon" +
                "\n 3. Evening: ");
        
        do {
            flag = false; // reset at the beginning
            TOD = scanS.nextLine().toLowerCase();
            switch (TOD) {

                case "morning":
                case "1":
                    TOD = "Morning";
                    break;
                case "afternoon":
                case "2":
                    TOD = "Afternoon";
                    break;
                case "evening":
                case "3":
                    TOD = "Evening";
                    break;
                default:
                    flag = true;
                    break;
            }
            
        } while (flag == true);

        System.out.print("\nAnd... What is your name?: ");
        name = scanS.nextLine();

        System.out.println("\nThe time of day is "+ TOD 
                + " and your name is " + name + "!");
        
        // selecting a random quote
        int index = (int)(Math.random() * quotes.size());
        // Prints out Quote and author

        System.out.println("\nQUOTE OF THE DAY");
        System.out.println(quotes.get(index));
        System.out.println("\t" + author.get(index));
        System.out.println("----------------------------------------");

    }
    
    /**
     * Method Name: addQuote
     * Description: adds a quote
     * @param quotes
     * @param author
     * @throws IOException
     * @throws InterruptedException 
     */
    public static void addQuote(ArrayList<String> quotes, 
            ArrayList<String> author) throws IOException, InterruptedException  
    {
        
        String quoteText; // store quote name
        String authorName; // store author name
        
        System.out.print("\nPlease enter the quote: ");
        quoteText = scanS.nextLine();
        
        System.out.print("Please enter the author: ");
        authorName = scanS.nextLine();
        
        quotes.add(quoteText); // adds the quote to the arraylist
        author.add(authorName); // adds the author to the arraylist
        
        // opening up text file to update with new arraylists
        File fileOutput = new File("motivationQ.txt");
        
        // finds the file directy that String DestinationFile is at
        FileWriter fWriter = new FileWriter(fileOutput.getAbsolutePath(), true);
        PrintWriter outputFile = new PrintWriter(fWriter);
        
        outputFile.println("\"" + quoteText + "\"/" + authorName); // update
        outputFile.close(); // closing files from outputting (saves)
    }
}
