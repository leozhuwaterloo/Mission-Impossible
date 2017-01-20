package application;

public class PlayerInformation {
	private String name;
	private int numberOfDeath;
	private String date;

	//a class that stores the player's information
	//this classes is used to create the high score table
	
	//constructors
	public PlayerInformation(){
		this.name = "";
		this.numberOfDeath = 999;
		this.date = "00/00/0000";

	}
	
	public PlayerInformation(String name, int numberOfDeath, String date){
        this.name = name;
        this.numberOfDeath = numberOfDeath;
        this.date = date;
    }

	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfDeath() {
		return numberOfDeath;
	}

	public void setNumberOfDeath(int numberOfDeath) {
		this.numberOfDeath = numberOfDeath;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
