package application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class PlayerMovement {
	private static int speed = 3;
	private static boolean upPressed = false;
	private static boolean downPressed = false;
	private static boolean leftPressed = false;
	private static boolean rightPressed = false;

	private static boolean dead = false;
	private static int coinCollected = 0;

	private static AnimationTimer rectangleAnimation;
	public static int getCoinCollected(){
		return coinCollected;
	}

	public static void resetCoinCollected(){
		coinCollected = 0;
		upPressed = false;
		downPressed = false;
		leftPressed = false;
		rightPressed = false;
	}
	//set the default movement of the player with the coordinates of the other objects
	public static void setDefault(Scene scene,Rectangle player, ArrayList<Rectangle> blueWalls,ArrayList<Rectangle> greenAreas, int currentLevel, ArrayList<Circle> coins, int totalNumerOfCoins,ArrayList<Rectangle> yellowAreas){

		//boolean variable to test, if the key is on hold
		scene.setOnKeyPressed(e->{
			if(e.getCode()==KeyCode.UP){
				upPressed = true;
			}else if(e.getCode()==KeyCode.DOWN){
				downPressed = true;
			}else if(e.getCode()==KeyCode.LEFT){
				leftPressed = true;
			}else if(e.getCode()==KeyCode.RIGHT){
				rightPressed = true;
			}
		});


		scene.setOnKeyReleased(e->{
			if(e.getCode()==KeyCode.UP)
				upPressed = false;
			else if(e.getCode()==KeyCode.DOWN)
				downPressed = false;
			else if(e.getCode()==KeyCode.LEFT)
				leftPressed = false;
			else if(e.getCode()==KeyCode.RIGHT)
				rightPressed = false;

		});

		//a timer that will run it self to check the collision detection of the player to the other objects
		if (rectangleAnimation!=null)
			rectangleAnimation.stop();
		rectangleAnimation = new AnimationTimer() {

			public void handle(long now) {


				if(!dead){
					if(upPressed){
						player.setY(player.getY()-speed);
						while(checkCollision(blueWalls,player)){
							player.setY(player.getY()+1);
						}
					}if(downPressed){
						player.setY(player.getY()+speed);
						while(checkCollision(blueWalls,player)){
							player.setY(player.getY()-1);
						}
					}if(leftPressed){
						player.setX(player.getX()-speed);
						while(checkCollision(blueWalls,player)){
							player.setX(player.getX()+1);
						}
					}if(rightPressed){
						player.setX(player.getX()+speed);
						while(checkCollision(blueWalls,player)){
							player.setX(player.getX()-1);
						}
					}
				}
				//collision detection between the player and the green areas
				if(checkCollision(greenAreas,player)){

					if(coinCollected == totalNumerOfCoins){
						player.setX(0);
						player.setY(0);
						coinCollected=0;
						Music.playPassSound();
						if(currentLevel>0){
							try{
								MainGameStart.changeSceneTo(BuildLevel.getScene(currentLevel+1+".txt"));
							}catch(Exception e){
								//System.out.println("Level Complete");
								MainGameStart.changeSceneTo(RegLevelCompleted.getScene());
							}
						}
						else if (currentLevel==0){
							Customize.setAbleToSave();
							MainGameStart.getWindow().hide();
							Menu.getNewWindow().show();
						}else if (currentLevel==-1){
							MainGameStart.changeSceneTo(CusLevelCompleted.getScene());
						}
					}

				}
				//collision detection between the player and the yellow areas
				for(Rectangle everyYellowArea:yellowAreas){
					Shape intersect = Shape.intersect(everyYellowArea, player);
					if (intersect.getBoundsInLocal().getWidth() != -1){
						int newSpawnPointX = (int)(everyYellowArea.getX()+everyYellowArea.getWidth()/2-player.getWidth()/2);
						int newSpawnPointY = (int)(everyYellowArea.getY()+everyYellowArea.getHeight()/2-player.getHeight()/2);
						DotMovement.setSpawnPointX(newSpawnPointX);
						DotMovement.setSpawnPointY(newSpawnPointY);
					}
				}

				//collision detection between the player and the coins
				for(Circle everyCoin:coins){
					Shape intersect = Shape.intersect(everyCoin, player);
					if (intersect.getBoundsInLocal().getWidth() != -1){
						coinCollected++;
						Music.playCoinSound();
						everyCoin.setLayoutX(-100);
						everyCoin.setLayoutY(-100);
					}
				}

			}
		};

		rectangleAnimation.start();

	}

	
	
	private static boolean checkCollision(ArrayList<Rectangle> listOfRectangles, Rectangle player){
		for(Rectangle everyRec:listOfRectangles){
			Shape intersect = Shape.intersect(everyRec, player);
			if (intersect.getBoundsInLocal().getWidth() != -1){
				return true;
			}
		}
		return false;
	}


	// the player can be set to dead, so that it will no longer be available to move
	public static void setDead(boolean current){
		dead = current;
	}

	public static boolean getDead(){
		return dead;
	}
}
