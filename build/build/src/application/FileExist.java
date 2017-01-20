package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FileExist {

	public static Scene getScene(){
		
		//a basic window that will show if the level already exist
		VBox root = new VBox();
		
		Label label1 = new Label("Level Name");
		Label label2 = new Label("Already Exist.");
		Label label3 = new Label("Please Rename");
		Label label4 = new Label("The Level.");
		
		Button ok = new Button("OK");
		ok.setOnAction(e->{
			Save.closeFileExist();
		});
		
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(label1, label2,label3,label4,ok);

		Scene scene = new Scene(root,400,400,Color.rgb(230, 230, 255));
		scene.getStylesheets().add("transparent.css");
		return scene;
	}
}
