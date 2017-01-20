package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CusLevelCompleted {

	private static Image yellowStarLeft = new Image("YellowStarLeft.png");
	private static Image yellowStarRight = new Image("YellowStarRight.png");
	private static Image emptyStarLeft = new Image("EmptyStarLeft.png");
	private static Image emptyStarRight = new Image("EmptyStarRight.png");
	private static int currentSelected = 1;

	public static Scene getScene(){
		//this will open up a rating system for the customized levels
		//Initialize the current selected star to 1;
		currentSelected = 1;
		VBox root = new VBox();
		//labels
		Label text1 = new Label("How Is The Level?");
		text1.setId("deathLabel");
		Label text2 = new Label("Rate It To Let Us Know.");
		text2.setId("deathLabel");
		
		//the rating starts
		HBox Stars = new HBox();
		//create the starts' images
		ArrayList<ImageView> allStars = new ArrayList<>();
		for(int i=1;i<=5;i++){
			ImageView starLeft = new ImageView(yellowStarLeft);
			ImageView starRight = new ImageView(yellowStarRight);
			starLeft.setFitWidth(46);
			starLeft.setFitHeight(92);
			starRight.setFitWidth(46);
			starRight.setFitHeight(92);
			allStars.add(starLeft);
			allStars.add(starRight);

		}
		//put the images into labels
		ArrayList<Label> starLabel = new ArrayList<>();
		for(ImageView everyStar: allStars){
			Label newLabel = new Label("", everyStar);
			int linkedRating = currentSelected;
			newLabel.setOnMouseClicked(e->{

				currentSelected=linkedRating;

				for(int i=0; i<=linkedRating-1;i++){
					if (allStars.get(i).getImage()==emptyStarLeft)
						allStars.get(i).setImage(yellowStarLeft);
					if (allStars.get(i).getImage()==emptyStarRight)
						allStars.get(i).setImage(yellowStarRight);
				}
				for(int i=9; i>linkedRating-1;i--){
					if (allStars.get(i).getImage()==yellowStarLeft)
						allStars.get(i).setImage(emptyStarLeft);
					if (allStars.get(i).getImage()==yellowStarRight)
						allStars.get(i).setImage(emptyStarRight);
				}
			});
			currentSelected++;
			//newButton.setBackground(Background.EMPTY);
			starLabel.add(newLabel);
		}
		//add all the labels
		Stars.getChildren().addAll(starLabel);
		Stars.setAlignment(Pos.CENTER);

		HBox buttons = new HBox();
		Button nextButton = new Button("Next");
		nextButton.setId("menuButton");
		//set the starts action when clicked
		nextButton.setOnAction(e-> {
			if(currentSelected==11){
				currentSelected = 10;
			}
			//System.out.print(currentSelected);
			updateTextFile();
			MainGameStart.changeSceneTo(Menu.getScene());
		});
		//buttons
		Button backButton = new Button("Back");
		backButton.setId("menuButton");
		backButton.setOnAction(e-> {
			MainGameStart.changeSceneTo(Menu.getScene());
		});
		buttons.getChildren().addAll(nextButton,backButton);
		buttons.setAlignment(Pos.CENTER);

		root.setSpacing(50);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(text1, text2,Stars,buttons);

		Scene scene = new Scene(root,800,600);
		scene.getStylesheets().add("application.css");
		return scene;
	}

	//update the textFile
	private static void updateTextFile(){
		Scanner input = ReadFile.get("CusLevels.txt");
		//create an ArrayList with the name of all the levels
		//associate the ArrayList of names to its properties with HashMaps
		ArrayList<String> namesList = new ArrayList<>();
		HashMap<String, Integer> timesPlayedMap = new HashMap<>();
		HashMap<String, Integer> ratingMap = new HashMap<>();
		HashMap<String, Integer> deathMap = new HashMap<>();
		while(input.hasNext()){
			String name = input.nextLine();
			int timesPlayed = Integer.parseInt(input.nextLine());
			int rating = Integer.parseInt(input.nextLine());
			int minDeath = Integer.parseInt(input.nextLine());
			namesList.add(name);
			timesPlayedMap.put(name, timesPlayed);
			ratingMap.put(name, rating);
			deathMap.put(name, minDeath);
		}
		input.close();
		timesPlayedMap.put(SelectCusLevel.getLevelSelected(), timesPlayedMap.get(SelectCusLevel.getLevelSelected())+1);
		ratingMap.put(SelectCusLevel.getLevelSelected(), ratingMap.get(SelectCusLevel.getLevelSelected())+currentSelected);
		if(deathMap.get(SelectCusLevel.getLevelSelected())>DotMovement.getNumberOfDeath())
			deathMap.put(SelectCusLevel.getLevelSelected(),DotMovement.getNumberOfDeath());
		String updatedInformation= "";
		for(String everyName: namesList){
			updatedInformation+=everyName+"\n";
			updatedInformation+=timesPlayedMap.get(everyName)+"\n";
			updatedInformation+=ratingMap.get(everyName)+"\n";
			updatedInformation+=deathMap.get(everyName)+"\n";
		}
		ReadFile.write("CusLevels.txt", updatedInformation);	
	}
}
