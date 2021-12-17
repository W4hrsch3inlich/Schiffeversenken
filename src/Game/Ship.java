package Game;

public class Ship {
	private int[][] coord;
	
	public Ship(int x, int y, int length, int directionX, int directionY) {
		int currentX = x;
		int currentY = y;

		/* entry[2] =! 0 if coord got hit */
		this.coord = new int[length][3];
		for (int[] entry: this.coord) {
			entry[0] = currentX;
			entry[1] = currentY;
			entry[2] = 0;
			/* direction tells us which values to add for each iteration */
			currentX += directionX;
			currentY += directionY;
		}
	}
	
	public boolean check(int x, int y) {
		for (int[] entry: this.coord) {
			if (entry[0] == x && entry[1] == y) {
				return true;
			}
		}
		
		return false;
	}
	
	public int shot(int x, int y) {
		boolean sunk = true;
		int status = 0;
		
		for(int[] entry: this.coord) {
			if (entry[0] == x && entry[1] == y) {
				if (entry[2] != 0) {
					status = 1;	/* first hit */
				} else {
					status = 2;	/* hit again */
				}
			}
			if (entry[2] == 0) {
				sunk = false;
			}
		}
		if (sunk) {
			return 3;
		}
		
		return status;
	}
}
