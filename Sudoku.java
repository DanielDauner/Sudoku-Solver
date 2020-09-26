public class Sudoku {

	private int[][] sudoku;
	private int dimension;
	private int iteration;

	public Sudoku(int[][] sudoku) {
		this.sudoku = sudoku;
		this.dimension = sudoku.length;
		this.iteration = 0;
	}

	public int[][] backtracking() {
		this.iteration = 0;
		if (!backtrackSolve()) {
			System.err.println("Solution not found in time");
		}
		return sudoku;
	}

	private boolean isPossibleNumber(int i, int j, int number) {

		for (int x = 0; x < dimension; x++) {
			if (sudoku[x][j] == number || sudoku[i][x] == number) {
				return false;
			}
		}
		int subcolumn = i - i % 3;
		int subrow = j - j % 3;
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (sudoku[subcolumn + x][subrow + y] == number) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean backtrackSolve() {
		if(this.iteration > 1000000) {
			return false;
		}
		this.iteration += 1;
		int i = 0;
		int j = 0;
		boolean hasEmptyCell = false;
		for (int x = 0; x < dimension && !hasEmptyCell; x++) {
			for (int y = 0; y < dimension && !hasEmptyCell; y++) {
				if (sudoku[x][y] == 0) {
					i = x;
					j = y;
					hasEmptyCell = true;
				}
			}
		}
		if(!hasEmptyCell) {
			return true;
		}
		for(int number = 1; number <= dimension; number++) {
			if (isPossibleNumber(i, j, number)) {
				sudoku[i][j] = number;

				if (backtrackSolve()) {
					return true;
					
				}else {
					sudoku[i][j] = 0;
				}
			}
		}
		return false;
	}
}