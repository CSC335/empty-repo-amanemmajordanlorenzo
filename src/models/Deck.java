package models;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The Deck class represents the deck of cards used during a round of Memory
 * Game. Each Deck object has an associated theme and difficulty, which are
 * selected by the user before the game begins.
 */
public class Deck {

	private String theme;
	private String difficulty;
	private ArrayList<Card> deck;

	/**
	 * The constructor for Deck class assigns the user selected theme and difficulty
	 * mode to the appopriate variables.
	 * 
	 * @param themeSelection The user selected theme.
	 * @param mode           The user selected difficulty.
	 */
	public Deck(String themeSelection, String mode) {
		theme = themeSelection;
		difficulty = mode;
		deck = new ArrayList<Card>();
	}

	/**
	 * createDeck() uses the slected theme and difficulty to build the approriate
	 * deck of cards for the game. The selectable themes are Cat, Car, and Galaxy.
	 * The selectable difficulties are easy and hard. An easy difficulty means that
	 * the deck will be created with 6 cards, each with a picture that corresponds
	 * to the theme. A deck created with the hard difficulty will be created with 12
	 * cards.
	 * 
	 * @return An Array List of Card objects.
	 */
	public ArrayList<Card> createDeck() {

		if (theme == "cat") {
			if (difficulty == "easy") {
				// Easy decks have 6 cards (3 pairs).
				deck.add(new Card("cat1", "blackcat.png"));
				deck.add(new Card("cat1", "blackcat.png"));

				deck.add(new Card("cat2", "orangecat.png"));
				deck.add(new Card("cat2", "orangecat.png"));

				deck.add(new Card("cat3", "siamesecat.png"));
				deck.add(new Card("cat3", "siamesecat.png"));
			}

			else if (difficulty == "hard") {
				// Hard decks have 12 cards (6 pairs).
				deck.add(new Card("cat1", "blackcat.png"));
				deck.add(new Card("cat1", "blackcat.png"));

				deck.add(new Card("cat2", "orangecat.png"));
				deck.add(new Card("cat2", "orangecat.png"));

				deck.add(new Card("cat3", "siamesecat.png"));
				deck.add(new Card("cat3", "siamesecat.png"));

				deck.add(new Card("cat4", "graycat.png"));
				deck.add(new Card("cat4", "graycat.png"));

				deck.add(new Card("cat5", "calicocat.png"));
				deck.add(new Card("cat5", "calicocat.png"));

				deck.add(new Card("cat6", "whitegraycat.png"));
				deck.add(new Card("cat6", "whitegraycat.png"));
			}
		}

		else if (theme == "car") {
			if (difficulty == "easy") {
				deck.add(new Card("car1", "charger.png"));
				deck.add(new Card("car1", "charger.png"));

				deck.add(new Card("car2", "mazda.png"));
				deck.add(new Card("car2", "mazda.png"));

				deck.add(new Card("car3", "orangesupra.png"));
				deck.add(new Card("car3", "orangesupra.png"));
			}

			else if (difficulty == "hard") {
				deck.add(new Card("car1", "charger.png"));
				deck.add(new Card("car1", "charger.png"));

				deck.add(new Card("car2", "mazda.png"));
				deck.add(new Card("car2", "mazda.png"));

				deck.add(new Card("car3", "orangesupra.png"));
				deck.add(new Card("car3", "orangesupra.png"));

				deck.add(new Card("car4", "sukicar.png"));
				deck.add(new Card("car4", "sukicar.png"));

				deck.add(new Card("car5", "fordescort.png"));
				deck.add(new Card("car5", "fordescort.png"));

				deck.add(new Card("car6", "stingray.png"));
				deck.add(new Card("car6", "stingray.png"));
			}
		}

		else if (theme == "space") {
			if (difficulty == "easy") {
				deck.add(new Card("space1", "saturn.png"));
				deck.add(new Card("space1", "saturn.png"));

				deck.add(new Card("space2", "earth_1.png"));
				deck.add(new Card("space2", "earth_1.png"));

				deck.add(new Card("space3", "sun.png"));
				deck.add(new Card("space3", "sun.png"));
			}

			else if (difficulty == "hard") {
				deck.add(new Card("space1", "saturn.png"));
				deck.add(new Card("space1", "saturn.png"));

				deck.add(new Card("space2", "earth_1.png"));
				deck.add(new Card("space2", "earth_1.png"));

				deck.add(new Card("space3", "sun.png"));
				deck.add(new Card("space3", "sun.png"));

				deck.add(new Card("space4", "moon.png"));
				deck.add(new Card("space4", "moon.png"));

				deck.add(new Card("space5", "jupiter.png"));
				deck.add(new Card("space5", "jupiter.png"));

				deck.add(new Card("space6", "venus.png"));
				deck.add(new Card("space6", "venus.png"));
			}
		}
		return deck;

	}// end createDeck

	// Method to shuffle the deck
	/**
	 * The shuffle() method randomizes the order of the cards in the deck. This
	 * method is used before each round of Memory Game to ensure that the location
	 * of matching cards isn't predictable.
	 */
	public void shuffle() {
		Collections.shuffle(deck);
	}

	/**
	 * A getter method.
	 * @return Returns the deck as an ArrayList of Card objects.
	 */
	public ArrayList<Card> getDeck() {
		return deck;
	}

}
