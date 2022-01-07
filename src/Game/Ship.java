package Game;

/**
 * The class Ship represents a Ship. It is used
 * to contain all necessary logic for a Ship object.
 *
 * @author Armin Wolf
 */
public class Ship {
	/**
	 * Array holding tree values for each Ship cell:
	 * [0] x-coordinate
	 * [1] y-coordinate
	 * [2] status (0 = not hit, hit otherwise)
	 */
	private int[][] coord;
	
	/**
	 * Constructor for a Ship.
	 * @param x x-coordinate of the starting point.
	 * @param y y-coordinate of the starting point.
	 * @param length Length of the whole Ship.
	 * @param directionX Distance between each cells in x-direction.
	 * @param directionY Distance between each cells in y-direction.
	 */
	public Ship(int x, int y, int length, int directionX, int directionY) {
		int currentX = x;
		int currentY = y;

		/* entry[2] =! 0 if coord got hit */
		this.coord = new int[length][3];
		/* Iterate through all array cells, using entry as an index */
		for (int[] entry: this.coord) {
			entry[0] = currentX;
			entry[1] = currentY;
			entry[2] = 0;
			/* direction tells us which values to add for each iteration */
			currentX += directionX;
			currentY += directionY;
		}
	}
	
	/**
	 * Check if Ship occupies a given coordinate.
	 * @param x x-coordinate to check.
	 * @param y y-coordinate to check.
	 * @return boolean True if Ship occupies the given coordinate.
	 */
	public boolean check(int x, int y) {
		for (int[] entry: this.coord) {
			if (entry[0] == x && entry[1] == y) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Check if a given shoot hits the Ship.
	 * @param x x-coordinate to shot at.
	 * @param y y-coordinate to shot at.
	 * @return int 0 = no hit, 1 = first hit, 2 = hit again, 3 = ship sunk.
	 */
	public int shot(int x, int y) {
		boolean sunk = true;
		int status = 0;
		
		for(int[] entry: this.coord) {
			/* Check if coordinate matches cell */
			if (entry[0] == x && entry[1] == y) {
				/* Check if first hit for this cell */
				if (entry[2] == 0) {
					/* Update cell status */
					entry[2] = 1;
					status = 1;
				} else {
					status = 2;
				}
			}
			/* If at least one cell has no hits, the the Ship does not sink */
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
