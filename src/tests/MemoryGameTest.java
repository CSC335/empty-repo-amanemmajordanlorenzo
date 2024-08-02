package tests;
/*
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import models.Card;
import models.MemoryGame;
*/

public class MemoryGameTest {
	/*
	@Test
	public void testGameConstructor() {
		MemoryGame game = new MemoryGame("cat", "easy");
		assertEquals(game.getGameTheme(), "cat");
		assertEquals(game.getGameDifficulty(), "easy");
		assertEquals(game.getGameDeck().get(0).getTitle(), "cat1");
	}

	@Test
	public void testCompareCards() {
		//MemoryGame game = new MemoryGame("cat", "hard");

		Card card1 = new Card("cat1", "hard");
		Card card2 = new Card("cat1", "hard");

		assertEquals(game.getGameMatch(), false);
		//game.compareCards(card1, card2);
		assertEquals(game.getGameMatch(), true);

		card1 = new Card("cat1", "hard");
		card2 = new Card("cat2", "hard");
		//game.compareCards(card1, card2);
		assertEquals(game.getGameMatch(), false);
	}

	@Test
	public void testPlayGame() {
		MemoryGame game = new MemoryGame("cat", "easy");
		assertEquals(game.getGameDeck().size(), 6);
		assertEquals(game.getGameDeck().get(0).getTitle(), "cat1");
		assertEquals(game.getGameDeck().get(1).getTitle(), "cat1");
		assertEquals(game.getGameDeck().get(2).getTitle(), "cat2");
		assertEquals(game.getGameDeck().get(3).getTitle(), "cat2");
		assertEquals(game.getGameDeck().get(4).getTitle(), "cat3");
		assertEquals(game.getGameDeck().get(5).getTitle(), "cat3");
		assertThrows(IndexOutOfBoundsException.class, () -> {
			game.getGameDeck().get(6);
		});

		game.playGame();

		assertEquals(game.getGameNumGuesses(), 3);
		assertEquals(game.getGameDeck().size(), 0);
		//assertEquals(game.getGameStats().getEasyCatHighScore(), 3);

		// Test new game
		//game.newGame("car", "hard");
		assertEquals(game.getGameTheme(), "car");
		assertEquals(game.getGameDifficulty(), "hard");
		assertEquals(game.getGameDeck().get(0).getTitle(), "car1");
		assertEquals(game.getGameNumGuesses(), 0);
		game.playGame();
		assertEquals(game.getGameNumGuesses(), 6);
		assertEquals(game.getGameDeck().size(), 0);
		//assertEquals(game.getGameStats().getHardCarHighScore(), 6);
	}*/
}
