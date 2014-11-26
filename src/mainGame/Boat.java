package mainGame;

public class Boat {
	private boolean[] boatShotAt;

	public Boat(int length) {
		boatShotAt = new boolean[length];

		for (int i = 0; i < boatShotAt.length; i++) {
			boatShotAt[i] = false;
		}

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
		return ""+boatShotAt.length;
	}

}
