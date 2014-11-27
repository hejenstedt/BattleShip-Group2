package mainGame;

public class Tile {

	private boolean boatExistsOnTile;
	private boolean tileHasBeenShootAt;
	private Boat boat;

	public Tile() {
		tileHasBeenShootAt = false;
		boatExistsOnTile = false;
	}
	
	public void setBoatOnTile(Boat boat){
		this.boat= boat;
		boatExistsOnTile= true;
	}
	
	public boolean isBoatOnTile(){
		return boatExistsOnTile;
	}

	public void shootAtTile() {
		tileHasBeenShootAt = true;
		if (boatExistsOnTile) {
			boat.setHit();
		}
	}

	public boolean tileHasBeenShootBefore() {
		return tileHasBeenShootAt;
	}

	@Override
	public String toString() {
		if (tileHasBeenShootAt) {

			if (boatExistsOnTile) {
				return boat.toString();
			}
			return "X";
		}
		return "~";
	}

	public String tileShowingBoat() {
		
			if (boatExistsOnTile) {
				return ""+boat.getBoatLength();
			}
		return "~";
	}
	
}
