package application;

import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class HighScore {
	
	@SuppressWarnings("unchecked")
	public static Scene getScene(){
		VBox root = new VBox();
		
		Label title = new Label("More Level Coming Soon!");
		title.setId("titleLabel");
		
		//create the columns of my table
		TableColumn<PlayerInformation, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(398);
		nameColumn.setMaxWidth(398);
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<PlayerInformation, Integer> deathColumn = new TableColumn<>("Deaths");
		deathColumn.setMinWidth(200);
		deathColumn.setMaxWidth(200);
		
		deathColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfDeath"));
		
		TableColumn<PlayerInformation, String> dateColumn = new TableColumn<>("Date");
		dateColumn.setMinWidth(200);
		dateColumn.setMaxWidth(200);
		
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

		//create a table
		TableView<PlayerInformation> table = new TableView<>();
		table.setItems(getPeople());
		table.setId("cusTable");
		table.setMaxWidth(800);
		table.setMinWidth(800);
		table.setMaxHeight(500);
		table.setMinHeight(500);
		table.getColumns().addAll(nameColumn, deathColumn, dateColumn);
		
		//create the buttons
		Button back = new Button("Back");
		back.setId("menuButton");
		back.setOnAction(e->MainGameStart.changeSceneTo(Menu.getScene()));
		
		//add the stuff to the root
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);

		
		root.getChildren().addAll(table,back);
		Scene scene = new Scene(root,800,600);
		scene.getStylesheets().add("application.css");
		return scene;
	}
	
	//generated a list of all the play's data in the txt file
	private static ObservableList<PlayerInformation> getPeople(){
		Scanner input = ReadFile.get("PlayerInformation.txt");
		ObservableList<PlayerInformation> players = FXCollections.observableArrayList();
		while(input.hasNext()){
			String name = input.nextLine();
			int numberOfDeath = Integer.parseInt(input.nextLine());
			String date = input.nextLine();
			players.add(new PlayerInformation(name,numberOfDeath, date));
		}
		return players;
	}
}
