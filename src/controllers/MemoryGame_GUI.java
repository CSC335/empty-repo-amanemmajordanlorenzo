package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Card;
import models.Deck;
import models.MemoryGame;
import models.Stats;

/**
 * This class acts as the user interface for Memory Game. All of the neccessary
 * user inputs are provided by this class.
 */
public class MemoryGame_GUI extends Application {
	private Scene mainScene;
	private Scene themeScene;
	private Scene difficultyScene;
	private Scene gameScene;

	private Scene endGameScene;
	private GridPane leaderboard;

	private Stage mainStage;
	private MemoryGame game;
	private String theme;
	private Card card1, card2;
	private int numSel = 0;
	private ArrayList<Button> buttonArray = new ArrayList<Button>();
	private int index1, index2;
	private boolean flippedOver1, flippedOver2 = false;
	private int numMatches = 0;
	private Stats stats = new Stats();
	private Deck deck;
	private GridPane cardGrid;
	private String difficulty;
	private boolean isMuted = false;
	private MediaPlayer mediaPlayer;
	private AudioClip soundEffect;
	private ToggleButton muteButton;

	@Override
	public void start(Stage primaryStage) {
		this.mainStage = primaryStage;
		readState();
		addMuteButton();
		setupMainScene();
		mainStage.setTitle("Welcome to MemoryGame");
		mainStage.setScene(mainScene);
		mainStage.show();
		mainStage.setResizable(false);
		playLobbyMusic();
	}

	private void setupMainScene() {
		VBox root = new VBox(20);
		Image backgroundImage = new Image("file:Documents/cardimages/welcomescreen.png");
		String backgroundImageUrl = "url('" + backgroundImage.getUrl() + "')";
		String style = "-fx-background-image: " + backgroundImageUrl + ";";
		root.setAlignment(Pos.TOP_CENTER);
		root.setStyle(style);
		// Create a StackPane to contain the play button
		StackPane buttonContainer = new StackPane();
		Button playButton = new Button("Start New Game");
		playButton.setPadding(new Insets(10, 7, 10, 7));
		playButton.setFont(Font.font("Palatino", FontWeight.BOLD, 14));
		playButton.setStyle(
				"-fx-background-color: #c69fa5; -fx-text-fill: #272744; -fx-border-color: #1D1231FF; -fx-border-width: 2px;");
		playButton.setOnAction(e -> {
			switchToThemeScene();
			playSoundEffect("retroclick.wav");
		});

		// Set padding for the button container to adjust its position
		buttonContainer.setPadding(new Insets(320, 0, 40, 2)); // Adjust the bottom inset (20 in this example) to
																// position the button lower
		//root.setStyle("-fx-border-style: solid; -fx-border-width: 5px; -fx-border-color: red;");
	
		
		buttonContainer.getChildren().add(playButton);
		//buttonContainer.setStyle("-fx-border-style: solid; -fx-border-width: 5px;");
		
		HBox muteButtonContainer = new HBox();
		muteButtonContainer.setPadding(new Insets(35, 0, 0, 35));
		muteButtonContainer.getChildren().add(muteButton);
		//muteButtonContainer.setStyle("-fx-border-style: dashed; -fx-border-width: 5px;");
		
		root.getChildren().addAll(muteButtonContainer, buttonContainer);
		
		mainScene = new Scene(root, 800, 600);
		//root.setStyle("-fx-border-style: solid; -fx-border-width: 5px;");
		// stops music from continously playing when choosing to start a new game
		stopMusic();
	}

	/**
	 * This section implements music and sound effect functionality.
	 */
	private void playSong(String fileName) {
		String projectDir = System.getProperty("user.dir"); // get the project directory
		String path = Paths.get(projectDir, "music", fileName).toUri().toString();
		Media media = new Media(path);
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // loop the music
		mediaPlayer.setMute(isMuted); // mute/unmute the music based on the current state
		mediaPlayer.play();
	}

	private void playSoundEffect(String fileName) {
		String projectDir = System.getProperty("user.dir"); // get the project directory
		String path = Paths.get(projectDir, "music", fileName).toUri().toString();
		soundEffect = new AudioClip(path);
		soundEffect.play();
	}

	private void playLobbyMusic() {
		String musicFileName = "lobbymusic.mp3";
		playSong(musicFileName);
	}

	// method to toggle mute/unmute
	private void toggleMute() {
		isMuted = muteButton.isSelected(); // update the mute status
		
		Text muteText = new Text("Mute");
		Text unmuteText = new Text("Unmute");
		
		if (!isMuted) {
			muteButton.setGraphic(muteText);
		}
		else {
			muteButton.setGraphic(unmuteText);
		}
		
		if (mediaPlayer != null) {
			mediaPlayer.setMute(isMuted); // mute/unmute the music
		}
	}

	// should hypothetically be able to addMuteButton(root); in the GUI scenes to
	// have the mute button,
	// but i'm having trouble integrating it independently.
	// leaving this here for now in case we can add it since this still successfully
	// mutes the music.
	private void addMuteButton() {
		Text muteText = new Text("Mute");
		Text unmuteText = new Text("Unmute");
		
		muteButton = new ToggleButton();
		muteButton.setStyle(
				"-fx-background-color: #c69fa5; -fx-text-fill: #272744; -fx-border-color: #1D1231FF; -fx-border-width: 2px;");
		if (!isMuted) {
			muteButton.setGraphic(muteText);
		}
		else {
			muteButton.setGraphic(unmuteText);
		}
		
		
		muteButton.setPrefSize(50, 25);
		muteButton.setOnAction(e -> toggleMute());

		// Set the position of the mute button to top left
		//VBox.setMargin(muteButton, new Insets(10, 0, 0, 10)); // Adjust top, right, bottom, left margins as needed

		// Do not set the alignment for the layout
		//layout.setAlignment(Pos.BOTTOM_CENTER);

		//layout.getChildren().add(muteButton);
	}

	private void stopMusic() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
		}
	}

	private void switchToThemeScene() {
		if (themeScene == null) { // create the scene if it hasn't been created yet
			setupThemeScene();
		}
		mainStage.setScene(themeScene);
	}

	private void setupThemeScene() {
		VBox layout = new VBox(20);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #272744;");
		Text chooseThemeText = new Text("Choose the Theme:");
		chooseThemeText.setFont(Font.font("Palatino", FontWeight.BOLD, 30));
		chooseThemeText.setStyle("-fx-fill: #c69fa5;");
		// HBox for buttons
		HBox buttonBox = new HBox(20); // Increased space between buttons
		buttonBox.setAlignment(Pos.CENTER);
		// Button for Theme 1 (Earth)
		ImageView earthView = new ImageView(new Image("file:Documents/cardimages/spacecardback.png"));
		earthView.setFitHeight(190); // Increased size of the image
		earthView.setFitWidth(140);
		Text themeText = new Text("Space");
		themeText.setFill(Color.web("#c69fa5")); // Set the text color (REAL)
		VBox earthBox = new VBox(5, earthView, themeText);
		earthBox.setAlignment(Pos.CENTER);
		Button theme1 = new Button();
		theme1.setGraphic(earthBox);
		theme1.setStyle(
				"-fx-background-color: #272744; -fx-text-fill: #0C1126FF; -fx-padding: 10; -fx-border-color: #c69fa5; -fx-border-width: 2;");
		theme1.setFont(new Font("Arial", 24));
		// Button for Theme 2 (Cats)
		ImageView catView = new ImageView(new Image("file:Documents/cardimages/catcardback.png"));
		catView.setFitHeight(190); // Increased size of the image
		catView.setFitWidth(140);
		Text catText = new Text("Cats");
		catText.setFill(Color.web("#c69fa5")); // Set the text color (REAL)
		VBox catBox = new VBox(5, catView, catText);
		catBox.setAlignment(Pos.CENTER);

		Button theme2 = new Button();
		theme2.setGraphic(catBox);
		theme2.setStyle(
				"-fx-background-color: #272744; -fx-text-fill: #0C1126FF; -fx-padding: 10; -fx-border-color: #c69fa5; -fx-border-width: 2;");
		theme2.setFont(new Font("Arial", 24));
		ImageView carView = new ImageView(new Image("file:Documents/cardimages/carcardback.png"));
		carView.setFitHeight(190);
		carView.setFitWidth(140);
		Text carText = new Text("Cars");
		carText.setFill(Color.web("#c69fa5")); // Set the text color (REAL)
		VBox carBox = new VBox(5, carView, carText);
		carBox.setAlignment(Pos.CENTER);

		Button theme3 = new Button();
		theme3.setGraphic(carBox);
		theme3.setStyle(
				"-fx-background-color: #272744; -fx-text-fill: #0C1126FF; -fx-padding: 10; -fx-border-color: #c69fa5; -fx-border-width: 2;");
		theme3.setFont(new Font("Arial", 24));

		theme1.setOnAction(e -> {
			switchToDifficultyScene(1);
			stopMusic();
			playSong("spacesong.mp3"); // play theme 1 music
			playSoundEffect("pingeffect.wav");
		});
		theme2.setOnAction(e -> {
			switchToDifficultyScene(2);
			stopMusic();
			playSong("catsong.mp3"); // play theme 2 music
			playSoundEffect("pingeffect.wav");
		});
		theme3.setOnAction(e -> {
			switchToDifficultyScene(3);
			stopMusic();
			playSong("carsong.mp3"); // play theme 3 music
			playSoundEffect("pingeffect.wav");
		});
		// Adding buttons to the HBox
		buttonBox.getChildren().addAll(theme1, theme2, theme3);
		// Add the HBox to the main VBox
		layout.getChildren().addAll(chooseThemeText, buttonBox);
		themeScene = new Scene(layout, 800, 600);
	}

	private void switchToDifficultyScene(int themeNumber) {
		setupDifficultyScene(themeNumber);
		mainStage.setScene(difficultyScene);
	}

	private void setupDifficultyScene(int themeNumber) {
		VBox layout = new VBox(20);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #272744;");
		Text chooseDifficultyText = new Text("Choose the Difficulty:");
		chooseDifficultyText.setFont(Font.font("Palatino", FontWeight.BOLD, 30));
		chooseDifficultyText.setStyle("-fx-fill: #c69fa5;");
		Button easyButton = new Button("Easy");
		easyButton.setFont(Font.font("Palatino", 28));
		easyButton.setStyle("-fx-background-color: #f2d3ab; -fx-text-fill: #0C1126FF;");
		easyButton.setOnAction(e -> {
			startGame(true, themeNumber);
			playSoundEffect("pingeffect.wav");
		});
		Button hardButton = new Button("Hard");
		hardButton.setFont(Font.font("Palatino", 28));
		hardButton.setStyle("-fx-background-color: #8b6d9c; -fx-text-fill: #0C1126FF;");
		hardButton.setOnAction(e -> {
			startGame(false, themeNumber);
			playSoundEffect("pingeffect.wav");
		});
		layout.getChildren().addAll(chooseDifficultyText, easyButton, hardButton);
		difficultyScene = new Scene(layout, 800, 600);
	}

	private void startGame(boolean isEasy, int themeNumber) {
		int cardCount = isEasy ? 6 : 12;
		difficulty = isEasy ? "easy" : "hard";
		setupGameScene(cardCount, themeNumber, difficulty);
		mainStage.setScene(gameScene);
	}

	private void setupGameScene(int cardCount, int themeNumber, String difficulty) {
		if (themeNumber == 1)
			theme = "space";
		else if (themeNumber == 2)
			theme = "cat";
		else
			theme = "car";
		game = new MemoryGame(theme, difficulty, deck);
		VBox layout = new VBox(20);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #272744;");
		Text gameInfoText = new Text("You are playing " + (cardCount == 6 ? "easy" : "hard") + " mode.");
		gameInfoText.setFont(Font.font("Palatino", FontWeight.BOLD, 20));
		gameInfoText.setStyle("-fx-fill: #c69fa5;");
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setFitToWidth(true);
		scrollPane.setPrefHeight(400);
		scrollPane.setStyle("-fx-background: #272744; -fx-background-color: #322947FF;");
		cardGrid = new GridPane();
		int gap = cardCount == 6 ? 20 : 10;
		int Vgap = cardCount == 6 ? 35 : 10;
		cardGrid.setHgap(Vgap);
		cardGrid.setVgap(gap);
		cardGrid.setAlignment(Pos.CENTER);
		// Create deck and lay out the cards.
		deck = new Deck(themeNumber == 1 ? "space" : (themeNumber == 2 ? "cat" : "car"), difficulty);
		ArrayList<Card> shuffledDeck = deck.createDeck(); // Create and shuffle the deck
		Collections.shuffle(shuffledDeck);
		int cardsPerColumn = cardCount == 6 ? 2 : 2;
		for (int i = 0; i < cardCount; i++) {
			String cardBackImage = themeNumber == 1 ? "spacecardback.png"
					: (themeNumber == 2 ? "catcardback.png" : "carcardback.png");
			ImageView imageView = new ImageView(new Image("file:Documents/cardimages/" + cardBackImage));
			imageView.setFitWidth(100);
			imageView.setFitHeight(140);
			Button cardButton = new Button();
			cardButton.setGraphic(imageView);
			cardButton.setStyle("-fx-background-color: #272744;");
			Card card = shuffledDeck.get(i);
			// Get the ImageView from the graphic of the button
			ImageView cardFrontView = new ImageView(new Image("file:Documents/cardimages/" + card.getPath()));
			cardFrontView.setFitWidth(100);
			cardFrontView.setFitHeight(140);
			cardFrontView.setVisible(false); // Initially hide the front of the card
			RotateTransition rotator = createRotator(cardButton, cardFrontView); // Pass the Button and ImageView of the
																					// Card
			StackPane cardPane = new StackPane(imageView, cardFrontView);
			cardButton.setGraphic(cardPane);
			cardButton.setOnAction(e -> {
				playSoundEffect("flip.mp3");
				if (!flippedOver2 && !cardButton.isDisabled()) { // Check if no animation is ongoing and the card is not
																	// disabled
					rotator.play();
					if (numSel == 0) {
						index1 = buttonArray.indexOf(cardButton);
						card1 = shuffledDeck.get(index1);
						flippedOver1 = true;
					} else if (numSel == 1) {
						index2 = buttonArray.indexOf(cardButton);
						if (index1 == index2)
							return; // Prevent matching the same card
						card2 = shuffledDeck.get(index2);
						flippedOver2 = true;
						checkForMatch(); // Check for a match when the second card is flipped
					}
					numSel = (numSel + 1) % 2; // Toggles between 0 and 1 for selecting cards
				}
			});
			int column = i / cardsPerColumn;
			int row = i % cardsPerColumn;
			cardGrid.add(cardButton, column, row);
			buttonArray.add(cardButton);
		}
		scrollPane.setContent(cardGrid);
		layout.getChildren().addAll(gameInfoText, scrollPane);
		gameScene = new Scene(layout, 800, 600);
	}

	private void checkForMatch() {
		if (game.compareCards(card1, card2)) {
			handleMatch(); // Handle match scenario
		} else {
			handleNoMatch(); // Handle no match scenario
		}
	}

	private void handleMatch() {
		PauseTransition pause = new PauseTransition(Duration.seconds(1));

		pause.setOnFinished(event -> {
			buttonArray.get(index1).setDisable(true);
			buttonArray.get(index2).setDisable(true);
			Pane placeholder1 = new Pane();
			placeholder1.setPrefSize(115, 142);
			Pane placeholder2 = new Pane();
			placeholder2.setPrefSize(115, 142);
			cardGrid.getChildren().remove(buttonArray.get(index1));
			cardGrid.add(placeholder1, GridPane.getColumnIndex(buttonArray.get(index1)),
					GridPane.getRowIndex(buttonArray.get(index1)));
			cardGrid.getChildren().remove(buttonArray.get(index2));
			cardGrid.add(placeholder2, GridPane.getColumnIndex(buttonArray.get(index2)),
					GridPane.getRowIndex(buttonArray.get(index2)));
			game.incGuesses();
			numMatches++;
			//System.out.println("Number of matches made: " + numMatches);
			resetCardState();

			// Check the game state after each match
			checkGameState();
		});
		pause.play();
	}

	private void handleNoMatch() {
		PauseTransition pause = new PauseTransition(Duration.seconds(1));
		pause.setOnFinished(event -> {
			playSoundEffect("flip.mp3");
			// Flip both cards back
			flipCardBack(index1);
			flipCardBack(index2);
			resetCardState();
			game.incGuesses();
		});
		pause.play();
	}

	private void resetCardState() {
		numSel = 0;
		flippedOver1 = false;
		flippedOver2 = false;
	}

	// Checks if the game is over and ends the game if needed.
	private void checkGameState() {
		//System.out.println("Difficulty = " + difficulty);
		if (numMatches == 3 && difficulty.equals("easy") || numMatches == 6 && difficulty.equals("hard")) {
			stats.addScore(theme, difficulty, game.getGameNumGuesses());
			writeState();
			// All matches found, switch to end game screen
			displayEndGamePopup();
		}
	}

	private void displayEndGamePopup() {
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setFitToWidth(true);
		scrollPane.setPrefHeight(400);
		scrollPane.setStyle("-fx-background: #272744; -fx-background-color: #322947FF;");

		VBox endGameBox = new VBox();
		endGameBox.setAlignment(Pos.CENTER);
		
		Text congratsText = new Text("Congratulations! You have found all matches!");
		
		Text reportText = new Text("It took you " + game.getGameNumGuesses() + " guesses!");
		reportText.setFont(Font.font("Palatino", FontWeight.BOLD, 20));
		reportText.setFill(Color.web("#c69fa5"));
		
		Text highScoreText = new Text("High Scores");
		highScoreText.setFont(Font.font("Palatino", FontWeight.BOLD, 20));
		highScoreText.setFill(Color.web("#c69fa5"));

		setupLeaderboard();
		leaderboard.setAlignment(Pos.CENTER);

		Text againText = new Text("Would you like to play again?");
		congratsText.setFont(Font.font("Palatino", FontWeight.BOLD, 20));
		congratsText.setStyle("-fx-fill: #c69fa5;");
		againText.setFont(Font.font("Palatino", FontWeight.BOLD, 20));
		againText.setStyle("-fx-fill: #c69fa5;");
		Button yesButton = new Button("Yes");
		yesButton.setStyle("-fx-background-color: #f2d3ab; -fx-text-fill: #0C1126FF;");
		Button quitButton = new Button("Quit");
		quitButton.setStyle("-fx-background-color: #8b6d9c; -fx-text-fill: #0C1126FF;");
		quitButton.setOnAction(e -> {
			System.exit(0);
		});
		HBox yesQuit = new HBox();
		yesQuit.setSpacing(15);
		yesQuit.setAlignment(Pos.CENTER);
		yesQuit.getChildren().addAll(yesButton, quitButton);

		yesButton.setOnAction(e -> {
			playSoundEffect("pingeffect.wav");
			buttonArray = new ArrayList<Button>();
			numMatches = 0;
			flippedOver1 = false;
			flippedOver2 = false;
			themeScene = null;
			start(mainStage);
		});

		// Add some space below the elements
		VBox.setMargin(congratsText, new Insets(40, 0, 5, 0)); // Add 20 pixels bottom margin
		VBox.setMargin(reportText, new Insets(0, 0, 20, 0));
		VBox.setMargin(highScoreText, new Insets(15, 0, 5, 0));
		VBox.setMargin(leaderboard, new Insets(0, 0, 20, 0)); // Add 20 pixels bottom margin
		VBox.setMargin(againText, new Insets(20, 0, 0, 0)); // Add 20 pixels top margin
		VBox.setMargin(yesQuit, new Insets(20, 0, 0, 0)); // Add 20 pixels top margin
		endGameBox.getChildren().addAll(congratsText, reportText, highScoreText, leaderboard, againText, yesQuit);

		scrollPane.setContent(endGameBox);
		endGameScene = new Scene(scrollPane, 800, 600);
		mainStage.setScene(endGameScene);
	}

	private void flipCardBack(int index) {
		Button cardButton = buttonArray.get(index);
		RotateTransition rotator = createRotator(cardButton,
				(ImageView) ((StackPane) cardButton.getGraphic()).getChildren().get(1));
		rotator.play();
	}

	private RotateTransition createRotator(Button cardButton, ImageView cardFrontView) {
		// First half of the rotation (0 to 90 degrees)
		RotateTransition firstHalf = new RotateTransition(Duration.millis(500), cardButton);
		firstHalf.setAxis(Rotate.Y_AXIS);
		firstHalf.setFromAngle(0);
		firstHalf.setToAngle(90);
		firstHalf.setInterpolator(Interpolator.LINEAR);
		// Second half of the rotation (90 to 180 degrees)
		RotateTransition secondHalf = new RotateTransition(Duration.millis(500), cardButton);
		secondHalf.setAxis(Rotate.Y_AXIS);
		secondHalf.setFromAngle(90);
		secondHalf.setToAngle(0);
		secondHalf.setInterpolator(Interpolator.LINEAR);
		// Toggle visibility of the card front when the first half of rotation finishes
		firstHalf.setOnFinished(e -> {
			cardFrontView.setVisible(!cardFrontView.isVisible());
			secondHalf.play(); // Start the second half of rotation
		});
		// Chain the second half of rotation to play after the first half
		firstHalf.setAutoReverse(false);
		firstHalf.setCycleCount(1);
		secondHalf.setAutoReverse(true);
		secondHalf.setCycleCount(1);
		return firstHalf;
	}

	private void setupLeaderboard() {
		leaderboard = new GridPane();
		leaderboard.setAlignment(Pos.CENTER);
		leaderboard.setHgap(10);
		leaderboard.setVgap(10);
		leaderboard.setGridLinesVisible(true);
		leaderboard.setStyle("-fx-background-color: #272744; -fx-padding: 10px;");

		// Title
		Text title = new Text("Leaderboard");
		title.setFont(Font.font("Palatino", FontWeight.BOLD, 24));
		title.setFill(Color.web("#c69fa5")); // Change color here

		// Column and row labels
		Text themeLabel = new Text("  Themes");
		Text easyLabel = new Text("  Easy");
		Text hardLabel = new Text("  Hard");
		Text catLabel = new Text("  Cat");
		Text carLabel = new Text("  Car");
		Text spaceLabel = new Text("  Space");

		// Set font and color for labels
		themeLabel.setFont(Font.font("Palatino", FontWeight.BOLD, 16));
		easyLabel.setFont(Font.font("Palatino", FontWeight.BOLD, 16));
		hardLabel.setFont(Font.font("Palatino", FontWeight.BOLD, 16));
		catLabel.setFont(Font.font("Palatino", FontWeight.BOLD, 16));
		carLabel.setFont(Font.font("Palatino", FontWeight.BOLD, 16));
		spaceLabel.setFont(Font.font("Palatino", FontWeight.BOLD, 16));

		themeLabel.setFill(Color.web("#c69fa5"));
		easyLabel.setFill(Color.web("#c69fa5")); // Change color here
		hardLabel.setFill(Color.web("#c69fa5")); // Change color here
		catLabel.setFill(Color.web("#c69fa5")); // Change color here
		carLabel.setFill(Color.web("#c69fa5")); // Change color here
		spaceLabel.setFill(Color.web("#c69fa5")); // Change color here

		// Add labels to the grid
		leaderboard.add(themeLabel, 0, 0);
		leaderboard.add(easyLabel, 1, 0);
		leaderboard.add(hardLabel, 2, 0);
		leaderboard.add(catLabel, 0, 1);
		leaderboard.add(carLabel, 0, 2);
		leaderboard.add(spaceLabel, 0, 3);

		// High score values
		String spaceEasy = stats.getEasySpaceHighScore() + "";
		String spaceHard = stats.getHardSpaceHighScore() + "";
		String catEasy = stats.getEasyCatHighScore() + "";
		String catHard = stats.getHardCatHighScore() + "";
		String carEasy = stats.getEasyCarHighScore() + "";
		String carHard = stats.getHardCarHighScore() + "";

		// Create Text objects for high scores
		Text spaceEasyScore = new Text(spaceEasy);
		Text spaceHardScore = new Text(spaceHard);
		Text catEasyScore = new Text(catEasy);
		Text catHardScore = new Text(catHard);
		Text carEasyScore = new Text(carEasy);
		Text carHardScore = new Text(carHard);

		// Set font and color for high scores
		spaceEasyScore.setFont(Font.font("Palatino", FontWeight.BOLD, 16));
		spaceHardScore.setFont(Font.font("Palatino", FontWeight.BOLD, 16));
		catEasyScore.setFont(Font.font("Palatino", FontWeight.BOLD, 16));
		catHardScore.setFont(Font.font("Palatino", FontWeight.BOLD, 16));
		carEasyScore.setFont(Font.font("Palatino", FontWeight.BOLD, 16));
		carHardScore.setFont(Font.font("Palatino", FontWeight.BOLD, 16));

		spaceEasyScore.setFill(Color.web("#c69fa5")); // Change color here
		spaceHardScore.setFill(Color.web("#c69fa5")); // Change color here
		catEasyScore.setFill(Color.web("#c69fa5")); // Change color here
		catHardScore.setFill(Color.web("#c69fa5")); // Change color here
		carEasyScore.setFill(Color.web("#c69fa5")); // Change color here
		carHardScore.setFill(Color.web("#c69fa5")); // Change color here

		// Add high scores to the grid
		leaderboard.add(spaceEasyScore, 1, 3);
		leaderboard.add(spaceHardScore, 2, 3);
		leaderboard.add(catEasyScore, 1, 1);
		leaderboard.add(catHardScore, 2, 1);
		leaderboard.add(carEasyScore, 1, 2);
		leaderboard.add(carHardScore, 2, 2);

		// Set alignment for high scores
		GridPane.setHalignment(spaceEasyScore, HPos.CENTER);
		GridPane.setHalignment(spaceHardScore, HPos.CENTER);
		GridPane.setHalignment(catEasyScore, HPos.CENTER);
		GridPane.setHalignment(catHardScore, HPos.CENTER);
		GridPane.setHalignment(carEasyScore, HPos.CENTER);
		GridPane.setHalignment(carHardScore, HPos.CENTER);

		// Set column constraints
		ColumnConstraints col1 = new ColumnConstraints(150);
		ColumnConstraints col2 = new ColumnConstraints(150);
		ColumnConstraints col3 = new ColumnConstraints(150);
		leaderboard.getColumnConstraints().addAll(col1, col2, col3);

		// Set row constraints
		RowConstraints row = new RowConstraints(50);
		leaderboard.getRowConstraints().addAll(row, row, row, row);


		// Add title and leaderboard to the scene
		VBox leaderboardBox = new VBox();
		leaderboardBox.setAlignment(Pos.TOP_CENTER); // Align to the top
		leaderboardBox.getChildren().addAll(title, leaderboard);
	}

	// Saves the high scores.
	private void writeState() {
		String fileName = "statsSave.ser";

		try {
			FileOutputStream bytesToDisk = new FileOutputStream(fileName);
			ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);
			outFile.writeObject(stats);
			outFile.close();
		} catch (IOException ioe) {
			System.out.println("Writing objects failed");
		}
	}

	// Loads the high scores.
	private void readState() {
		try {
			FileInputStream rawBytes = new FileInputStream("statsSave.ser");
			ObjectInputStream inFile = new ObjectInputStream(rawBytes);

			stats = (Stats) inFile.readObject();
			inFile.close();
			rawBytes.close();

		} catch (FileNotFoundException f) {
			System.out.println("File Not Found Exception");
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class Not Found Exception");
			return;
		} catch (IOException i) {
			System.out.println("IO Exception");
			return;
		}
	}

	/**
	 * The main method of the MemoryGame_GUI class.
	 * 
	 * @param args Needed to run the main method.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}