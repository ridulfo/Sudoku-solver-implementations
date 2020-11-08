# Sudoku-solver-implementations
Python vs Numba vs c++: Different implementations of a sudoku solver!

## Performance
|         | Time (microseconds) |
|---------|---------------------|
| java    | 22 (wow)            |
| c++     | 31                  |
| Numba   | 170                 |
| CPython | 4000                |
| pypy    | 18000 (why?)        |

The CPython algorithm was written first. This implementation uses recursion to solve the board.
Running the CPython implementation using pypy actually resulted in longer runtime...
To implement the numba version recursion had to be eliminated as it is not supported. This required changing the algorithm.
The C++ implementation uses the non-recursive algorithm to make it easier to write. To achieve the highest speed the highest optimization had to be used.
Unoptimized C++ took 110 microseconds, while the optimized took only 31.
The java implementation was taken from an exercise I had in school a year ago.