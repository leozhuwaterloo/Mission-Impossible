package application;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;


public class DotMovement {
	private static int spawnPointX;
	private static int spawnPointY;
	private static int numberOfDeath = 0;
	
	public static int getNumberOfDeath(){
		return numberOfDeath;
	}
	
	public static void resetNumberOfDeath(){
		numberOfDeath = 0;
	}
	
	//create a circle path for the dots 
	private Path createEllipsePath(double centerX, double centerY, int radius, int start) {

		int x = 0;
		int y = 0;
		ArcTo arcTo = new ArcTo();

		if(start ==1){
			y=-radius;
			arcTo.setX(x-1);
			arcTo.setY(y);	
		}else if(start==2){
			x=radius;
			arcTo.setX(x);
			arcTo.setY(y-1);
		}else if(start==3){
			y=radius;
			arcTo.setX(x+1);
			arcTo.setY(y);	
		}else if(start==4){
			x=-radius;
			arcTo.setX(x);
			arcTo.setY(y+1);
		}
		
		arcTo.setSweepFlag(true);
		arcTo.setLargeArcFlag(true);
		arcTo.setRadiusX(radius);
		arcTo.setRadiusY(radius);

		Path path = new Path();
		path.getElements().add(new MoveTo(x, y));
		path.getElements().add(arcTo);
		path.getElements().add(new ClosePath());
		return path;
	}


	public static void setSpawnPointX(int spawnPointX) {
		DotMovement.spawnPointX = spawnPointX;
	}

	public static void setSpawnPointY(int spawnPointY) {
		DotMovement.spawnPointY = spawnPointY;
	}


	public DotMovement(Circle dot, int direction, int from, int to, Rectangle player, int setSpeed){
		//based on the things reading from the txt file, set the dot to follow a certain path 
		PathTransition followPath = new PathTransition();
		Path path = new Path();
		if(direction==0){
			followPath = new PathTransition();
		}else if(direction==1){
			path.getElements().add(new MoveTo(0,0));
			path.getElements().add(new LineTo(from-dot.getLayoutX(),to-dot.getLayoutY()));
			followPath.setAutoReverse(true);
		}else if(direction==2){
			int width = (int) (from-dot.getLayoutX());
			int height = (int) (to-dot.getLayoutY());
			path.getElements().add(new MoveTo(0,0));
			path.getElements().add(new LineTo(width,0));
			path.getElements().add(new LineTo(width,height));
			path.getElements().add(new LineTo(0,height));
			path.getElements().add(new LineTo(0,0));
		} else if (direction ==3){
			path = createEllipsePath(dot.getLayoutX(), dot.getLayoutX(), from, to);
			followPath.setDelay((Duration.millis(to)));
		}

		followPath.setDuration(Duration.millis(setSpeed));
		followPath.setPath(path);
		followPath.setNode(dot);
		followPath.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		followPath.setInterpolator(Interpolator.LINEAR);
		followPath.setCycleCount(Timeline.INDEFINITE);
		followPath.play();

		//a timer that detects the collision and set the player to death
		AnimationTimer rectangleAnimation = new AnimationTimer() {
			public void handle(long now){
				Shape intersect = Shape.intersect(dot, player);
				if (intersect.getBoundsInLocal().getWidth() != -1){
					if(!PlayerMovement.getDead()){
						PlayerMovement.setDead(true);
						numberOfDeath++;
						Music.playPunchSound();
						FadeTransition fadeout = new FadeTransition(Duration.millis(1000),player);
						fadeout.setFromValue(1.0);
						fadeout.setToValue(0.0);
						fadeout.play();

						PauseTransition delay = new PauseTransition(Duration.millis(1050));
						delay.setOnFinished(e -> {
							player.setX(spawnPointX);
							player.setY(spawnPointY);
							player.setOpacity(1.0);
							PlayerMovement.setDead(false);
						});
						delay.play();
					}
				}
			}
		};
		rectangleAnimation.start();


	}
}
