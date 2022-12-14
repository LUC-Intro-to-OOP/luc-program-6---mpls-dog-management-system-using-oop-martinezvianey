
/*-------------------------------------------------------------
# Program 6: MPLS Dog Management System using OOP Principles
**Programs 6 is similar in functionality to program 5**

The MPLS Dog Boarding company would like for you to create an application that allows for a care attendant to be able 
to create, retrieve and update dog records from the system. MPLS Dog Boarding can only have 12 dogs in their care at a time. When you select the option to **create** a dog record.  You will 
enter the dog's id, dog's name, weight and dog's age.  Once information is entered for a dog, the program will display
entered information and reprompt the care attendent to select an option to exit, display, create or update dog record.  

When the **update** option is selected the care attendant will be presented with option to enter the dog's id number and reassign information
pertaining to dog.  

When the **retrieve** option is selected, the user will be able to enter the dog's id and be presented
with dog information.

## What makes this program different from program 5
1. You will create and utilize a class structure to create dog objects.  
2. You will add functionality to read from a file (doginfo.csv) and create new dog records.  The dog records should be held within an ArrayList.   

**Hints**
- Consider reading all the dog data from the file and creating your object instances once your program starts  
- Use ArrayList collection to store your dog objects.
- Use the toString method to format and display dog information. 

#### Listed below is a detailed explanation of the requirements needed to complete the dog management system.  

## Requirement 1 (5 Points) 
Variables are properly declared and initialized; Use of Scanner Object to read input from console. Make use of constant final variables. 
Whenever possible, make sure to declare all variables that will hold data along with declaring final variables that will not change during runtime.
Proper structure used for allowing the end-user to continously select menu option until a sentinel value is entered.  Proper syntax for instantiating new objects.

## Requirement 2 (5 Points) 
The class structure should included these 5 things:
    1. Your class should have a minimum of 5 attributes.  Data encapsulation should be enforced with the use of Getter and Setter methods
    2. A constructor that allows you to set all of the class properties
    3. A default constructor that sets properties to default values.
    4. Methods that will allow you to display, create and update dog record.
    5. A toString method that outputs the dogs info.

## Requirement 3 (5 Points) 

Input from the csv file - Program reads all data into program and creates object instances.  All objects are added to an ArrayList.

## Requirement 4 (5 Points) 
Style - Proper use of comments, spacing, in program; use of descriptive variable names

## Requirement 5 (5 Points) 
Program is submitted by the due date listed and pushed to assigned GitHub Repository; Repository contains a minimum of three commits.

## Submission
Your project folder will need to be submitted to the assigned GitHub repository provided to you by the instructor. In Sakai, you will need to submit the link to your repository by the due date and time listed in the write-up. Make sure you receive confirmation from Sakai that your assignment has been submitted.
If you prefer, you can also submit the .java files to Sakai.

    [REPLACE MY INFORMATION WITH YOURS]
    Course: COMP 170, Fall 1 2022
    System: Visual Studio Code, MacOS 12.6
    Author: V. Martinez
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileInputStream;
import java.io.IOException;



 public class DogManagement {
    static Scanner input = new Scanner(System.in);
       
    //collection for array list 
    static ArrayList<Dog> dogList = new ArrayList<Dog>();

    
    public static void main(String[] args) throws IOException {
        FileInputStream fileByteStream = new FileInputStream("doginfo.csv");
        Scanner fileScnr = new Scanner(fileByteStream);
        
        int END = 4;
        int MAXDOGS = 12;
        int menuOption; 
        int dogCount; 

        
        

        //just a check for myself to make sure file is being read correctly>>
        readFile(fileByteStream, fileScnr);
        dogCount = dogList.size();

        //System.out.println("Please choose from the following dog names: \n");
        //for (Dog dog: dogList) {
        //    String name = dog.getName();

        //    System.out.println("Name: " + name);
        //}
        //works correctly^^
        menuOption = displayPrompt();
        //check>> System.out.println("You selected: " + menuOption);
        while (menuOption != END) {
            switch (menuOption) {
                case 1:
                while (dogCount <= MAXDOGS) {
                    createRecord(dogCount, MAXDOGS);
                    dogCount = dogCount+1;
                    break;
                }
                break;
                    
                case 2:
                displayRecord();
                break;
                    
                case 3:
                updateRecord(MAXDOGS);
                break;
                    
                default: 
                System.out.print("Invalid option. Please enter another menu option: ");
                //menuOption = displayPrompt();
                break;
            }
            menuOption = displayPrompt();
        
        }
    }

    public static void readFile(FileInputStream file, Scanner fileScnr) {
        //FileInputStream fileByteStream = new FileInputStream("doginfo.csv");
        //Scanner fileScnr = new Scanner(fileByteStream); 
       
        StringTokenizer token = null;
        
        String firstLine = fileScnr.nextLine();
        //System.out.println(firstLine);
        
        while (fileScnr.hasNextLine()) {
            
            token = new StringTokenizer(fileScnr.nextLine(), ",");
            
            
            int ID = Integer.parseInt(token.nextToken());
            String name = token.nextToken();
            int weight = Integer.parseInt(token.nextToken());
            int age = Integer.parseInt(token.nextToken());

            //System.out.println(ID + name + weight + age );
            
            Dog dog = new Dog(name, ID, weight, age); 
            dog.setAge(age);
            dog.setID(ID);
            dog.setWeight(weight);
            dog.setName(name);

            dogList.add(dog);
            //System.out.println(dog.outputInfo());

        }
        
        fileScnr.close();
    }
    public static int displayPrompt() {
        int menuOption;

        System.out.println("\nSelect a menu option:");
        System.out.println("\t1) Create a dog record");
        System.out.println("\t2) Display dog record");
        System.out.println("\t3) Update dog record");
        System.out.println("\t4) Exit Program");
        
        System.out.print("Enter selection here --> ");
        //INPUT
        menuOption = Integer.parseInt(input.next());

        return menuOption;
    }

    public static void createRecord(int dogCount, int MAXDOGS) {
        int newDogID = 0;
        int newDogAge = 0;
        String newDogName = null;
        int newDogWeight = 0;
        

        if (dogCount < MAXDOGS) {
            System.out.print("Enter dog's name: ");
            newDogName = input.next();
            System.out.print("Enter dog's ID number: ");
            newDogID = input.nextInt();
            System.out.print("Enter dog's age: ");
            newDogAge = input.nextInt();
            System.out.print("Enter dog's weight: ");
            newDogWeight = input.nextInt();

            Dog dog = new Dog(newDogName, newDogID, newDogWeight, newDogAge);
            dogList.add(dog);

            System.out.println("\nThe following information has been entered: ");
            System.out.print(dog.outputInfo());
            
        //
        }
    }
    public static void displayRecord() {
        boolean dogFound;
        System.out.println("\nPlease select an ID number from the following list:");
        for (Dog dog: dogList) {
            String name = dog.getName();
            int ID = dog.getID();
            System.out.println("ID: " + ID + " for " + name);
        }
        System.out.print("ID# ");
        int displayID = Integer.parseInt(input.next());
        while (dogFound = false); {
            for (Dog dog: dogList) {        
                if (dog.getID() == displayID) {
                    System.out.println("You selected dog ID# " + displayID + " \n" + dog.outputInfo());
                    dogFound = true;
                    break;
                        //System.out.println(dogString);
                }
            }
            if (dogFound != true) {
                System.out.println("Dog ID not in our system.");
            }
        }
            
    }

    public static void updateRecord(int MAXDOGS) {
        boolean dogFound = false;

        System.out.println("\nPlease select an ID number from the following list:");
        for (Dog dog: dogList) {
            String name = dog.getName();
            int ID = dog.getID();
            System.out.println("ID: " + ID + " for " + name);
        }
        System.out.print("ID# ");
        int updateID = Integer.parseInt(input.next());

        for (Dog dog: dogList) {
            if (dog.getID() == updateID) {
                int i = dogList.indexOf(dog);
                dogFound = true;
                dogList.remove(i);
                createRecord( i,  MAXDOGS);
            } 
            
        }
        if (dogFound != true) {
            System.out.println("Dog ID not in our system.");
        }
    }       
}
 