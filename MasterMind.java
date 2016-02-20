/*
 * MasterMind
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

import java.util.ArrayList;
import java.util.Scanner;

public class MasterMind implements MasterMindInterface{
	
	//solutions is the arrayList that will contain all the viable solutions
	//non-viable solutions are deleted from the list after every response from the user
	ArrayList<String []> solutions = new ArrayList<String []>();

	//constructor
	public MasterMind(String [] tokenColors, int positions) {
		
		//size is the total number of possible solutions
		int size = (int) Math.pow(tokenColors.length, positions);
		
		//create the two dimensional array that is used to generate all the solutions
		String [][] solutionsArray = new String [size][positions];
		
		//call the recursive method that generates all the solutions in the 2D array
		generateSolutions(solutionsArray, tokenColors, 0);
		
		//take every row of the 2D solutions array and add it to the arrayList
		for(int y = 0; y < solutionsArray.length; y++) {
			solutions.add(solutionsArray[y]);
		}
		
		//call the first move method, which begins the response engine
		firstMove();
		
	}
	
	//recursive method to generate all the solutions in the 2D array
	public String [][] generateSolutions(String [][] answers, String [] tokenColors, int columnNum){
		
		//basis case: is the current column number is greater than the length of the first row, return the array
		if(columnNum >= answers[0].length) {
			return answers;
		}
		
		int exp = (answers[0].length) - columnNum -1;//calculate the exponent used in calculating the number of repeats in the row
		int reps = (int) Math.pow((double) tokenColors.length, (double) exp);//calculate the number of repeats in the row
		int count = 0;//used to iterate through the rows of the 2D array
		
		//while-loop loops through the entire column that is being filled
		while(count < answers.length) {
			//first for loop iterates through each "repeating sequence" in the column
			for(int i = 0; i < tokenColors.length; i++) {
				for(int j = 0; j < reps; j++) {//fills repeating colors in array
					answers[count++][columnNum] = tokenColors[i];
				}
			}
		}
		//recursive call to fill the next column in the 2D array
		return generateSolutions(answers, tokenColors, columnNum+1);
	}
	
	
	//method to calculate matches in the computer's guess and all the possible solutions
	public static boolean [] colorCorrectPositionCorrect(String [] guess, String [] candidate) {
		
		//boolean array will contain the boolean variable for each position in the code
		//i.e. if two colors match, the boolean will be true for that position
		boolean [] bArray = new boolean [candidate.length];
		
		//iterates through length of solution
		for(int i = 0; i < candidate.length; i++) {
			if(guess[i].equals(candidate[i])) {//if the two colors match, set the boolean to true
				bArray[i] = true;
			}
		}
		
		return bArray;
	}
	
	//method to calculate matches between the computer's guess and each possible solution
	//note: the method requires the boolean array from the colorCorrectPositionCorrect method above
	public static boolean [] colorCorrectPositionIncorrect(String [] guess, String [] candidate, boolean [] ignore) {
		
		//boolean array will contain the boolean variable for each position in the code
		boolean [] bArray = new boolean [guess.length];
		
		//iterate through all positions in the guess
		for(int i = 0; i < guess.length; i++) {
			if(ignore[i] == false) {//if that position has not already been accounted for in colorCorrectPositionCorrect
				for(int j = 0; j < candidate.length; j++) {//iterate through candidate
					if(ignore[j] == false) {//if candidate's position has not already been accounted for in colorCorrectPositionCorrect
						if(guess[i].equals(candidate[j])) {//if the color in the 1st position of the guess match with ANY positions in the candidate, set to TRUE
							bArray[j] = true;
						}
					}
				}
			}
		}
		
		return bArray;
	}
	
	//method to count the number of True booleans in a boolean array
	public static int countBoolean(boolean [] temp) {
		int count = 0;
		
		for(int i = 0; i < temp.length; i++) {
			if(temp[i] == true) {
				count++;
			}
		}
		
		return count;
		
	}
	
	//response method is the primary method behind the engine that analyzes the user's response and prompts the user
	public void response(int colorsRightPositionsRight, int ColorsRightPositionsWrong) {
		
		//basis case: is the number of correct colors in correct positions is the same as the length of the code, end the game and start a new one
		if(colorsRightPositionsRight == solutions.get(0).length){
			System.out.println("\n\n*********************************************");
			System.out.println("The computer guessed correctly!! Game over!");
			System.out.println("*********************************************");
			newGame();//method calls the MasterMind constructor again, resetting the game
		}
		else{
			//new array list that holds all the solutions that are no longer viable 
			//based on the user's response of #colorCorrectPositionCorrect and #colorCorrectPosition incorrect
			ArrayList<String []> toBeRemoved = new ArrayList<String []>();
		
			//next move returns the 1st possible solution in the arrayList, which is always the computer's guess
			String [] currentGuess = nextMove();
			
			//iterates through arrayList
			for(String [] candidate : solutions) {
				
				//compares the computer's guess and the codes in the arrayList for correctColorCorrectPosition
				boolean [] CCCP = colorCorrectPositionCorrect(currentGuess, candidate);	
				
				//if the computer's guess and the candidate solution from the arrayList do not have the same #colorCorrectPositionCorrect
				//or #colorCorrectPositionIncorrect as the user's response for the computer's guess
				//that candidate is added to the toBeRemoved ArrayList
				if(countBoolean(CCCP) != colorsRightPositionsRight || countBoolean(colorCorrectPositionIncorrect(currentGuess, candidate, CCCP)) != ColorsRightPositionsWrong) {
						toBeRemoved.add(candidate);
				}
			}
				
			//removes all the solutions in toBeRemoved from the solutions array
			solutions.removeAll(toBeRemoved);
			
			//next guess is the first solution in the modified arrayList
			String [] nextGuess = nextMove();
			
			//print out the computer's guess
			System.out.println("\nMy guess is:");
			for(int z = 0; z<nextGuess.length;z++ ) {
				System.out.print(nextGuess[z] + ", ");
			}
			
			//scans the user's response for #colorCorrectPositionCorrect and #colorCorrectPositionIncorrect
			Scanner scan = new Scanner(System.in);
		
			System.out.println("\n\nNow, please tell me how many tokens are the correct color and in the correct position,"
					+ " and how many are the correct color but in the wrong position. \n(Please enter two integers separated by a comma.)");
			String line = scan.nextLine();
			line = line.replaceAll(" ", "");
			String [] response = line.split(",");
		
			int ColorCorrPosCorr = Integer.parseInt(response[0]);
			int ColorCorrPosIncorr = Integer.parseInt(response[1]);
			
			//calls the response method again with the updated #colorCorrectPositionCorrect and #colorCorrectPositionIncorrect
			response(ColorCorrPosCorr, ColorCorrPosIncorr);
			
			}
	}

	//method for the first move
	public void firstMove() {

		//print computer's first guess
		System.out.println("\nMy first guess is: ");
		String [] firstGuess = nextMove();//returns first solution in the ArrayList
		for(int z = 0; z<firstGuess.length;z++ ) {
			System.out.print(firstGuess[z] + ", ");
		}
		
		//scan in user's response of #colorCorrectPositionCorrect and #colorCorrectPositionIncorrect
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\nNow, please tell me how many tokens are the correct color and in the correct position,"
				+ " and how many are the correct color but in the wrong position. \n(Please enter two integers separated by a comma.)");
		String line = scan.nextLine();
		line = line.replaceAll(" ", "");
		String [] response = line.split(",");
		
		int ColorCorrPosCorr = Integer.parseInt(response[0]);
		int ColorCorrPosIncorr = Integer.parseInt(response[1]);
		
		//call the response method with the #colorCorrectPositionCorrect and #colorCorrectPositionIncorrect, which starts the engine for the computer/user interaction
		response(ColorCorrPosCorr, ColorCorrPosIncorr);
	}
	
	//method to return the first solution in the arrayList
	public String[] nextMove() {
		
		return solutions.get(0);
	}

	//method that prompts the user for another set of colors and a new code length and then calls the MasterMind constructor
	public void newGame() {
		
		Scanner scan = new Scanner(System.in);
		
		
		for(int i = 0; i < 3; i++) {
			System.out.println();
		}
		
		//prompt user for new list of colors and a new code length
		System.out.println("Let's play another game of MasterMind!\nWhat colors of tokens would you like to play with? "
				+ "(Please enter the colors as a list separated by commas and please only enter a total of ten colors.)");
		String line = scan.nextLine();
		
		line = line.replaceAll(" ", "");
		String [] colors = line.split(",");
		
		System.out.println("How many positions would you like to play with?");
		int pos = scan.nextInt();
		
		//call the MasterMind constructor to begin a new game
		MasterMind game = new MasterMind(colors, pos);
	}

}
