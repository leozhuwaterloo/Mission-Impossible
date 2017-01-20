package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ChooseLevel {

	public static Scene getScene(){
		VBox root = new VBox();
		//create buttons
		Button playRegular = new Button("Play Regular Levels");
		playRegular.setId("menuButton");
		playRegular.setOnAction(e->MainGameStart.changeSceneTo(BuildLevel.getScene("1.txt")));

		Button playCusLevel = new Button("Play Levels Made by Other Players");
		playCusLevel.setId("menuButton");
		playCusLevel.setOnAction(e-> MainGameStart.changeSceneTo(SelectCusLevel.getScene()));
		
		Button backButton = new Button("Back");
		backButton.setId("menuButton");
		backButton.setOnAction(e-> {
			MainGameStart.changeSceneTo(Menu.getScene());
		});
		//add the stuff and set the root
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(playRegular, playCusLevel,backButton);
		//return the scene
		Scene scene = new Scene(root,800,600);
		scene.getStylesheets().add("application.css");
		return scene;
	}
}
