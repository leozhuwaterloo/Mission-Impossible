package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AreUSure {

	public static Scene getScene(){
		//root
		VBox root = new VBox();
		//labels informations
		Label label1 = new Label("You Will Lose Your");
		Label label2 = new Label("Progress.");
		Label label3 = new Label("Are You Sure?");
		
		//yes button
		Button yes = new Button("Yes");
		yes.setOnAction(e->{
			MainGameStart.changeSceneTo(Menu.getScene());
			MainGameStart.getWindow().show();
			Menu.closeNewWindow();
			
			BuildLevel.closeAreUSure();
			Customize.closeAreUSure();
		});
		//cancel button
		Button cancel = new Button("Cancel");
		cancel.setOnAction(e->{
			BuildLevel.closeAreUSure();
			Customize.closeAreUSure();
		});
		//add the stuff in
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(label1, label2, label3,yes,cancel);
		//return the scene
		Scene scene = new Scene(root,400,400,Color.TRANSPARENT);
		scene.getStylesheets().add("transparent.css");
		return scene;
	}
}
