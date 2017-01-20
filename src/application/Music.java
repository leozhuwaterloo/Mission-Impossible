package application;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {

	//All the music with boolean variables to make them play or not
	private static Media punchMusic = new Media(new File("punch.mp3").toURI().toString());
	private static Media backGroundMusic = new Media(new File("BackgroundMusic.mp3").toURI().toString());
	private static MediaPlayer background = new MediaPlayer(backGroundMusic);
	private static Media coinSound = new Media(new File("coinSound.mp3").toURI().toString());
	private static Media playSound = new Media(new File("pass.mp3").toURI().toString());

	private static boolean deathMusic = true;
	private static boolean coinMusic = true;
	private static boolean passMusic = true;

	
	//getter and setters
	public static MediaPlayer getBackgroundMusic(){
		return background;
	}

	public static void setDeathMusic(boolean playOrNot){
		deathMusic = playOrNot;
	}

	public static void setCoinMusic(boolean playOrNot){
		coinMusic = playOrNot;
	}

	public static void setPassMusic(boolean playOrNot){
		passMusic = playOrNot;
	}
	
	public static boolean getDeathMusic(){
		return deathMusic;
	}

	public static boolean getCoinMusic(){
		return coinMusic;
	}

	public static boolean getPassMusic(){
		return passMusic;
	}

	public static void playBackgroundMusic(){
		background.setCycleCount(MediaPlayer.INDEFINITE);
		background.play();
	}


	public static void playPunchSound(){
		if(deathMusic)
			new MediaPlayer(punchMusic).play();
	}

	public static void playCoinSound(){
		if(coinMusic)
			new MediaPlayer(coinSound).play();
	}

	public static void playPassSound(){
		if(passMusic)
			new MediaPlayer(playSound).play();
	}
}
