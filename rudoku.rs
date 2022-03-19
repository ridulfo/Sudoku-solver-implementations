use std::time::Instant;
type Board = [[u8; 9]; 9];

fn is_allowed(board: &Board, row: usize, col: usize, val: u8) -> bool {
    for i in 0..9 {
        if board[row][i] == val {
            return false;
        }
        if board[i][col] == val {
            return false;
        }
    }
    let row_start = row - row % 3;
    let col_start = col - col % 3;
    for i in row_start..row_start + 3 {
        for j in col_start..col_start + 3 {
            if board[i][j] == val {
                return false;
            }
        }
    }
    true
}

fn solve(mut board: Board) -> Board {
    let mut solved = false;
    let mut row: usize = 0;
    let mut column: usize = 0;
    while !solved {
        let mut next = false;
        for n in board[row][column] + 1..10 {
            if is_allowed(&board, row, column, n) {
                board[row][column] = n;
                next = true;
                break;
            }
        }
        if next {
            if row == 8 && column == 8 {
                solved = true;
            }
            column = (column + 1) % 9;
            if column == 0 {
                row += 1;
            }
        } else {
            board[row][column] = 0;
            column = (column - 1) % 9;
            if column == 0 {
                row -= 1;
            }
        }
    }
    board
}
fn main() {
    let board = [
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
    ];
    let mut times: Vec<std::time::Duration> = vec![];
    for _ in 0..1000 {
        let now = Instant::now();
        solve(board);
        times.push(now.elapsed());
    }
    times.sort();
    println!("{:?}", times[0]);
}
