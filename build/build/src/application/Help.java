package application;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class Help {

	public static Scene getScene(){
		//the help window will display with text 
		VBox root = new VBox();
		
		Text t = new Text(50, 50, "This is a test");
		t.setWrappingWidth(350);
		t.setText("This is a drag and drop game level marker, which will give you the ability to create your own customized levels.\n"
				+ "When you click on the objects, you can set its correspoding properties to a different value.\n"
				+ "You can change the width and height of the colored rectangles.\n"
				+ "You can change the path of the dots, by setting the movement type, the movement in x and y axis, and the time.\n"
				+ "If you want to dot to move in a circle, you need to set the start point(1 for top, 2 for right, 3 for bottom, 4 for left and also the radius.\n"
				+ "*Any green area under the player's initial spawn point will be removed.");
		t.setFont(new Font("Agency FB", 24));
		
		Text t2 = new Text(50, 50, "This is a test");
		t2.setWrappingWidth(350);
		t2.setText("CLICK TO CONTINUE");
		t2.setFont(new Font("Agency FB", 24));
		t2.setTextAlignment(TextAlignment.CENTER);
		
		//a fade animation to the label to let it stand out
		FadeTransition fadeout = new FadeTransition(Duration.millis(1000),t2);
		fadeout.setFromValue(1.0);
		fadeout.setToValue(0.0);
		fadeout.setAutoReverse(true);
		fadeout.setCycleCount(FadeTransition.INDEFINITE);
		fadeout.play();
		
		//add the things to the scene
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(t,t2);
	
		//return the scene
		Scene scene = new Scene(root,400,500,Color.TRANSPARENT);
		scene.getStylesheets().add("transparent.css");
		return scene;
	}
}
