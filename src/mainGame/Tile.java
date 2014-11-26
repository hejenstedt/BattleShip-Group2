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

	public void shootAtTile() {
		tileHasBeenShootAt = true;
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

}
