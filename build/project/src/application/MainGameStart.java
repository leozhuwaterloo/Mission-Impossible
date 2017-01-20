package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MainGameStart extends Application {
	
	//run the javaFx application
	private static Stage window = new Stage();
	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		//Scene scene = RegLevelCompleted.getScene();
		//Scene scene = HighScore.getScene();
		//Scene scene = BuildLevel.getScene("10.txt");
		//Scene scene = Customize.getScene();
		Scene scene = Menu.getScene();
		//Scene scene = Setting.getScene();
		//Scene scene = Help.getScene();
		//Scene scene = Instructions.getScene();
		//Scene scene = CusLevelCompleted.getScene();
		//Scene scene = Save.getScene(); 			
		//Scene scene = SelectCusLevel.getScene(t);
		window.setScene(scene);
		window.sizeToScene();
		window.setResizable(false);
		window.getIcons().add(new Image("Icon.png"));
		window.setTitle("Mission Impossible - Leo Zhu");
		
		//window.initStyle(StageStyle.);
		window.show();
		window.setOnCloseRequest(e->{
			e.consume();
		});
	}

	public static void main(String[] args) {
		Music.playBackgroundMusic();
		launch(args);
	}
	
	public static Stage getWindow(){
		return window;
	}
	
	public static void changeSceneTo(Scene scene){
		window.setScene(scene);
	}
}
