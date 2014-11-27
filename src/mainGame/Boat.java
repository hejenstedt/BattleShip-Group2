package mainGame;

public class Boat {
	private boolean[] boatShotAt;
	private int boatLength;

	public Boat(int length) {
		boatShotAt = new boolean[length];
		boatLength=length;
		for (int i = 0; i < boatShotAt.length; i++) {
			boatShotAt[i] = false;
		}

	}
	
	public int getBoatLength() {
		return boatLength;
	}

	public boolean boatSinked() {
		for (int i = 0; i < boatShotAt.length; i++) {
			if (boatShotAt[i] == false) {
				return false;
			}
		}
		return true;
	}

	public void setHit() {
		for (int i = 0; i < boatShotAt.length; i++) {
			if (boatShotAt[i] == false){
				boatShotAt[i] = true;
				break;
			}
		}
	}
	
	public String toString(){
		
		if (boatSinked()) {
			return ""+boatShotAt.length;
			
		}
		return "H";
		
	}

}
