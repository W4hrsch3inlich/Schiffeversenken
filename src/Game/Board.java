package Game;

/**
 * The Board class contains the logic for
 * a single Board.
 *
 * @author wolf
 */
public class Board {
	/**
	 * Array holding all Ships on the given Board.
	 */
	private Ship[] ship;

	/**
	 * Maximum x-coordinate of the given Board.
	 */
	private int xMax;

	/**
	 * Maximum y-coordinate of the given Board.
	 */
	private int yMax;

	/**
	 * Constructor for a Board.
	 * @param x Maximum x-coordinate
	 * @param y Maximum y-coordinate
	 * @param num Number of Ships.
	 */
	public Board(int x, int y, int num) {
		this.xMax = x;
		this.yMax = y;
		this.ship = new Ship[num];
		/**
		 * TODO:
		 * Ask the player to enter the data necessary to instantiate
		 * the Ships over the command line.
		 */
	}

	/**
	 * Checks if a Board does still contain any Ships.
	 * @return boolean True if no remaining Ships on the given Board
	 */
	public boolean check() {
		/**
		 * TODO:
		 * Iterate through all Ships and check if all
		 * of them are sunk with .check().
		 */
	}

	/**
	 * Shoot at a given Board.
	 * @param x	x-coordinate to shoot at.
	 * @param y y-coordinate to shoot at.
	 * @return int 0 = no hit, 1 = first hit, 2 = hit again, 3 = sunk a Ship.
	 */
	public int shoot(int x, int y) {
		/**
		 * TODO:
		 * Iterate through all Ships and check if one
		 * gets hit with .shoot().
		 */
	}
}