package application;

import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class SelectCusLevel {
	private static String levelSelected = "";
	
	public static String getLevelSelected(){
		return levelSelected;
	}
	
	@SuppressWarnings("unchecked")
	public static Scene getScene(){
		//create a pane
		Pane root = new Pane();
		//name column
		TableColumn<CusLevel, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(348);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		//times column
		TableColumn<CusLevel, Integer> timesColumn = new TableColumn<>("Played");
		timesColumn.setMinWidth(150);
		timesColumn.setCellValueFactory(new PropertyValueFactory<>("timesPlayed"));

		//rating column
		TableColumn<CusLevel, Double> ratingColumn = new TableColumn<>("Rating");
		ratingColumn.setMinWidth(150);
		ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
		
		//min death column
		TableColumn<CusLevel, Double> deathColumn = new TableColumn<>("Min Death");
		deathColumn.setMinWidth(150);
		deathColumn.setCellValueFactory(new PropertyValueFactory<>("minDeath"));

		//create a table with the table columns
		TableView<CusLevel> table = new TableView<>();
		table.setItems(getLevel());
		table.setId("cusTable");
		table.setMaxWidth(800);
		table.setMinWidth(800);
		table.setMaxHeight(500);
		table.setMinHeight(500);
		table.getColumns().addAll(nameColumn, timesColumn, ratingColumn,deathColumn);
		
		//create buttons
		Button playButton = new Button("Play");
		playButton.setId("menuButton");
		playButton.setOnAction(e-> {
			levelSelected = table.getSelectionModel().getSelectedItems().get(0).getName();
			MainGameStart.changeSceneTo(BuildLevel.getScene("CusLevels/"+levelSelected+".txt"));
		});
		playButton.setLayoutX(500);
		playButton.setLayoutY(500);
		
		Button backButton = new Button("Back");
		backButton.setId("menuButton");
		backButton.setOnAction(e-> {
			MainGameStart.changeSceneTo(ChooseLevel.getScene());
		});
		backButton.setLayoutX(630);
		backButton.setLayoutY(500);
		//create the scene and add all the stuff inside and then return the scene
		root.getChildren().addAll(table,playButton,backButton);
		Scene scene = new Scene(root,800,600);
		scene.getStylesheets().add("application.css");
		return scene;
	}

	//gather the data from the text file, and create a bunch of Customized levels with its attributes
	private static ObservableList<CusLevel> getLevel(){
		Scanner input = ReadFile.get("CusLevels.txt");
		ObservableList<CusLevel> levels = FXCollections.observableArrayList();
		while(input.hasNext()){
			String name = input.nextLine();
			int timesPlayed = Integer.parseInt(input.nextLine());
			double rating = Math.round(Integer.parseInt(input.nextLine())/(double)timesPlayed*100)/100.0;
			int minDeath = Integer.parseInt(input.nextLine());
			levels.add(new CusLevel(name, timesPlayed, rating, minDeath));
		}
		return levels;
	}
}
