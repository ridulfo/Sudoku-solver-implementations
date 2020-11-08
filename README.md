# Sudoku-solver-implementations
Python vs Numba vs c++: Different implementations of a sudoku solver!

## Performance
|         | Time (microseconds) |
|---------|---------------------|
| c++     | 31                  |
| Numba   | 170                 |
| Java    | 225                 |
| CPython | 4000                |
| pypy    | 18000 (why?)        |

The CPython algorithm was written first. This implementation uses recursion to solve the board.
Running the CPython implementation using pypy actually resulted in longer runtime...
To implement the numba version recursion had to be eliminated as it is not supported. This required changing the algorithm.
The C++ implementation uses the non-recursive algorithm to make it easier to write. To achieve the highest speed the highest optimization had to be used.
Unoptimized C++ took 110 microseconds, while the optimized took only 31.
The java implementation was taken from an exercise I had in school a year ago.

#### To run the python implementation, install the requirements using:<br>
`pip install -r requirements.txt`


#### To run the C++ implementation, compile it using:<br>
`g++ ccudoku.cc -o ccudoku -Ofast`

#### To run the C++ implementation, compile it using:<br>
`javac Board.java`
then run with
`java Board`
