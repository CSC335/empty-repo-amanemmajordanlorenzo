package models;

/**
 * The MemoryGame class provides methods for the administration of Memory Game, a game where a number
 * of cards are placed face down and the player flips two of the cards over
 * in an attempt to find two cards that are the same. If the selected
 * cards are the same, the two cards are removed. If the selected cards are not
 * the same, the cards are flipped back over. The game ends when the player 
 * finds all of the matching cards. This version of Memory Game supports
 * three distinct themes of cards and two difficulties: easy and hard.
 */
public class MemoryGame {
	private Deck deck;
	private String difficulty;
	private String theme;
	private int numGuesses;
	private boolean match = false;
	private boolean playAgain = true;
	
	/**
	 * The constructor for MemoryGame assigns values to the theme,
	 * difficulty, and the deck that will be used for the game. It
	 * sets the number of guesses to a default value of zero. 
	 * 
	 * @param themeSelection The user selected theme.
	 * @param mode The user selected difficulty.
	 * @param deck The deck containing the Cards of the appropriate theme and difficulty. 
	 */
	public MemoryGame(String themeSelection, String mode, Deck deck) {
		theme = themeSelection;
		difficulty = mode;
		numGuesses = 0;
		this.deck = deck;	
	}
	
	/**
	 * Increments the number of guesses made.
	 */
	public void incGuesses() {
		numGuesses++;
	}


	/**
	 * Determines whether the two cards selected by the user are a match.
	 * 
	 * @param card1 The first card selected by the user.
	 * @param card2 The second card selected by the user.
	 * @return Returns true if the two cards are a match. Returns false otherwise.
	 */
	public boolean compareCards(Card card1, Card card2) {
		if (card1.getTitle() == card2.getTitle()) {
			return true;
		} else {
			return false;
		}
	} 
	
	// Getters
	/**
	 * A getter method. 
	 * @return Returns the deck.
	 */
	public Deck getGameDeck(){
		return deck; 
	}
	
	/**
	 * A getter method.
	 * @return Returns the difficulty of the game as a String.
	 */
	public String getGameDifficulty() {
		return difficulty;
	}
	
	/**
	 * A getter method.
	 * @return Returns the theme of the game as a String.
	 */
	public String getGameTheme() {
		return theme;
	}
	
	/**
	 * A getter method.
	 * @return Returns the number of guesses the player needed to
	 * find all of the matching cards.
	 */
	public int getGameNumGuesses() {
		return numGuesses;
	}
	
	/**
	 * A getter method. Only used for testing.
	 * @return Returns the boolean variable "match", which is used
	 * to help determine if two cards are a match.
	 */
	public boolean getGameMatch() {
		return match;
	}
	
	/**
	 * A getter method. Only used for testing.
	 * @return Returns the boolean variable playAgain, which is used
	 * when determining if the player wants to play another round
	 * of Memory Game.
	 */
	public boolean getGamePlayAgain() {
		return playAgain;
	}
	
}
