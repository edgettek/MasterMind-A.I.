README

Contact Information:

Name: Kyle Edgette
Partners: Ricky Su
Class: CSC 172 SPRING 2015
Lab Session: Monday/Wednesday 2pm
TA Name: TJ Stein
Assignment Number: Project 01

Description:

A JAVA program that implements a "reverse" version of the game MasterMind. The main method in the TestClass
prompts the user to enter the colors and number of positions used in the game. Then the main method
calls the constructor of MasterMind, which calls the recursive generateSolutions method. The generateSolutions
method fills in one column of a two dimensional array, where each row represents one possible solution.
The algorithm for filling in the two dimensional array of solutions belongs to Ted Pawlicki. Then the constructor takes
every row in the 2D array and adds it to an arrayList of 1D arrays, where each 1D array is a possible solution. The constructor
then calls the firstMove() method, which prints out the program's first guess (which is the first array in the ArrayList).
The user then responds with the number of colors which are correct AND also in the correct position (based on their
answer) in the code and the colors that are correct but are not in the correct position. Using this information,
firstMove calls the response method. This method is the engine that deletes non-viable solutions from the complete arrayList,
prints out the program's next guess and prompts the user for his/her feedback. Then the method calls itself with the new number
of correct colors in the correct positions and the new number of correct colors in the incorrect places. This engine continues
until the number of correct colors in correct positions equals the number of positions in the code. At that point, the game ends
and a new game begins when the MasterMind constructor is called again.


Files Handed In:

1. TestClass.java contains the main method that begins the game
2. MasterMindInterface.java contains the interface used in the MasterMind class
3. MasterMind.java implements the MasterMindInterface and defines the implemented methods


How To Run:

Change directories until you are in the correct directory in the command line. Then, use the 
command line prompts shown below to compile all the .java files and run the TestClass

dhcp-10-5-26-213:src KEdgette1$ javac *.java
dhcp-10-5-26-213:src KEdgette1$ java TestClass

