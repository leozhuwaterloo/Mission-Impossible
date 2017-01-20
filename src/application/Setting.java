package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Setting {

	public static Scene getScene(){
		VBox root = new VBox();

		//title
		Label title = new Label("Setting");
		title.setId("titleLabel");

		HBox controls = new HBox();

		//a bunch of labels on the left side indicating what the control is for 
		VBox labels = new VBox();
		Label BGMusic = createLabel("BackGround Music");
		Label deathMusic = createLabel("Death Sound Effect");
		Label coinMusic = createLabel("Coin Sound Effect");
		Label passMusic = createLabel("Level Complete Sound Effect");
		labels.setSpacing(20);
		labels.getChildren().addAll(BGMusic, deathMusic,coinMusic,passMusic);

		//a bunch of combo box on the right hand side, allowing the user to control the sound
		VBox ons = new VBox();
		ComboBox<String> onBGMusic = createComboBox();
		if(Music.getBackgroundMusic().isMute())
			onBGMusic.setValue("Off");
		else
			onBGMusic.setValue("On");
		ComboBox<String> onDeathMusic= createComboBox();
		onDeathMusic.setValue(changeBooleanToString(Music.getDeathMusic()));
		ComboBox<String> onCoinMusic = createComboBox();
		onCoinMusic.setValue(changeBooleanToString(Music.getCoinMusic()));
		ComboBox<String> onPassMusic = createComboBox();
		onPassMusic.setValue(changeBooleanToString(Music.getPassMusic()));
		ons.setSpacing(20);
		ons.getChildren().addAll(onBGMusic, onDeathMusic,onCoinMusic,onPassMusic);

		controls.setAlignment(Pos.CENTER);
		controls.setSpacing(50);
		controls.getChildren().addAll(labels,ons);

		//save the attribute that the player controls
		HBox buttons = new HBox();
		Button save = new Button("Save");
		save.setId("menuButton");
		save.setOnAction(e->{
			if(onBGMusic.getValue().equals("On"))
				Music.getBackgroundMusic().setMute(false);
			else if(onBGMusic.getValue().equals("Off"))
				Music.getBackgroundMusic().setMute(true);

			if(onDeathMusic.getValue().equals("On"))
				Music.setDeathMusic(true);
			else if(onDeathMusic.getValue().equals("Off"))
				Music.setDeathMusic(false);

			if(onCoinMusic.getValue().equals("On"))
				Music.setCoinMusic(true);
			else if(onCoinMusic.getValue().equals("Off"))
				Music.setCoinMusic(false);

			if(onPassMusic.getValue().equals("On"))
				Music.setPassMusic(true);
			else if(onPassMusic.getValue().equals("Off"))
				Music.setPassMusic(false);
			MainGameStart.changeSceneTo(Menu.getScene());
		});
		Button back = new Button("Back");
		back.setId("menuButton");
		back.setOnAction(e->MainGameStart.changeSceneTo(Menu.getScene()));
		buttons.setSpacing(50);
		buttons.setPrefHeight(150);
		buttons.setAlignment(Pos.CENTER);
		buttons.getChildren().addAll(save,back);

		//create the scene and return it
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(title,controls,buttons);

		Scene scene = new Scene(root,800,600);
		scene.getStylesheets().add("application.css");
		return scene;
	}
	//create the typical nice label
	private static Label createLabel(String text){
		Label newLabel = new Label(text);
		newLabel.setId("deathLabel");
		newLabel.setMinHeight(80);
		return newLabel;
	}
	//create the typical nice combo box
	private static ComboBox<String> createComboBox(){
		ComboBox<String> newComboBox = new ComboBox<>();
		newComboBox.getItems().addAll("On","Off");
		newComboBox.setMinHeight(80);
		newComboBox.setMinWidth(170);
		return newComboBox;
	}
	//change boolean variable to a String that will says "On" or "Off"
	private static String changeBooleanToString(boolean current){
		if(current)
			return "On";
		else
			return "Off";
	}
}
