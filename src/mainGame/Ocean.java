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

	
	public void placeBoat(int row, int column, int boatLength, String direction){
		//TODO: where should the validation logic be?
		//TODO: where should we turn char input into int input?
		
		Boat boat = new Boat(boatLength);
		ocean[row][column].setBoatOnTile(boat);
		
		switch (direction) {
		case "south":
			for (int i = 1; i < boatLength; i++) {
				ocean[row+i][column].setBoatOnTile(boat);
			}
			break;
		case "north":
			for (int i = 1; i < boatLength; i++) {
				ocean[row-i][column].setBoatOnTile(boat);
			}
			break;
		case "west":
			for (int i = 1; i < boatLength; i++) {
				ocean[row][column-1].setBoatOnTile(boat);
			}
			break;
		case "east":
			for (int i = 1; i < boatLength; i++) {
				ocean[row][column+1].setBoatOnTile(boat);
			}
			break;
		default:
			break;
		}

		
	}
	
	public void shoot(int row, int column){
		ocean[row][column].shootAtTile();
	}

	public void showOcean(){
		
		System.out.println("  A B C D E F G H I J");
		System.out.println(" ______________________");
		int row=0;
		for (Tile[] tiles : ocean) {
			System.out.print(row+"|");
			for (Tile tile : tiles) {
				System.out.print(tile.toString()+" ");				
			}
			System.out.println("| "+row);
			row++;
		}
		System.out.println(" ______________________");
		System.out.println("  A B C D E F G H I J");
		
	}
	
}
