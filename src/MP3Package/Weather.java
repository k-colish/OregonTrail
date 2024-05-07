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
	
	private int currentTemp, destinationIndex;
	private double snowOnGround, rainOnGround, currentPrecipitation;
	private String weatherStatus;
	private boolean isRainy, heavyRain, lightRain, keepWeather, isDrought, isSevereDrought,
					snowyTerrain;
	
	public Weather() {
	    currentTemp = 0; // Or any sensible default
	    destinationIndex = 0; // Default destination
	    snowOnGround = 0.0;
	    rainOnGround = 0.0;
	    currentPrecipitation = 0.0;
	    weatherStatus = "";
	    isRainy = false;
	    heavyRain = false;
	    lightRain = false;
	    keepWeather = false;
	    isDrought = false;
	    isSevereDrought = false;
	    snowyTerrain = false;
	}
	
	/**
	 * getWeather - calculates and determines the weather conditions
	 * 
	 * @param temp - the average daily temperature
	 * @param precipitation - the average daily precipitation
	 * @return - the weather condition for the day
	 */
	public String getWeather(int destinationDistance, int month){
		
		//fill array of temperatures for each destination and month
		int[][] tempArray ={{26,31,43,54,65,74,77,74,67,55,42,30},
							{31,36,47,57,67,76,80,78,71,60,48,36},
							{29,34,44,55,65,75,80,78,69,56,43,32},
							{28,28,39,47,57,68,75,72,63,50,38,27},
							{27,28,37,45,54,65,77,70,59,46,34,31},
							{33,38,46,53,62,72,79,78,68,56,43,33},
							{33,37,43,48,55,64,71,69,61,51,41,33},
							{31,32,34,42,50,60,70,69,62,50,36,30},
							{48,53,57,61,68,74,77,77,74,66,55,48}};
					
		//fill array of precipitation for each destination and month
		double[][] precipitationArray = {{4.1,5.4,2.4,3.5,4.6,4.4,3.4,3.7,3.5,3.2,2.1,2.4},
										{4.8,4.1,4.3,4.4,4,4.3,3.5,3.5,3.4,2.8,3.8,4.8},
										{6.2,6.3,4.8,3.5,5.1,5.5,4.1,3.8,3.7,2.9,2.7,5.4},
										{5.1,8.7,8.6,6.2,4.4,2.6,1.4,1.2,1.3,1.1,.5,.5,},
										{2.8,6.1,4.2,4.7,3.4,2.1,1.8,1.4,1.3,2.9,3.2,5.2},
										{12.8,11.8,8.5,5.3,2.4,1,.6,.6,1,2,7.8,11.4},
										{6,5,2.9,.8,.6,.3,.2,.2,.4,.7,1,5.3},
										{7.9,8.4,9.7,4.7,3.6,1.4,.3,.5,1,3.4,5.5,10},
										{4,3.7,2.9,1.5,.9,.2,0,0,.1,.9,1.8,3.7}};
		
		
		//sets destination index to use for array later
		if (destinationDistance < 125)
			destinationIndex = 0;
		if (destinationDistance < 225)
			destinationIndex = 1;
		if (destinationDistance < 675)
			destinationIndex = 2;
		if (destinationDistance < 805)
			destinationIndex = 3;
		if (destinationDistance < 1235)
			destinationIndex = 4;
		if (destinationDistance < 1600)
			destinationIndex = 5;
		if (destinationDistance < 1700)
			destinationIndex = 6;
		if (destinationDistance < 1800)
			destinationIndex = 7;
		if (destinationDistance >= 1800 )
			destinationIndex = 8;
		
		if (Math.random() > .5)
			keepWeather = true;
		else
			keepWeather = false;
		
		//updates current temp and precipitation based on the correct 2d array value
		if (keepWeather) {
		currentTemp = tempArray[destinationIndex][month] + (int)(Math.random()*41) - 20;
		currentPrecipitation = precipitationArray[destinationIndex][month];
		}
		
		
		//implements 50% chance that weather stays the same
		//if (Math.random() < 0.5)
		//	keepWeather = true;
	
		//updates the rain or snow on the ground for the given weather conditions
		//particularly if the 50% chance to keep the same weather was true
		
		/**
		if (keepWeather == true) {
			
			//10% of accumulated rainfall disappears
			rainOnGround -= rainOnGround * 0.10;
			
			//then todays rainfall is added
			if (heavyRain == true && currentTemp >= 30)
				rainOnGround += 0.8;
			if (heavyRain == true && currentTemp < 30)
				snowOnGround += 8;
			if (lightRain == true && currentTemp >= 30)
				rainOnGround += 0.2;
			if (lightRain == true && currentTemp < 30)
				snowOnGround += 2;
			
			//updates the snow and rain on the ground
			if(temp < 50 &&
				heavyRain == false) {
				snowOnGround -= snowOnGround * .03;
			}
		
			//snow is converted to rainfall on ground
			if (temp >= 50 ||
				heavyRain == true){
				if (snowOnGround > 5) {
					snowOnGround -= 5;
					rainOnGround += 0.5;
					}
				}
		}
			**/
		
		double chanceOfRainfall = (currentPrecipitation / 20);

		// Determines whether it is raining or not
		if (chanceOfRainfall > Math.random()) {
		    isRainy = true;
		} else {
		    isRainy = false; // Reset chance of rain to false
		}

		// Determines whether it is heavy rain or light
		if (isRainy == true) {
		    if (Math.random() < 0.3) {
		        heavyRain = true;
		    } else {
		        lightRain = true;
		    }
		} else {
		    // Reset rain flags if not raining
		    heavyRain = false;
		    lightRain = false;
		}

		
		
		//updates the rain or snow on the ground for new days weather conditions
		//if (keepWeather == false) {
			
			//updates the visual weather text
			if (currentTemp > 90) 
				 weatherStatus = "Very Hot";
					
			if (currentTemp < 90 && currentTemp >= 70)
				weatherStatus = "Hot";
					
			if (currentTemp < 70 && currentTemp >= 50)
				weatherStatus = "Warm";
					
			if (currentTemp < 50 && currentTemp >= 30)
				weatherStatus = "Cool";
					
			if (currentTemp < 30 && currentTemp >= 10)
				weatherStatus = "Cold";
					
			if (currentTemp < 10)
				weatherStatus = "Very Cold";
			
			//10% of accumulated rainfall disappears
			rainOnGround -= rainOnGround * 0.10;
			
			//then todays rainfall is added
			if (heavyRain == true && currentTemp >= 30)
				rainOnGround += 0.8;
			if (heavyRain == true && currentTemp < 30)
				snowOnGround += 8;
			if (lightRain == true && currentTemp >= 30)
				rainOnGround += 0.2;
			if (lightRain == true && currentTemp < 30)
				snowOnGround += 2;
			
			//updates the snow and rain on the ground
			if(currentTemp < 50 &&
				heavyRain == false) {
				snowOnGround -= snowOnGround * .03;
			}
		
			//snow is converted to rainfall on ground
			if (currentTemp >= 50 ||
				heavyRain == true){
				if (snowOnGround > 5) {
					snowOnGround -= 5;
					rainOnGround += 0.5;
					}
				}
			
			//updates visual weather text
			if (lightRain == true)
				weatherStatus = "Rainy";
			
			if (heavyRain == true)
				weatherStatus = "Very Rainy";
			
			if (lightRain == true && currentTemp < 30)
				weatherStatus = "Snowy";
			
			if (heavyRain == true && currentTemp < 30)
				weatherStatus = "Very Snowy";
		//}
	
		
		//terrain is supposed to be painted orange if this occurs
		if (rainOnGround < 0.2 && snowOnGround < 1)
			isDrought = true;
		
		//player is supposed to be given drought messages ("insufficient grass",
		//inadequate water", "bad water"). if this occurs
		if (rainOnGround < 0.1 && snowOnGround < 1) {
			isSevereDrought = true;
			isDrought = false;
		}
		
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
	
	/**
	 * getTempereature - gets the temperature
	 * @return temperature
	 */
	public int getTemperature() {
		return currentTemp;
	}
	
	/**
	 * getPrecipitation - gets the precipitation
	 * @return
	 */
	public double getPrecipitation() {
		return currentPrecipitation;
	}
}
