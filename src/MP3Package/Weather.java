/**
 * Weather.java
 * 
 * The weather class will take in data and use probabilities to determine what the 
 * weather conditions will be each day. It will also update how much snow/rain is on 
 * the ground, which will be used to figure out if there is a drought
 * 
 * @author - Kaiden Colish, Justin Schiefer, Zachary Iles, & Mitchell Gerwin
 */
package MP3Package;

public class Weather {
	
	//instance variables
	private double snowOnGround = 0, rainOnGround = 0;
	private String weatherStatus = "Cool";
	private boolean isRainy, heavyRain, lightRain, keepWeather, isDrought, isSevereDrought,
					snowyTerrain;
	
	
	/**
	 * getWeather - calculates and determines the weather conditions
	 * 
	 * @param temp - the average daily temperature
	 * @param precipitation - the average daily precipitation
	 * @return - the weather condition for the day
	 */
	public String getWeather(int temp, double precipitation) {
		
		//implements 50% chance that weather stays the same
		if (Math.random() < 0.5)
			keepWeather = true;
		
	
		//updates the rain or snow on the ground for the given weather conditions
		//particularly if the 50% chance to keep the same weather was true
		if (keepWeather == true) {
			
			//10% of accumulated rainfall disappears
			rainOnGround -= rainOnGround * 0.10;
			
			//then todays rainfall is added
			if (heavyRain == true && weatherStatus != "Cold" || weatherStatus != "Very Cold")
				rainOnGround += 0.8;
			if (heavyRain == true && weatherStatus == "Cold" || weatherStatus == "Very Cold")
				snowOnGround += 8;
			if (lightRain == true && weatherStatus != "Cold" || weatherStatus != "Very Cold")
				rainOnGround += 0.2;
			if (lightRain == true && weatherStatus == "Cold" || weatherStatus == "Very Cold")
				snowOnGround += 2;
			
			//updates the snow and rain on the ground
			if(weatherStatus == "Very Cold" || weatherStatus == "Cold" || weatherStatus == "Cool" &&
				heavyRain == false) {
				snowOnGround -= snowOnGround * .03;
			}
		
			//snow is converted to rainfall on ground
			if (weatherStatus == "Warm" || weatherStatus == "Hot" || weatherStatus == "Very Hot" ||
				heavyRain == true){
				if (snowOnGround > 5) {
					snowOnGround -= 5;
					rainOnGround += 0.5;
					}
				}
		}
			
		//gets probability of it raining
		double chanceOfRainfall = (precipitation / 20);
		
		//determines whether it is raining or not
		if(chanceOfRainfall > Math.random())
			isRainy = true;
		
		//determines whether is is heavy rain or light
		if (isRainy = true) {
			if (Math.random() < 0.3)
				heavyRain = true;
			else
				lightRain = true;
		}
		
		temp = temp + (int)(Math.random()*41) - 20;
		
		
		//updates the rain or snow on the ground for new days weather conditions
		if (keepWeather == false) {
			
			//updates the visual weather text
			if (temp > 90) 
				 weatherStatus = "Very hot";
					
			if (temp < 90 && temp >= 70)
				weatherStatus = "Hot";
					
			if (temp < 70 && temp >= 50)
				weatherStatus = "Warm";
					
			if (temp < 50 && temp >= 30)
				weatherStatus = "Cool";
					
			if (temp < 30 && temp >= 10)
				weatherStatus = "Cold";
					
			if (temp < 10)
				weatherStatus = "Very Cold";
			
			//10% of accumulated rainfall disappears
			rainOnGround -= rainOnGround * 0.10;
			
			//then todays rainfall is added
			if (heavyRain == true && weatherStatus != "Cold" || weatherStatus != "Very Cold")
				rainOnGround += 0.8;
			if (heavyRain == true && weatherStatus == "Cold" || weatherStatus == "Very Cold")
				snowOnGround += 8;
			if (lightRain == true && weatherStatus != "Cold" || weatherStatus != "Very Cold")
				rainOnGround += 0.2;
			if (lightRain == true && weatherStatus == "Cold" || weatherStatus == "Very Cold")
				snowOnGround += 2;
		
		//updates the snow and rain on the ground
			if(weatherStatus == "Very Cold" || weatherStatus == "Cold" || weatherStatus == "Cool" &&
			   heavyRain == false) {
					snowOnGround -= snowOnGround * .03;
				}
			
			if (weatherStatus == "Warm" || weatherStatus == "Hot" || weatherStatus == "Very Hot" ||
				heavyRain == true){
					snowOnGround -= 5;
					rainOnGround += 0.5;
			}
			
			//updates visual weather text
			if (lightRain == true)
				weatherStatus = "Rainy";
			
			if (heavyRain == true)
				weatherStatus = "Very Rainy";
			
			if (lightRain == true && temp < 30)
				weatherStatus = "Snowy";
			
			if (heavyRain == true && temp < 30)
				weatherStatus = "Very Snowy";
		}
	
		
		//terrain is supposed to be painted orange if this occurs
		if (rainOnGround < 0.2 && snowOnGround < 1)
			isDrought = true;
		
		//player is supposed to be given drought messages ("insufficient grass",
		//inadequate water", "bad water"). if this occurs
		if (rainOnGround < 0.1 && snowOnGround < 1)
			isSevereDrought = true;
			isDrought = false;
		
		//terrain is painted white if there is at least 1 inch of snow on the ground
		if (snowOnGround >= 1)
			snowyTerrain = true;
			
		
		return weatherStatus;
	}
	
	/**
	 * isSnowyTerrain - figures out if there should be displayed snow on the ground
	 * @return - true if the terrain is snowy or false otherwise
	 */
	public boolean isSnowyTerrain() {
		return snowyTerrain;
	}
	
	/**
	 * isDrought - figures out if there is a drought and terrain should be orange
	 * @return - true if there is a drought or false otherwise
	 */
	public boolean isDrought() {
		return isDrought;
	}
	
	/**
	 * isSevereDrought - figures out if there should be drought messages displayed
	 * @return true if there is a severe drought or false otherwise
	 */
	public boolean isSevereDrought() {
		return isSevereDrought;
	}
}
