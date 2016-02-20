/*
 * MasterMindInterface
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

//interface for required methods in the MasterMind class
public interface MasterMindInterface {
	
	public void response(int colorsRightPositionsWrong, int poisitionsAndColorsRight);
	
	public String [] nextMove();
	
	public void newGame();

}
