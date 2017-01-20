package application;

import java.io.File;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Menu {
	private static Stage newWindow;

	public static Stage getNewWindow(){
		return newWindow;
	}

	public static void closeNewWindow(){
		if(newWindow!=null){
			newWindow.close();
			newWindow=null;
		}
	}

	public static Scene getScene(){
		DotMovement.resetNumberOfDeath();
		Pane root = new Pane();
		//Labels
		Label title = new Label("Mission Impossible");
		title.setId("titleLabel");
		title.setLayoutX(200);
		title.setLayoutY(50);
		//Buttons
		Button playButton = createMenuButton("Play Game",60,180);
		playButton.setOnAction(e->MainGameStart.changeSceneTo(ChooseLevel.getScene()));
		Button instructionButton = createMenuButton("Instructions",60,270);
		instructionButton.setOnAction(e->MainGameStart.changeSceneTo(Instructions.getScene()));
		Button settingButton = createMenuButton("Setting",60,360);
		settingButton.setOnAction(e->MainGameStart.changeSceneTo(Setting.getScene()));
		Button exitButton = createMenuButton("Exit",60,450);
		exitButton.setOnAction(e->System.exit(0));
		//play the video
		Media media = new Media(new File("LevelsIntro.mp4").toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		MediaView mediaView = new MediaView(mediaPlayer);
		mediaPlayer.play();
		mediaPlayer.setMute(true);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		//ImageView mediaView = new ImageView(backgroundImage);
		mediaView.setLayoutX(360);
		mediaView.setLayoutY(230);
		mediaView.setFitWidth(364);
		mediaView.setFitHeight(273);

		Rectangle border = new Rectangle();
		border.setLayoutX(350);
		border.setLayoutY(220);
		border.setWidth(384);
		border.setHeight(293);
		
		//create the image and let is fade and change scale
		Image customizeText = new Image("Customize.png");
		ImageView customizeLabel = new ImageView(customizeText);
		Button customizeButton = new Button("",customizeLabel);
		customizeButton.setLayoutX(530);
		customizeButton.setLayoutY(130);
		customizeButton.setBackground(Background.EMPTY);

		FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), customizeLabel);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0.3);
		fadeTransition.setCycleCount(FadeTransition.INDEFINITE);
		fadeTransition.setAutoReverse(true);
		fadeTransition.play();
		ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(1000), customizeLabel);
		scaleTransition.setToX(0.8);
		scaleTransition.setToY(0.8);
		scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
		scaleTransition.setAutoReverse(true);
		scaleTransition.play();


		customizeButton.setOnAction(e->{
			newWindow = new Stage();
			newWindow.sizeToScene();
			newWindow.setResizable(false);
			newWindow.getIcons().add(new Image("Icon.png"));
			newWindow.setTitle("Mission Impossible - Leo Zhu");
			newWindow.setScene(Customize.getScene());
			newWindow.show();
			newWindow.setOnCloseRequest(e1->e1.consume());
			MainGameStart.getWindow().hide();
			
			Stage newStage = new Stage();
			newStage.sizeToScene();
			newStage.setResizable(false);
			newStage.setScene(Help.getScene());
			newStage.getIcons().add(new Image("Icon.png"));
			newStage.setTitle("Mission Impossible - Leo Zhu");
			newStage.initStyle(StageStyle.TRANSPARENT);
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.show();
			newStage.setOnCloseRequest(e1->e1.consume());
			
			newStage.getScene().setOnMouseClicked(e2->newStage.close());
		});

		customizeButton.setOnMouseEntered(e->{
			fadeTransition.stop();
			scaleTransition.stop();
			customizeLabel.setScaleX(1);
			customizeLabel.setScaleY(1);
			customizeLabel.setOpacity(1);
		});

		customizeButton.setOnMouseExited(e->{
			fadeTransition.play();
			scaleTransition.play();
		});

		//create 2 animated Rectangle to make the main menu look nicer
		Rectangle player1 = createMovingPlayer(40,160,280,400);
		Rectangle player2 = createMovingPlayer(320,560,-280,-400);
		
		//add all the things to the scene
		root.getChildren().addAll(title,playButton, instructionButton,settingButton,exitButton,border,mediaView,customizeButton, player1,player2);
		Scene scene = new Scene(root,800,600);
		scene.getStylesheets().add("application.css");
		return scene;
	}

	//create a typical button
	private static Button createMenuButton(String text, int posX, int posY){
		Button newButton = new Button(text);
		newButton.setLayoutX(posX);
		newButton.setLayoutY(posY);
		newButton.setId("menuButton");
		return newButton;
	}

	//create a animated moving rectangle to make the main menu look nicer
	private static Rectangle createMovingPlayer(int initX, int initY, int width, int height){
		Rectangle player = Default.createPlayer(initX, initY);
		PathTransition followPath = new PathTransition();
		Path path = new Path();
		path.getElements().add(new MoveTo(initX,initY));
		path.getElements().add(new LineTo(width+initX,initY));
		path.getElements().add(new LineTo(width+initX,height+initY));
		path.getElements().add(new LineTo(initX,height+initY));
		path.getElements().add(new LineTo(initX,initY));
		followPath.setDuration(Duration.millis(6500));
		followPath.setPath(path);
		followPath.setNode(player);
		followPath.setInterpolator(Interpolator.LINEAR);
		followPath.setCycleCount(Timeline.INDEFINITE);
		followPath.play();
		return player;
	}

}
