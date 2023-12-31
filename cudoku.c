#include <stdbool.h>
#include <stdio.h>
#include <time.h>

bool isAllowed(int *board, int row, int col, int val) {
  for (size_t i = 0; i < 9; i++) {
    if (board[row * 9 + i] == val)
      return false;

    if (board[i * 9 + col] == val)
      return false;
  }

  int row_start = 3 * (row / 3);
  int col_start = 3 * (col / 3);

  for (int r = row_start; r < row_start + 3; r++) {
    for (int c = col_start; c < col_start + 3; c++) {
      if (board[r * 9 + c] == val)
        return false;
    }
  }

  return true;
}

void solve(int *board) {
  bool solved = false;
  int row = 0;
  int column = 0;
  while (!solved) {
    bool move = false;
    for (int n = board[row * 9 + column] + 1; n < 10; n++) {
      if (isAllowed(board, row, column, n)) {
        board[row * 9 + column] = n;
        move = true;
        break;
      }
    }
    if (move) {
      if (row == 8 && column == 8)
        solved = true;
      column = (column + 1) % 9;
      if (column == 0)
        row += 1;
    } else {
      board[row * 9 + column] = 0;
      column = (column - 1) % 9;
      if (column == 0)
        row -= 1;
    }
  }
}

int main() {
  float total = 0;
  for (int i = 0; i < 100; i++) {
    int grid[9 * 9] = {0};
    float startTime = (float)clock() / CLOCKS_PER_SEC;
    solve(grid);
    float endTime = (float)clock() / CLOCKS_PER_SEC;
    float timeElapsed = endTime - startTime;
    total += timeElapsed;
  }

  printf("%f us\n", 1e6 * total / 100);
}
