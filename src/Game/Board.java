package Game;
import java.util.Scanner;

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
		/* Coordinates start in the lower left corner */
		this.xMax = x;
		this.yMax = y;
		this.ship = new Ship[num];
		int xCoord, yCoord, length, xDirection, yDirection;
		boolean overlapps = false;

		for (int i = 0; i < num; i++) {
			while (true) {
				/* clears the console */
				System.out.print("\033[H\033[2J");
				System.out.println("Ship number " + i + " :");

				System.out.println("Starting point (X Y)?");
				while (true) {
					xCoord = this.getInt();
					if (xCoord > this.xMax || xCoord < 0) {
						System.out.println("The maximum value for X is " + this.xMax);
						continue;
					}
					yCoord = this.getInt();
					if (yCoord > this.yMax || yCoord < 0) {
						System.out.println("The maximum value for Y is " + this.yMax);
						continue;
					}
					break;
				}

				System.out.println("Length?");
				while (true) {
					length = this.getInt();
					if (length > this.xMax - xCoord && length > this.yMax - yCoord) {
						System.out.println("Ship would be too long");
						continue;
					}
					break;
				}

				System.out.println("In which direction (N/E/S/W)?");
				while (true) {
					switch (this.getChar()) {
					case 'N':
						xDirection = 0;
						yDirection = 1;
						break;
					case 'E':
						xDirection = 1;
						yDirection = 0;
						break;
					case 'S':
						xDirection = 0;
						yDirection = -1;
						break;
					case 'W':
						xDirection = -1;
						yDirection = 0;
						break;
					default:
						System.out.println("Not a valid direction");
						continue;
					}
					if (xCoord + length * xDirection > this.xMax || xCoord + length * xDirection > 0 ||
						yCoord + length * yDirection > this.yMax || yCoord + length * yDirection > this.yMax) {
						System.out.println("Ship would not fit onto this Board");
						continue;
					}
					break;
				}
				for (Ship ship: this.ship) {
					if (ship == null) {
						continue;
					}
					for (int cell_num = 0; cell_num <= length; cell_num++) {
						if (ship.check(xCoord + cell_num * xDirection, yCoord + cell_num * yDirection)) {
							System.out.println("Ship is overlapping");
							overlapps = true;
						}
					}
				}
				if (!overlapps) {
					this.ship[i] = new Ship(xCoord, yCoord, length, xDirection, yDirection);
					break;
				}
				/* The while loop retries the input after 1 second */
				try {
				    Thread.sleep(1000);
				}
				catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			}
		}
	}

	private int getInt() {
		/* stdin is automatically closed on exit */
		try (Scanner stdin = new Scanner(System.in)) {
			while (true) {
				try {
					return stdin.nextInt();
				} catch (java.util.InputMismatchException e) {
					/* nextLine() is necessary to flush stdin! */
					System.out.println(stdin.nextLine() + " is not a valid number!");
					continue;
				}
			}
		}
	}

	private char getChar() {
		/* stdin is automatically closed on exit */
		try (Scanner stdin = new Scanner(System.in)) {
			while (true) {
				String str = stdin.next();
				if (str.length() > 1) {
					System.out.println("To much info");
					continue;
				}

				return str.charAt(0);
			}
		}
	}
	/**
	 * Checks if a Board does still contain any Ships.
	 * @return boolean True if no remaining Ships on the given Board
	 */
	public boolean check() {
		boolean sunkAll = true;

		for (Ship ship: this.ship) {
			if (ship != null) {
				sunkAll = false;
			}
		}

		return sunkAll;
	}

	/**
	 * Shoot at a given Board.
	 * @param x	x-coordinate to shoot at.
	 * @param y y-coordinate to shoot at.
	 * @return int 0 = no hit, 1 = first hit, 2 = hit again, 3 = sunk a Ship.
	 */
	public int shoot(int x, int y) {
		int result = 0;

		for (Ship ship: this.ship) {
			if (ship == null) {
				/* Ship is already sunk */
				continue;
			}
			result = ship.shot(x,  y);
			if (result > 0) {
				if (result == 3) {
					/* mark Ship as sunken */
					ship = null;
				}
				break;
			}
		}

		return result;
	}
}