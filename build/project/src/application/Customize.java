package application;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Customize {
	/*
	 * The following will build the level builder which will have text file as an output, and thus created level can be read in later
	 * The data will be set with drag and drop action
	 */
	private static Stage areUSure;
	//initialize all the images needed
	private static Image playerImage = new Image("Player.png");
	private static Image yellowAreaImage = new Image("yellowArea.png");
	private static Image greenAreaImage = new Image("greenArea.png");
	private static Image blueWallImage = new Image("blueWall.png");
	private static Image redDotImage = new Image("redDot.png");
	private static Image coinImage = new Image("coin.png");

	//initialize the variable which will allow you to control the current dragging source
	private static ImageView currentSource;

	//initialize all the boxes, the board, the trashBox
	private static Pane board;
	private static HBox playerBox;
	private static HBox yellowAreaBox;
	private static HBox greenAreaBox;
	private static HBox blueWallBox;
	private static HBox redDotBox;
	private static HBox coinBox;
	private static HBox trashBox;

	//initialize all the things that can control the property of an object
	private static TextField width;

	private static TextField height;
	private static ChoiceBox<String> dotTypeChoice;
	private static TextField data1Entry;
	private static TextField data2Entry;
	private static TextField speedEntry;
	private static Button setWidthHeight;
	private static Button setDot;
	private static Button save;

	//initialize all the properties of associated objects' list
	private static String playerLocation = "0 0";
	private static ArrayList<ImageView> yellowAreas;
	private static ArrayList<ImageView> greenAreas;
	private static ArrayList<ImageView> blueWalls;
	private static ArrayList<ImageView> redDots;
	private static ArrayList<ImageView> coins;
	private static HashMap<ImageView,String> redDotProperties;

	//all the properties of all the objects will then be converted in to a string
	private static String content;
	private static Stage saveWindow;

	//A method that will get the string which should be written into the text file
	public static String getContent(){
		return content;
	}

	//A method that will close the saveWindow
	public static void closeSaveWindow(){
		saveWindow.close();
		saveWindow=null;
	}

	public static void closeAreUSure(){
		if(areUSure!=null){
			areUSure.close();
			areUSure=null;
		}
	}
	//A method that will allow the user to save after the user have complete the level at least once
	public static void setAbleToSave(){
		save.setDisable(false);
	}
	//A method that disable the save button
	public static void setUnableToSave(){
		save.setDisable(true);
	}
	//build the scene
	public static Scene getScene(){

		playerLocation = "0 0";
		yellowAreas = new ArrayList<>();
		greenAreas = new ArrayList<>();
		blueWalls = new ArrayList<>();
		redDots = new ArrayList<>();
		coins = new ArrayList<>();
		redDotProperties = new HashMap<>();


		//create the pane 
		Pane root = new Pane();

		//create a the board and add background to it
		board = new Pane();
		Image backGround = new Image("BG.png");
		final ImageView BG = new ImageView(backGround);
		board.getChildren().add(BG);
		setUpTarget(board);

		//create the bottom of the window, which will contain the items and the control panel
		HBox buttom = new HBox();
		buttom.setPrefWidth(800);
		buttom.setPrefHeight(250);
		buttom.setLayoutX(0);
		buttom.setLayoutY(600);
		buttom.setSpacing(10);
		buttom.setId("buttom");
		//create the player and put it inside a box at the bottom
		ImageView player = new ImageView(playerImage);
		setUpDrag(player);
		playerBox = createBox(80,80,player);

		//create the yellow area and put it inside a box at the bottom
		ImageView yellowArea = new ImageView(yellowAreaImage);
		yellowArea.setFitWidth(40);
		yellowArea.setFitHeight(40);
		setUpDrag(yellowArea);
		yellowAreaBox = createBox(80,80,yellowArea);

		//create the green area and put it inside a box at the bottom
		ImageView greenArea = new ImageView(greenAreaImage);
		greenArea.setFitWidth(40);
		greenArea.setFitHeight(40);
		setUpDrag(greenArea);
		greenAreaBox = createBox(80,80,greenArea);	

		//create the blue wall and put it inside a box at the bottom
		ImageView blueWall = new ImageView(blueWallImage);
		blueWall.setFitWidth(40);
		blueWall.setFitHeight(40);
		setUpDrag(blueWall);
		blueWallBox = createBox(80,80,blueWall);		
		//create the red dot and put it inside a box at the bottom
		ImageView redDot = new ImageView(redDotImage);
		setUpDrag(redDot);
		redDotBox = createBox(80,80,redDot);
		//create the coin and put it inside a box at the bottom
		ImageView coin = new ImageView(coinImage);
		setUpDrag(coin);
		coinBox = createBox(80,80,coin);
		//create a VBox of to organize all the items
		//put all the boxes nicely inside 2 larger horizontal boxes
		//and then put the 2 horizontal boxes inside 1 vertical box
		VBox items = new VBox();
		items.setSpacing(5);
		items.setAlignment(Pos.CENTER);
		items.setId("buttom");
		HBox itemsRow1 = new HBox();
		itemsRow1.setSpacing(5);
		HBox itemsRow2 = new HBox();
		itemsRow2.setSpacing(5);
		itemsRow1.getChildren().addAll(playerBox,yellowAreaBox,greenAreaBox,blueWallBox);
		itemsRow2.getChildren().addAll(redDotBox,coinBox);
		items.getChildren().addAll(itemsRow1,itemsRow2);

		//create the control panel of the areas where the user get the control the width and height of an area
		VBox controls = new VBox();
		controls.setId("buttom");
		controls.setAlignment(Pos.CENTER);
		controls.setPrefWidth(130);
		controls.setSpacing(10);
		Label widthLabel = new Label("Width\n(# of blocks):");
		width = new TextField("1");
		width.setDisable(true);
		Label heightLabel = new Label("Height\n(# of blocks):");
		height = new TextField("1");
		height.setDisable(true);
		setWidthHeight = new Button("Set");
		setWidthHeight.setDisable(true);
		setWidthHeight.setOnAction(e->changeProperty(width,height));
		controls.getChildren().addAll(widthLabel,width,heightLabel,height,setWidthHeight);

		//create the control panel for dots where the user get the control the movement type of a dot
		VBox dotControl = new VBox();
		dotControl.setId("buttom");
		dotControl.setAlignment(Pos.CENTER);
		dotControl.setPrefWidth(160);
		dotControl.setSpacing(1);
		Label dotType = new Label("Type of Dot:");
		dotTypeChoice = new ChoiceBox<>();
		dotTypeChoice.getItems().add("Stay Still");
		dotTypeChoice.getItems().add("Bounce");
		dotTypeChoice.getItems().add("Go In Rectangle");
		dotTypeChoice.getItems().add("Go In Circle");
		dotTypeChoice.setValue("Stay Still");
		dotTypeChoice.setDisable(true);
		setDot = new Button("Set Dot");
		setDot.setDisable(true);
		Label data1 = new Label("X/Radius:");
		data1Entry = new TextField("0");
		data1Entry.setDisable(true);
		Label data2 = new Label("Y/Start:");
		data2Entry = new TextField("0");
		data2Entry.setDisable(true);
		Label speed = new Label("Done in: (ms)");
		speedEntry = new TextField("1000");
		speedEntry.setDisable(true);

		setDot.setOnAction(e->changeDotProperty(dotTypeChoice.getValue(),data1Entry.getText(),data2Entry.getText(),speedEntry.getText()));
		dotControl.getChildren().addAll(dotType,dotTypeChoice,data1,data1Entry,data2,data2Entry,speed,speedEntry,setDot);


		//create the last box, which contains the trash box, the test button, the back button, and the save button
		VBox other = new VBox();
		other.setId("buttom");
		other.setAlignment(Pos.CENTER);
		other.setSpacing(10);
		Button test = new Button("Test");
		test.setOnAction(e->test());
		Button back = new Button("Back");
		back.setOnAction(e->goBack());
		save = new Button("Save");
		save.setDisable(true);
		save.setOnAction(e->save());
		ImageView trash = new ImageView(new Image("trashCan.png"));
		trash.setFitWidth(50);
		trash.setFitHeight(60);
		trashBox = createBox(80,80,trash);	
		setUpTargetTrash(trashBox);
		other.getChildren().addAll(trashBox,test, back, save);


		//add all the things to the bottom box
		buttom.getChildren().addAll(items,controls,dotControl,other);
		//add the board and the bottom box to the overall scene
		root.getChildren().addAll(board,buttom);

		//set the properties of the scene
		Scene scene = new Scene(root,800,850);
		scene.getStylesheets().add("application.css");

		setOnlyNum(width);
		setOnlyNum(height);
		setOnlyNum(data1Entry);
		setOnlyNum(data2Entry);
		setOnlyNum(speedEntry);


		return scene;
	}

	private static void setOnlyNum(TextField entry){
		entry.textProperty().addListener((v, oldValue, newValue)->{
			try{
				Integer.parseInt(newValue);
			}catch(Exception e){
				entry.setText(oldValue);
			}
		});
	}



	//the method to save the current created level, which will execute when the save button is pressed
	private static void save(){
		if (saveWindow==null){	
			saveWindow = new Stage();
			saveWindow.sizeToScene();
			saveWindow.setResizable(false);
			saveWindow.getIcons().add(new Image("Icon.png"));
			saveWindow.setTitle("Mission Impossible - Leo Zhu");
			saveWindow.setScene(Save.getScene());
			saveWindow.show();
			saveWindow.setOnCloseRequest(e1->e1.consume());
			Menu.getNewWindow().hide();
		}
	}

	//the method to change the width and the height of an area, which will execute when the set button is pressed
	private static void changeProperty(TextField width, TextField height) {
		save.setDisable(true);
		if(currentSource!=null){
			if(currentSource.getImage()==yellowAreaImage||currentSource.getImage()==greenAreaImage||currentSource.getImage()==blueWallImage){
				int w = Integer.parseInt(width.getText())*40;
				int h = Integer.parseInt(height.getText())*40;
				currentSource.setFitWidth(w);
				currentSource.setFitHeight(h);
			}
		}
	}

	//the method to change the property of the current dot, which will execute when the setDot button is pressed
	private static void changeDotProperty(String type, String data1, String data2, String speed) {
		String path="";
		String property1="";
		String property2="";
		save.setDisable(true);
		if(currentSource!=null){
			if(currentSource.getImage()==redDotImage){
				if(type.equals("Stay Still")){
					path="0";
					property1 = "0";
					property2 = "0";
				}else if(type.equals("Bounce")){
					path="1";
					property1 = Integer.toString((int)(Integer.parseInt(data1)+currentSource.getX()+40));
					property2 = Integer.toString((int)(Integer.parseInt(data2)+currentSource.getY()+40));
				}else if(type.equals("Go In Rectangle")){
					path="2";
					property1 = Integer.toString((int)(Integer.parseInt(data1)+currentSource.getX()+40));
					property2 = Integer.toString((int)(Integer.parseInt(data2)+currentSource.getY()+40));
				}else if(type.equals("Go In Circle")){
					path="3";
					property1 = data1;
					property2 = data2;
				}


				String finalProperty=path+" "+property1+" "+property2+" "+speed;
				redDotProperties.put(currentSource, finalProperty);
			}
		}
	}

	//the method the make the objects able to be dragged
	private static void setUpDrag(ImageView source){
		source.setOnDragDetected(e->{
			Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
			ClipboardContent content = new ClipboardContent();
			content.putImage(source.getImage());
			db.setContent(content);
			currentSource = source;
			e.consume();
		});

		source.setOnMouseClicked(e->{
			if(board.getChildren().contains(source)){
				currentSource = source;
				updateProperties();
				e.consume();
			}
		});
	}
	//the method to set the event happens when the dragged object is dropped at the trash box
	private static void setUpTargetTrash(Pane target){
		target.setOnDragOver(e->{
			if(currentSource.getImage()!=null){
				e.acceptTransferModes(TransferMode.MOVE);
			}
			e.consume();
		});


		target.setOnDragDropped(e->{
			save.setDisable(true);
			if (currentSource.getImage()==playerImage){
				board.getChildren().remove(currentSource);
				playerLocation = "0 0";
				ImageView newObject = new ImageView(currentSource.getImage());
				setUpDrag(newObject);
				if (playerBox.getChildren().isEmpty())
					playerBox.getChildren().add(newObject);

			}else{
				if(yellowAreas.contains(currentSource)){
					yellowAreas.remove(currentSource);
					board.getChildren().remove(currentSource);
				}else if(greenAreas.contains(currentSource)){
					greenAreas.remove(currentSource);
					board.getChildren().remove(currentSource);
				}else if(blueWalls.contains(currentSource)){
					blueWalls.remove(currentSource);
					board.getChildren().remove(currentSource);
				}else if(redDots.contains(currentSource)){
					redDots.remove(currentSource);
					redDotProperties.remove(currentSource);
					board.getChildren().remove(currentSource);
				}else if(coins.contains(currentSource)){
					coins.remove(currentSource);
					board.getChildren().remove(currentSource);
				}
			}
			e.consume();
		});
	}

	//the method to set the event happens when the dragged object is dropped at the board
	private static void setUpTarget(Pane target){
		target.setOnDragOver(e->{
			if(currentSource.getImage()!=null){
				e.acceptTransferModes(TransferMode.MOVE);
			}
			e.consume();
		});


		target.setOnDragDropped(e->{
			int currentX = (int) e.getX();
			int currentY = (int) e.getY();
			if(currentSource.getImage()==playerImage){
				target.getChildren().remove(currentSource);
				currentSource.setX((int)Math.round((double)currentX/20)*20-40);
				currentSource.setY((int)Math.round((double)currentY/20)*20-40);
				target.getChildren().add(currentSource);
				playerLocation = Integer.toString((int)Math.round((double)currentX/20)*20-11)+" " + Integer.toString((int)Math.round((double)currentY/20)*20-11);
			}else if(currentSource.getImage()==yellowAreaImage){	
				DragDroppedAction(target,(int)((double)currentX/40)*40-20, (int)((double)currentY/40)*40-20, yellowAreas, yellowAreaBox);
			}else if(currentSource.getImage()==greenAreaImage){	
				DragDroppedAction(target,(int)((double)currentX/40)*40-20, (int)((double)currentY/40)*40-20, greenAreas, greenAreaBox);
			}else if(currentSource.getImage()==blueWallImage){	
				DragDroppedAction(target,(int)((double)currentX/40)*40-20, (int)((double)currentY/40)*40-20, blueWalls, blueWallBox);
			}else if(currentSource.getImage()==redDotImage){
				DragDroppedAction(target,(int)Math.round((double)currentX/20)*20-40, (int)Math.round((double)currentY/20)*20-40, redDots, redDotBox);
				redDotProperties.put(currentSource, "0 "+ (int)(currentSource.getX()+40)+ " " + (int)(currentSource.getY()+40) +" 0");
			}else if(currentSource.getImage()==coinImage){
				DragDroppedAction(target,(int)Math.round((double)currentX/20)*20-40, (int)Math.round((double)currentY/20)*20-40, coins, coinBox);
			}
			save.setDisable(true);
			updateProperties();
			e.consume();
		});
	}

	//the method that will execute whenever the an object is changed, so that for example, it will not allow the user to change the width and the height of the player
	private static void updateProperties(){
		if(currentSource.getImage()==yellowAreaImage||currentSource.getImage()==greenAreaImage||currentSource.getImage()==blueWallImage){
			width.setDisable(false);
			height.setDisable(false);
			setWidthHeight.setDisable(false);
			width.setText(Integer.toString((int)currentSource.getFitWidth()/40));
			height.setText(Integer.toString((int)currentSource.getFitHeight()/40));
			dotTypeChoice.setDisable(true);
			data1Entry.setDisable(true);
			data2Entry.setDisable(true);
			speedEntry.setDisable(true);
			setDot.setDisable(true);
		}else if (currentSource.getImage()==playerImage||currentSource.getImage()==coinImage){
			width.setDisable(true);
			height.setDisable(true);
			setWidthHeight.setDisable(true);
			dotTypeChoice.setDisable(true);
			data1Entry.setDisable(true);
			data2Entry.setDisable(true);
			speedEntry.setDisable(true);
			setDot.setDisable(true);
		}else if (currentSource.getImage()==redDotImage){
			width.setDisable(true);
			height.setDisable(true);
			setWidthHeight.setDisable(true);
			dotTypeChoice.setDisable(false);
			data1Entry.setDisable(false);
			data2Entry.setDisable(false);
			speedEntry.setDisable(false);
			setDot.setDisable(false);

			if (redDotProperties.containsKey(currentSource)){
				String[] currentDotProperty = redDotProperties.get(currentSource).split(" ");
				dotTypeChoice.setValue(dotTypeChoice.getItems().get(Integer.parseInt(currentDotProperty[0])));;
				if(Integer.parseInt(currentDotProperty[0])==1||Integer.parseInt(currentDotProperty[0])==2){
					data1Entry.setText(Integer.toString(Integer.parseInt(currentDotProperty[1])-(int)currentSource.getX()-40));
					data2Entry.setText(Integer.toString(Integer.parseInt(currentDotProperty[2])-(int)currentSource.getY()-40));
					speedEntry.setText(currentDotProperty[3]);
				}else if(Integer.parseInt(currentDotProperty[0])==3){
					data1Entry.setText(currentDotProperty[1]);
					data2Entry.setText(currentDotProperty[2]);
					speedEntry.setText(currentDotProperty[3]);
				}else if(Integer.parseInt(currentDotProperty[0])==0){
					data1Entry.setText("0");
					data2Entry.setText("0");
					speedEntry.setText("0");
				}
			}else{
				dotTypeChoice.setDisable(true);
				data1Entry.setDisable(true);
				data2Entry.setDisable(true);
				speedEntry.setDisable(true);
				setDot.setDisable(true);
			}
		}
	}
	//this method will execute whenever an object is dropped inside the board
	//based on the current dragging object, it will sort the current object into its corresponding ArrayList
	private static void DragDroppedAction(Pane target, int currentX, int currentY, ArrayList<ImageView> targetList, HBox targetBox){
		target.getChildren().remove(currentSource);
		currentSource.setX(currentX);
		currentSource.setY(currentY);
		target.getChildren().add(currentSource);
		if(!targetList.contains(currentSource))
			targetList.add(currentSource);
		if (targetBox.getChildren().isEmpty()){
			ImageView newObject = new ImageView(currentSource.getImage());
			if(targetBox!=redDotBox&&targetBox!=coinBox){
				newObject.setFitWidth(40);
				newObject.setFitHeight(40);
			}
			setUpDrag(newObject);
			targetBox.getChildren().add(newObject);
		}

	}

	//A method that will create a box with only one image inside
	private static HBox createBox(int width, int height, ImageView putIn){
		HBox box  = new HBox();
		box.setId("buttom");
		box.setMinWidth(width);
		box.setMaxWidth(width);
		box.setMinHeight(height);
		box.setMaxHeight(height);
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(putIn);
		return box;
	}

	//A method that will allow the user to go back to main menu
	private static void goBack(){
		if(areUSure==null){
			areUSure = new Stage();
			areUSure.sizeToScene();
			areUSure.setResizable(false);
			areUSure.setScene(AreUSure.getScene());
			areUSure.getIcons().add(new Image("Icon.png"));
			areUSure.setTitle("Mission Impossible - Leo Zhu");
			areUSure.initStyle(StageStyle.TRANSPARENT);
			areUSure.initModality(Modality.APPLICATION_MODAL);
			areUSure.show();
			areUSure.setOnCloseRequest(e1->e1.consume());	
			//Main.changeSceneTo(Menu.getScene());
		}
	}

	//A method that will temporarily save the level, generate the level, and let the user test it
	private static void test(){
		DotMovement.resetNumberOfDeath();
		content = "PLAYER\n" 
				+playerLocation+"\n"
				+"DOTS\n"
				+turnDotsIntoString(redDots, redDotProperties)
				+"YELLOW\n"
				+turnAreasIntoString(yellowAreas)
				+"GREEN\n"
				+turnAreasIntoString(greenAreas)
				+"BLUE\n"
				+turnAreasIntoString(blueWalls)
				+"COINS\n"
				+coins.size()+"\n"
				+turnCoinsIntoString(coins)
				+"END\n";
		ReadFile.write("0.txt", content);
		MainGameStart.getWindow().show();
		MainGameStart.changeSceneTo(BuildLevel.getScene("0.txt"));
		Menu.getNewWindow().hide();
	}

	//a method that will turn the ArrayList of the areas into a String that will be written into a text file and can be read later to generate the level
	private static String turnAreasIntoString(ArrayList<ImageView> list){
		String location = "";
		for(ImageView everyObject: list){
			location+=(int)(everyObject.getX()+20)+" "+(int)(everyObject.getY()+20)+" "+(int)(everyObject.getFitWidth())+" "+(int)(everyObject.getFitHeight())+"\n";
		}
		return location;
	}
	//a method that will turn the ArrayList of the coins into a String that will be written into a text file and can be read later to generate the level
	private static String turnCoinsIntoString(ArrayList<ImageView> coinsList){
		String location = "";
		for(ImageView everyCoin: coinsList){
			location+=(int)(everyCoin.getX()+40)+" "+(int)(everyCoin.getY()+40)+"\n";
		}
		return location;
	}

	//a method that will turn the ArrayList of the red dots, and the HashMap of the red dots' properties into a String that will be written into a text file and can be read later to generate the level
	private static String turnDotsIntoString(ArrayList<ImageView> dotsList, HashMap<ImageView, String> properties){
		String location = "";
		for(ImageView everyDot: dotsList){
			location+=(int)(everyDot.getX()+40)+" "+(int)(everyDot.getY()+40)+" "+properties.get(everyDot) +"\n";
		}
		return location;
	}
}
