package application;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class BuildLevel {
	private static Stage areUSure;

	/*
	 * The following is to visualize the text file to real scene
	 * It also set the interactions
	 */
	public static void closeAreUSure(){
		if(areUSure!=null){
			areUSure.close();
			areUSure=null;
		}
	}

	public static Scene getScene(String fileToRead){
		//Reset the coins
		PlayerMovement.resetCoinCollected();
		//create the panel
		Pane root = new Pane();
		//get the scanner which will read from the file selected
		Scanner input = ReadFile.get(fileToRead);
		//read in the players spawns point and creates the player
		input.next();
		Rectangle player = Default.createPlayer(input.nextInt(),input.nextInt());
		//a basic delay with player fade into the scene which also help the player get prepared
		//set the player to dead first
		PlayerMovement.setDead(true);
		//create a fade transition which will show the animation of the player block fade in
		FadeTransition fadeout = new FadeTransition(Duration.millis(1000),player);
		fadeout.setFromValue(0.0);
		fadeout.setToValue(1.0);
		fadeout.play();
		//with delay spawn the player and set dead equal to false
		PauseTransition delay = new PauseTransition(Duration.millis(1050));
		delay.setOnFinished(e -> {
			player.setOpacity(1.0);
			PlayerMovement.setDead(false);
		});
		delay.play();

		//read in the red dots' properties, create them and stored them all in an ArrayList
		input.next();
		ArrayList<Circle> dots = new ArrayList<>();
		while(input.hasNextInt()){
			Circle dot = Default.createDot(input.nextInt(), input.nextInt());
			new DotMovement(dot, input.nextInt(), input.nextInt(), input.nextInt(),player, input.nextInt());
			dots.add(dot);
		}

		//read in the yellow areas' properties, create them and stored them all in an ArrayList
		input.next();
		ArrayList<Rectangle> yellowAreas = new ArrayList<>();
		while(input.hasNextInt()){
			Rectangle yellowArea = Default.createYellow(input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt());
			yellowAreas.add(yellowArea);
		}

		//read in the green areas' properties, create them and stored them all in an ArrayList
		input.next();
		ArrayList<Rectangle> greenAreas = new ArrayList<>();
		while(input.hasNextInt()){
			Rectangle greenArea = Default.createGreen(input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt());
			greenAreas.add(greenArea);
		}
		//read in the blue walls' properties, create them and stored them all in an ArrayList
		input.next();
		ArrayList<Rectangle> blueWalls = new ArrayList<>();
		while(input.hasNextInt()){
			Rectangle blueWall = Default.createBlue(input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt());
			blueWalls.add(blueWall);
		}

		//read in the coins' properties, create them and stored them all in an ArrayList
		input.next();
		ArrayList<Circle> coins = new ArrayList<>();
		int totalNumberofCoins = input.nextInt();
		while(input.hasNextInt()){
			Circle coin = Default.createCoin(input.nextInt(), input.nextInt());
			coins.add(coin);
		}
		//close the input because all the data needed is collected
		input.close();
		//make the thing that is need for every frame

		//make a label in the top left corner which will indicate the number of death
		Label numberOfDeath = new Label("Deaths: "+Integer.toString(DotMovement.getNumberOfDeath()));
		numberOfDeath.setId("deathLabel");
		numberOfDeath.setLayoutX(20);
		numberOfDeath.setLayoutY(10);
		//make a label in the top right corner which will indicate the coins collected
		Label coinsCollected = new Label("Coins: "+Integer.toString(PlayerMovement.getCoinCollected())+"/"+Integer.toString(totalNumberofCoins));
		coinsCollected.setId("deathLabel");
		coinsCollected.setLayoutX(630);
		coinsCollected.setLayoutY(10);
		//make a back button in the bottom right corner which will allow the user to go back
		Button backToMenu = new Button("Back");
		backToMenu.setId("menuButton");
		backToMenu.setLayoutX(630);
		backToMenu.setLayoutY(500);

		try{
			if(Integer.parseInt(fileToRead.split("\\.")[0])==0){
				backToMenu.setOnAction(e2->{
					MainGameStart.getWindow().hide();
					Menu.getNewWindow().show();
				});
			}else{
				backToMenu.setOnAction(e2->{
					if(areUSure==null){
						areUSure = new Stage();
						areUSure.sizeToScene();
						areUSure.setResizable(false);
						areUSure.setScene(AreUSure.getScene());
						areUSure.getIcons().add(new Image("Icon.png"));
						areUSure.setTitle("Mission Impossible - Leo Zhu");
						areUSure.initStyle(StageStyle.TRANSPARENT);
						areUSure.initModality(Modality.APPLICATION_MODAL);
						areUSure.show();
						areUSure.setOnCloseRequest(e1->e1.consume());
						//Main.changeSceneTo(Menu.getScene());
					}
				});
			}
		}catch(NumberFormatException e){
			backToMenu.setOnAction(e2->{
				if(areUSure==null){
					areUSure = new Stage();
					areUSure.sizeToScene();
					areUSure.setResizable(false);
					areUSure.setScene(AreUSure.getScene());
					areUSure.getIcons().add(new Image("Icon.png"));
					areUSure.setTitle("Mission Impossible - Leo Zhu");
					areUSure.initStyle(StageStyle.TRANSPARENT);
					areUSure.initModality(Modality.APPLICATION_MODAL);
					areUSure.show();
					areUSure.setOnCloseRequest(e1->e1.consume());
					//Main.changeSceneTo(Menu.getScene());
				}
			});
		}

		//make a shadow of the player with 0.8 opacity and with a delay, this shadow will follow the player's movement as well
		Rectangle shadow = Default.createPlayer((int)player.getX(), (int)player.getY());
		shadow.setOpacity(0.0);
		AnimationTimer shadowAnimation = new AnimationTimer() {
			public void handle(long now) {
				if(PlayerMovement.getDead()==false)
					shadow.setOpacity(0.8);
				else
					shadow.setOpacity(0.0);

				shadow.setX(player.getX());
				shadow.setY(player.getY());
				numberOfDeath.setText("Deaths: "+Integer.toString(DotMovement.getNumberOfDeath()));
				coinsCollected.setText("Coins: "+Integer.toString(PlayerMovement.getCoinCollected())+"/"+Integer.toString(totalNumberofCoins));
			}
		};
		shadowAnimation.start();

		ArrayList<Rectangle> greenAreaUnderPlayer = new ArrayList<>();
		//add in everything to the scene
		for(Rectangle everyYellowArea:yellowAreas)
			root.getChildren().add(everyYellowArea);
		for(Rectangle everyGreenArea:greenAreas){
			Shape intersect = Shape.intersect(everyGreenArea, player);
			if (intersect.getBoundsInLocal().getWidth() != -1){
				greenAreaUnderPlayer.add(everyGreenArea);
			}
		}
		greenAreas.removeAll(greenAreaUnderPlayer);
		for(Rectangle everyGreenArea:greenAreas){
			root.getChildren().add(everyGreenArea);
		}

		for(Rectangle everyBlueWall:blueWalls)
			root.getChildren().add(everyBlueWall);
		for(Circle everyDot:dots)
			root.getChildren().add(everyDot);
		for(Circle everyCoin:coins)
			root.getChildren().add(everyCoin);

		root.getChildren().addAll(shadow,player, numberOfDeath,coinsCollected,backToMenu);

		Scene scene = new Scene(root,800,600);
		scene.getStylesheets().add("application.css");





		//set the player to listen to keyboard action also allow the player to interact with different objects
		try{
			PlayerMovement.setDefault(scene, player, blueWalls,greenAreas,Integer.parseInt(fileToRead.split("\\.")[0]),coins,totalNumberofCoins,yellowAreas);
		}catch(NumberFormatException e){
			PlayerMovement.setDefault(scene, player, blueWalls, greenAreas, -1, coins, totalNumberofCoins, yellowAreas);
		}
		return scene;
	}
}
