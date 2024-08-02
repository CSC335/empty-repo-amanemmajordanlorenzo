package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import org.junit.Test;
import models.Card;
import models.Deck;

public class DeckTest {
	@Test
	public void testConstructor() {
		// Arrange
		String theme = "cat";
		String difficulty = "easy";
		Deck deck = new Deck(theme, difficulty);
		assertNotNull(deck);
	}

	@Test
	public void testEasyCat() {
		String theme = "cat";
		String difficulty = "easy";
		Deck deck = new Deck(theme, difficulty);

		//ArrayList<Card> createdDeck = deck.createDeck();

		//assertEquals(6, createdDeck.size());
	}

	@Test
	public void testHardCat() {
		String theme = "cat";
		String difficulty = "hard";
		Deck deck = new Deck(theme, difficulty);

		//ArrayList<Card> createdDeck = deck.createDeck();

		//assertEquals(12, createdDeck.size());
	}

	@Test
	public void testEasyCar() {
		String theme = "car";
		String difficulty = "easy";
		Deck deck = new Deck(theme, difficulty);

		//ArrayList<Card> createdDeck = deck.createDeck();

		//assertEquals(6, createdDeck.size());
	}

	@Test
	public void testHardCar() {
		String theme = "car";
		String difficulty = "hard";
		Deck deck = new Deck(theme, difficulty);

		//ArrayList<Card> createdDeck = deck.createDeck();

		//assertEquals(12, createdDeck.size());
	}

	@Test
	public void testEasyGalaxy() {
		String theme = "galaxy";
		String difficulty = "easy";
		Deck deck = new Deck(theme, difficulty);

		//ArrayList<Card> createdDeck = deck.createDeck();

		//assertEquals(6, createdDeck.size());
	}

	@Test
	public void testHardGalaxy() {
		String theme = "galaxy";
		String difficulty = "hard";
		Deck deck = new Deck(theme, difficulty);

		//ArrayList<Card> createdDeck = deck.createDeck();

		//assertEquals(12, createdDeck.size());
	}

}
