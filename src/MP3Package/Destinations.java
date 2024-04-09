package MP3Package;

public class Destinations {
	private int distance;
	private String name;
	private boolean isFort;
	
	
	public Destinations(int distance, String name, boolean isFort) {
		this.distance = distance;
		this.name = name;
		this.isFort = isFort;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean lookAround(char choice) {
		
		if(choice == 'y' || choice == 'Y') {
			return true;
		}
		else{
			return false;
		}
	}
}
