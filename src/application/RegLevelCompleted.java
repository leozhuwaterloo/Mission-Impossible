package application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RegLevelCompleted {


	public static Scene getScene(){
		//when all the regular levels are completed, a window will pop up to allow the player to upload their score
		VBox root = new VBox();
		Label text1 = new Label("Congratulations, You Complete All Levels");
		text1.setId("deathLabel");
		Label text2 = new Label("What's Your Name?");
		text2.setId("titleLabel");
		//an entry
		TextField nameEntry = new TextField();
		nameEntry.setId("deathLabel");
		nameEntry.setAlignment(Pos.CENTER);
		
		//Buttons
		HBox buttons = new HBox();
		Button backButton = new Button("Back");
		backButton.setId("menuButton");
		backButton.setOnAction(e-> {
			MainGameStart.changeSceneTo(Menu.getScene());
		});

		Button continueButton = new Button("Continue");
		continueButton.setId("menuButton");
		continueButton.setOnAction(e-> {
			updateTextFile(nameEntry);
			MainGameStart.changeSceneTo(HighScore.getScene());
		});
		buttons.getChildren().addAll(backButton, continueButton);
		buttons.setSpacing(50);
		buttons.setAlignment(Pos.CENTER);

		root.setSpacing(50);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(text1, text2,nameEntry,buttons);
		//create the scene add the stuff inside and return it
		Scene scene = new Scene(root,800,600);
		scene.getStylesheets().add("application.css");
		return scene;
	}

	private static void updateTextFile(TextField nameEntry){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		ReadFile.writeAppend("PlayerInformation.txt", nameEntry.getText());
		ReadFile.writeAppend("PlayerInformation.txt", Integer.toString(DotMovement.getNumberOfDeath()));
		ReadFile.writeAppend("PlayerInformation.txt", dateFormat.format(date));
	}
}
