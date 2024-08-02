package models;

/**
 * This Card class represents a card in the Memory Game. 
 * Each card has an associated title and picture. The player
 * of the game is able to create a match by choosing two
 * cards with the same picture.
 */
public class Card {
	
	private String title;
	private String path;
	
	
	/**
	 * The constructor for the Card class assigns a value
	 * to the variables "title" and "path" based on the
	 * selected deck. 
	 * 
	 * @param description The title of the card.
	 * @param filePath The file path for the picture that is
	 * on the card.
	 */
	public Card(String description, String filePath){
		title = description;
		path = filePath;
	}
	
	//Getters
	/**
	 * A getter method. Returns the the title of the card, which is used
	 * when comparing two cards.
	 * 
	 * @return The title of the card as a string.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * A getter method. Returns the file path of the photo used for
	 * the card. Currently only used for testing.
	 * 
	 * @return The file path of the card as a string.
	 */
	public String getPath() {
		return path;
	}
}
