/*
 * TestClass
 * 
 * Version 2.0
 * 
 * Copyright Kyle Edgette
 * 
 * Course : CSC 172 SPRING 2015
 * 
 * Assignment : Project 01
 * 
 * Author : Kyle Edgette
 * 
 * Lab Session : Monday/Wednesday 2pm-3:15pm
 * 
 * Lab TA : TJ Stein
 * 
 * Last Revised : February 20, 2015
 * 
 */

import java.util.Scanner;

public class TestClass {
	
	public static void main(String [] args) {
		
		//Prompt user for the set of colors they wish to play with and the length of code they would like to user
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Hello! Welcome to MasterMind!\nWhat colors of tokens would you like to play with? "
				+ "(Please enter the colors as a list separated by commas and please only enter a total of 8 colors.)");
		String line = scan.nextLine();
		
		line = line.replaceAll(" ", "");
		String [] colors = line.split(",");
		
		System.out.println("How many positions would you like to play with? "
				+ "(Please only enter an integer less than 8.)");
		int pos = scan.nextInt();
		
		//call the MasterMind constructor to begin the game
		MasterMind game = new MasterMind(colors, pos);
		
	}

}
