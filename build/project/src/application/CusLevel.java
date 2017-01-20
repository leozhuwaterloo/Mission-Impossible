package application;

public class CusLevel {
	//a class that will be used to associate customized levels with its corresponding properties
	//this class will be used to create tables
	private String name;
	private int timesPlayed;
	private	double rating;
	private int minDeath;
	//constructors
	public CusLevel(){
		this.name = "";
		this.timesPlayed = 0;
		this.rating = 0;
		this.minDeath = 100;
	}

	public CusLevel(String name, int timesPlayed, double rating, int death){
		this.name = name;
		this.timesPlayed = timesPlayed;
		this.rating = rating;
		this.minDeath = death;
	}
	//getters and setters
	public int getMinDeath() {
		return minDeath;
	}

	public void setMinDeath(int minDeath) {
		this.minDeath = minDeath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTimesPlayed() {
		return timesPlayed;
	}

	public void setTimesPlayed(int timesPlayed) {
		this.timesPlayed = timesPlayed;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
}
