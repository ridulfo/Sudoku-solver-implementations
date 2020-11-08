import numpy as np
from numba import njit
from time import sleep, time

@njit(fastmath=True)
def isAllowed(board, row, column, value):
    for cell in board[row]:
        if value == cell:
            return False
    for cell in board[:, column]:
        if value == cell:
            return False

    squareRow = 3*(row//3)
    squareColumn = 3*(column//3)
    flat = board[squareRow:squareRow+3, squareColumn:squareColumn+3].flatten()
    for i in flat:
        if value == cell:
            return False
    return True

@njit(fastmath=True)
def solve(board):
    solved = False
    row = column = 0
    while not solved:
        move = False
        for n in range(int(board[row, column])+1,10):
            if isAllowed(board, row, column, n):
                board[row, column] = n
                move = True
                break
        if move:
            if (row, column)==(8,8):
                solved = True
            column = (column+1)%9
            if column == 0:
                row += 1
        else:
            board[row, column] = 0
            column = (column-1)%9
            if column == 8:
                row -=1
for n in range(5):
    board = np.zeros((9,9),int)
    solve(board)

board = np.zeros((9,9),int)
print(board)
print()
t0 = time()
solve(board)
t1 = time()
print(board)
print((t1-t0)*10**6)
