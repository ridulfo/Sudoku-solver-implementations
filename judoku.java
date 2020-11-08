import java.util.PriorityQueue;

public class Board{
	private int[][] values;
	final int size = 9; // The size of one side of the board

	/**
	 * A Sudoku board (9x9)
	 */
	public Board() {
		values = new int[size][size];
	}

	/**
	 * Returns if the number exists in the x:th column
	 * 
	 * @param x      the column number
	 * @param number the number to check for
	 * @return true if exist in column
	 */
	private boolean isInColumn(int x, int number) {
		for (int y = 0; y < size; y++)
			if (values[x][y] == number)
				return true;

		return false;
	}

	/**
	 * Returns if the number exists in the y:th row
	 * 
	 * @param y      the row number
	 * @param number the number to check for
	 * @return true if exist in row
	 */
	private boolean isInRow(int y, int number) {
		for (int x = 0; x < size; x++)
			if (values[x][y] == number)
				return true;

		return false;
	}

	/**
	 * Returns if the number exist in one of the 3x3 square
	 * 
	 * @param x      x position
	 * @param y      y position
	 * @param number the number to check for
	 * @return true if the number exists in square
	 */
	private boolean isInSquare(int x, int y, int number) {
		int r = x - x % 3;
		int c = y - y % 3;
		for (int i = r; i < r + 3; i++)
			for (int j = c; j < c + 3; j++)
				if (values[i][j] == number)
					return true;

		return false;

	}

	/**
	 * Returns if it is allowed to set the given number at the given position
	 * 
	 * @param x      x position
	 * @param y      y position
	 * @param number the number to check
	 * @return true if the move would be allowed
	 */
	private boolean isAllowed(int x, int y, int number) {
		if (!isInColumn(x, number) && !isInRow(y, number) && !isInSquare(x, y, number))
			return true;
		return false;
	}

	/**
	 * Solves the board
	 * 
	 * @return true if the board could be solved, false if could not
	 */
	public boolean solve() {
		// Check if the current config is allowed
		if (!isCorrect())
			return false;

		// Solve
		return solve(0, 0);
	}

	/**
	 * Solves the board recursively. Starts at the given x/y position Benchmark:
	 * 0.0022ms
	 * 
	 * @param x the x position
	 * @param y the y position
	 * @return true if the board is solved
	 */
	private boolean solve(int x, int y) {
		if (y > 8) { // If the last row is done -> finished
			return true;
		}
		if (values[x][y] == 0) { // check if cell is empty
			for (int nextNum = 1; nextNum <= 9; nextNum++) { // Try all the values
				if (isAllowed(x, y, nextNum)) { // Is it allowed?
					values[x][y] = nextNum; // Set the value
					if (solve((x + 1) % size, y + (int) (x / 8))) { // recursive call
						return true;
					}
				}
			}
			values[x][y] = 0; // clear
			return false; // Return false (backtracking)
		} else {
			return solve((x + 1) % size, y + (int) (x / 8)); // if the cell was alredy filled
		}
	}

	/**
	 * Checks if the current board configuration is in accordance with the rules
	 * @return true if correct, false if incorrect
	 */
	public boolean isCorrect() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				int temp = values[x][y];
				if (temp != 0) {
					values[x][y] = 0;
					if (!isAllowed(x, y, temp)) {
						values[x][y] = temp;
						return false;
					}
					values[x][y] = temp;
				}
			}
		}
		return true;
	}

	/**
	 * Clears the board
	 */
	public void clear() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				values[x][y] = 0;
			}
		}
	}

	/**
	 * Sets value at cell
	 * @param x x position
	 * @param y y position
	 * @param value value to set
	 */
	public void setValue(int x, int y, int value) {
		values[x][y] = value;
	}
	public int getValue(int x, int y) {
		return values[x][y];
	}

	private void print() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				System.out.print("[" + values[x][y] + "]");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	public void setTable(int[][] values) {
		this.values = values;
	}

	public static void main(String[] args)  {

		Board board = new Board();
		board.print();
		PriorityQueue<Long> times = new PriorityQueue<>();
		for(int i = 0;i<10;i++){
			long t0 = System.nanoTime();
			board.solve();
			long t1 = System.nanoTime();
			board.solve();
			times.add((t1-t0)/1000);
		}
		board.print();
		System.out.println(times.poll());
    }
}
