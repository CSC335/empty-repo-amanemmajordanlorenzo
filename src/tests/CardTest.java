package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import models.Card;

public class CardTest {

	@Test
	public void testConstructorAndGetters() {
		String expectedTitle = "Sample Title";
		String expectedPath = "/path/to/sample";

		Card card = new Card(expectedTitle, expectedPath);

		assertEquals(expectedTitle, card.getTitle());
		assertEquals(expectedPath, card.getPath());
	}

	@Test
	public void testGetTitle() {
		String expectedTitle = "Sample Title";
		String filePath = "/path/to/sample";
		Card card = new Card(expectedTitle, filePath);

		String actualTitle = card.getTitle();

		assertEquals(expectedTitle, actualTitle);
	}

	@Test
	public void testGetPath() {
		String title = "Sample Title";
		String expectedPath = "/path/to/sample";
		Card card = new Card(title, expectedPath);

		String actualPath = card.getPath();

		assertEquals(expectedPath, actualPath);
	}
}
