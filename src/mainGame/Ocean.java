package mainGame;

public class Ocean {

	private Tile[][] ocean;
	
	public Ocean() {
		ocean = new Tile[10][10];
		
		for (int i = 0; i < ocean.length; i++) {
			for (int j = 0; j < ocean[0].length; j++) {
				ocean[i][j] = new Tile();
			}
		}
		
		
	}
	
	public void placeBoat(int row, int column, int boatLength, int direction){
		//TODO: 
		
	}

	public void showOcean(){
		
		for (Tile[] tiles : ocean) {
			for (Tile tile : tiles) {
				
				
			}
		}
		
	}
	
}
