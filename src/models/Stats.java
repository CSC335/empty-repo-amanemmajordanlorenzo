package models;

import java.io.Serializable;

/**
 * The Stats class maintains a record of numerous statiscal data
 * that is pertinent to the Memory Game, such as number of games played,
 * high scores, etc...
 */
public class Stats implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private double easyCatAvg, hardCatAvg, easyCarAvg, hardCarAvg, easySpaceAvg, hardSpaceyAvg = 0;
	private int easyCatNumGames, hardCatNumGames, easyCarNumGames, hardCarNumGames, easySpaceNumGames,
			hardSpaceNumGames = 0;
	private int easyCatRawScore, hardCatRawScore, easyCarRawScore, hardCarRawScore, easySpaceRawScore,
			hardSpaceRawScore = 0;
	private int easyCatHighScore, hardCatHighScore, easyCarHighScore, hardCarHighScore, easySpaceHighScore,
			hardSpaceHighScore = 0;
	
	/**
	 * The addScore() method updates the Stats object with the score of the most
	 * recently played game. It increments the number of games played, calculates 
	 * the average score for the given difficulty, and updates the high score, if
	 * necessary. 
	 * 
	 * @param theme Used to determine which theme was played.
	 * @param difficulty Used to determine which difficulty was played.
	 * @param numGuesses Used for the calculation of averages and high scores.
	 */
	public void addScore(String theme, String difficulty, int numGuesses) {
		if (theme.equals("cat")) {
			if (difficulty == "easy") {
				easyCatNumGames++;
				easyCatRawScore += numGuesses;
				easyCatAvg = easyCatRawScore / easyCatNumGames;
				if (easyCatHighScore == 0) {
					easyCatHighScore = numGuesses;
				} else if (numGuesses < easyCatHighScore) {
					easyCatHighScore = numGuesses;
				}

			}

			else { // difficulty == "hard"
				hardCatNumGames++;
				hardCatRawScore += numGuesses;
				hardCatAvg = hardCatRawScore / hardCatNumGames;
				if (hardCatHighScore == 0) {
					hardCatHighScore = numGuesses;
				} else if (numGuesses < hardCatHighScore) {
					hardCatHighScore = numGuesses;
				}
			}
		} // end of cat if statements

		else if (theme.equals("car")) {
			if (difficulty == "easy") {
				easyCarNumGames++;
				easyCarRawScore += numGuesses;
				easyCarAvg = easyCarRawScore / easyCarNumGames;
				if (easyCarHighScore == 0) {
					easyCarHighScore = numGuesses;
				} else if (numGuesses < easyCarHighScore) {
					easyCarHighScore = numGuesses;
				}

			}

			else { // difficulty == "hard"
				hardCarNumGames++;
				hardCarRawScore += numGuesses;
				hardCarAvg = hardCarRawScore / hardCarNumGames;
				if (hardCarHighScore == 0) {
					hardCarHighScore = numGuesses;
				} else if (numGuesses < hardCarHighScore) {
					hardCarHighScore = numGuesses;
				}
			}
		} // end of car if statememts

		else { // theme == "Space"
			if (difficulty.equals("easy")) {
				easySpaceNumGames++;
				easySpaceRawScore += numGuesses;
				easySpaceAvg = easySpaceRawScore / easySpaceNumGames;
				if (easySpaceHighScore == 0) {
					easySpaceHighScore = numGuesses;
				} else if (numGuesses < easySpaceHighScore) {
					easySpaceHighScore = numGuesses;
				}

			}

			else { // difficulty == "hard"
				hardSpaceNumGames++;
				hardSpaceRawScore += numGuesses;
				hardSpaceyAvg = hardSpaceRawScore / hardSpaceNumGames;
				if (hardSpaceHighScore == 0) {
					hardSpaceHighScore = numGuesses;
				} else if (numGuesses < hardSpaceHighScore) {
					hardSpaceHighScore = numGuesses;
				}
			}
		} // end of Space if statements
	}// end of addScore()

	
	// Getters
	/**
	 * A getter method. Returns the high score for the difficulty and
	 * theme that are in the title of the method.
	 * @return easyCatHighScore, the current high score for the difficulty
	 * and theme.
	 */
	public int getEasyCatHighScore() {
		return easyCatHighScore;
	}
	
	/**
	 * A getter method. Returns the average score for the difficulty and
	 * theme that are in the title of the method.
	 * @return easyCatAverage, the current average score for the difficulty
	 * and theme.
	 */
	public double getEasyCatAvg() {
		return easyCatAvg;
	}
	
	/**
	 * A getter method. Returns the high score for the difficulty and
	 * theme that are in the title of the method.
	 * @return hardCatHighScore, the current high score for the difficulty
	 * and theme.
	 */
	public int getHardCatHighScore() {
		return hardCatHighScore;
	}
	
	/**
	 * A getter method. Returns the average score for the difficulty and
	 * theme that are in the title of the method.
	 * @return hardCatAverage, the current average score for the difficulty
	 * and theme.
	 */
	public double getHardCatAvg() {
		return hardCatAvg;
	}
	
	/**
	 * A getter method. Returns the high score for the difficulty and
	 * theme that are in the title of the method.
	 * @return easyCarHighScore, the current high score for the difficulty
	 * and theme.
	 */
	public int getEasyCarHighScore() {
		return easyCarHighScore;
	}
	
	/**
	 * A getter method. Returns the average score for the difficulty and
	 * theme that are in the title of the method.
	 * @return easyCarAverage, the current average score for the difficulty
	 * and theme.
	 */
	public double getEasyCarAvg() {
		return easyCarAvg;
	}
	
	/**
	 * A getter method. Returns the high score for the difficulty and
	 * theme that are in the title of the method.
	 * @return hardCarHighScore, the current high score for the difficulty
	 * and theme.
	 */
	public int getHardCarHighScore() {
		return hardCarHighScore;
	}
	
	/**
	 * A getter method. Returns the average score for the difficulty and
	 * theme that are in the title of the method.
	 * @return hardCarAverage, the current average score for the difficulty
	 * and theme.
	 */
	public double getHardCarAvg() {
		return hardCarAvg;
	}
	
	/**
	 * A getter method. Returns the high score for the difficulty and
	 * theme that are in the title of the method.
	 * @return easySpaceHighScore, the current high score for the difficulty
	 * and theme.
	 */
	public int getEasySpaceHighScore() {
		return easySpaceHighScore;
	}
	
	/**
	 * A getter method. Returns the average score for the difficulty and
	 * theme that are in the title of the method.
	 * @return easySpaceAverage, the current average score for the difficulty
	 * and theme.
	 */
	public double getEasySpaceAvg() {
		return easySpaceAvg;
	}
	
	/**
	 * A getter method. Returns the high score for the difficulty and
	 * theme that are in the title of the method.
	 * @return hardSpaceHighScore, the current high score for the difficulty
	 * and theme.
	 */
	public int getHardSpaceHighScore() {
		return hardSpaceHighScore;
	}
	
	/**
	 * A getter method. Returns the average score for the difficulty and
	 * theme that are in the title of the method.
	 * @return hardSpaceAverage, the current average score for the difficulty
	 * and theme.
	 */
	public double getHardSpaceAvg() {
		return hardSpaceyAvg;
	}

}// end of file
