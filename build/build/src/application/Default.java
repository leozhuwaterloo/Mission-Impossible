package application;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Default {

	//create the player and return it
	public static Rectangle createPlayer(int posX, int posY){
		Rectangle player = new Rectangle();
		player.setId("player");
		player.setWidth(23);
		player.setHeight(23);
		player.setX(posX);
		player.setY(posY);
		return player;
	}
	
	//create the red dots and return it
	public static Circle createDot(int posX, int posY){
		Circle dot = new Circle();
		dot.setId("redDot");
		dot.setLayoutX(posX);
		dot.setLayoutY(posY);
		dot.setRadius(9);
		return dot;
	}	
	
	//create the coins and return it
	public static Circle createCoin(int posX, int posY){
		Circle coin = new Circle();
		coin.setId("coin");
		coin.setLayoutX(posX);
		coin.setLayoutY(posY);
		coin.setRadius(10);
		return coin;
	}	
	
	//create the green areas and return it
	public static Rectangle createGreen(int posX, int posY, int width, int height){
		Rectangle greenArea = new Rectangle();
		greenArea.setId("greenArea");
		greenArea.setX(posX);
		greenArea.setY(posY);
		greenArea.setWidth(width);
		greenArea.setHeight(height);
		return greenArea;
	}
	
	
	//create the yellow area and return it
	public static Rectangle createYellow(int posX, int posY, int width, int height){
		Rectangle yellowArea = new Rectangle();
		yellowArea.setId("yellowArea");
		yellowArea.setX(posX);
		yellowArea.setY(posY);
		yellowArea.setWidth(width);
		yellowArea.setHeight(height);
		return yellowArea;
	}
	
	//create the wall and return it
	public static Rectangle createBlue(int posX, int posY, int width, int height){
		Rectangle blueWall = new Rectangle();
		blueWall.setId("blueWall");
		blueWall.setX(posX);
		blueWall.setY(posY);
		blueWall.setWidth(width);
		blueWall.setHeight(height);
		return blueWall;
	}
}
