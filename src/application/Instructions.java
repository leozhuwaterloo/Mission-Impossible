package application;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Instructions {

	public static Scene getScene(){
		//a basic instruction window that will show when the player clicked the instruction button
		VBox root = new VBox();

		ArrayList<Label> labels = new ArrayList<>();
		labels.add(createLabel("  Use the arrow keys to move the red square  "));
		labels.add(createLabel("  through the maze of dots to the end zone.  "));
		labels.add(createLabel("  You have to avoid the red dots  "));
		labels.add(createLabel("  while collecting all of the yellow coins.  "));
		labels.add(createLabel("  Good luck. You're going to need it!  "));
		labels.add(createLabel("  Tip: For most levels, there is an easy way  "));
		labels.add(createLabel("  and a hard way, think before you act!  "));
		

		Button back = new Button("Back");
		back.setId("menuButton");
		back.setOnAction(e->MainGameStart.changeSceneTo(Menu.getScene()));

		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);
		for(Label everyLabel:labels)
			root.getChildren().addAll(everyLabel);
		root.getChildren().add(back);
		Scene scene = new Scene(root,800,600);
		scene.getStylesheets().add("application.css");
		return scene;
	}

	private static Label createLabel(String text){
		Label label = new Label(text);
		label.setId("fancyLabel");
		return label;
	}
}
