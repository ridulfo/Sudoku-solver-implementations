from time import sleep, time
from random import randint
# Benchmark 4ms
class Board:

    def __init__(self):
        self.board = []
        for row in range(9):
            self.board.append([0]*9)

    def __str__(self):
        return "\n".join([str(row) for row in self.board])

    def isAllowed(self, position, value):
        row, column = position
        # Check row
        if value in self.board[row]:
            return False
        # Check column
        if value in [row[column] for row in self.board]:
            return False
        # Check square
        #(8, 4)
        squareRow = row//3
        squareColumn = column//3
        for x in range(3):
            for y in range(3):
                if value == self.board[3*squareRow+y][3*squareColumn+x]:
                    return False
        return True

    def solve(self):
       self._solve((0,0))

    def _solve(self, position):
        if position == (8,8): # Exit-cluase
            return True

        if self.board[position[0]][position[1]]!=0:
            nextColumn = (position[1]+1)%9
            nextRow = position[0]  
            if nextColumn == 0:
                nextRow += 1

            return self._solve((nextRow, nextColumn)) # Go to the next square

        for n in range(1,10): # Loop all the posible values
            if self.isAllowed(position, n): # Check if the value is allowed
                self.board[position[0]][position[1]] = n # In that case set that value
                nextColumn = (position[1]+1)%9
                nextRow = position[0]  
                if nextColumn == 0:
                    nextRow += 1
                if self._solve((nextRow, nextColumn)): # Go to the next square
                    return True
                self.board[position[0]][position[1]] = 0 # If the next square did not find a value, reset and try another value in this square.
        return False
b = Board()
for n in range(4):
    b.board[randint(2,8)][randint(2,8)] = randint(2,8)
b.solve()

b = Board()
print(b)
print()
t0 = time()
b.solve()
t1 = time()
print(b)
print((t1-t0)*10**6)
