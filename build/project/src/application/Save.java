package application;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Save {


	private static Stage fileExist;


	//show this window when the player want to save their levels
	public static void closeFileExist(){
		if(fileExist!=null){
			fileExist.close();
			fileExist=null;
		}
	}

	public static Scene getScene(){
		//some basic buttons and labels are added to the window
		VBox root = new VBox();

		Label enterName = new Label("What Is The Name Of Your Level?");
		enterName.setId("deathLabel");

		TextField nameEntry = new TextField();
		nameEntry.setId("deathLabel");
		nameEntry.setAlignment(Pos.CENTER);

		Button saveToFile = new Button("Save");
		saveToFile.setId("menuButton");
		saveToFile.setOnAction(e->saveFile(nameEntry));

		Button cancle = new Button("Cancle");
		cancle.setId("menuButton");
		cancle.setOnAction(e-> cancleGoBack());
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(enterName, nameEntry, saveToFile,cancle);
		//create the scene, add the stuff inside and then return it
		Scene scene = new Scene(root,800,600);
		scene.getStylesheets().add("application.css");
		return scene;
	}

	private static void cancleGoBack(){
		Menu.getNewWindow().show();
		Customize.closeSaveWindow();
	}

	//write to a file and check if the file already exist or not
	private static void saveFile(TextField nameEntry){
		File f = new File("CusLevels/"+nameEntry.getText()+".txt");
		if(f.exists() && !f.isDirectory()) { 
			if(fileExist==null){
				fileExist = new Stage();
				fileExist.sizeToScene();
				fileExist.setResizable(false);
				fileExist.setScene(FileExist.getScene());
				fileExist.getIcons().add(new Image("Icon.png"));
				fileExist.setTitle("Mission Impossible - Leo Zhu");
				fileExist.initStyle(StageStyle.TRANSPARENT);
				fileExist.initModality(Modality.APPLICATION_MODAL);
				fileExist.show();
				fileExist.setOnCloseRequest(e1->e1.consume());
			}
		}else{
			ReadFile.write("CusLevels/"+nameEntry.getText()+".txt", Customize.getContent());
			String data = nameEntry.getText() +"\n" +"0\n0\n999";
			ReadFile.writeAppend("CusLevels.txt", data);
			Customize.closeSaveWindow();
			Menu.getNewWindow().show();
		}
	}
}
