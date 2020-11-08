#include <iostream>
#include <chrono>

void printBoard(int board[][9]){
    for(int row = 0;row<9;row++){
        for(int column = 0;column<9;column++){
            std::cout<<board[row][column]<<" ";
        }
        std::cout<<std::endl;
    }
    std::cout<<std::endl;
}

bool isAllowed(int board[][9], int row, int column, int value){
    for(int cell:board[row]){
        if(cell==value)return false;
    }
    for(int r = 0; r<9; r++){
        if(value==board[r][column]) return false;
    }
    int squareRow = 3*(row/3);
    int squareColumn = 3*(column/3);
    for(int r = 0;r<3;r++){
        for(int c = 0;c<3; c++){
            if(value==board[squareRow+r][squareColumn+c])
                return false;
        }
    }
    return true;
}

void solve(int board[][9]){
    bool solved = false;
    int row = 0;
    int column = 0;
    while(!solved){
        bool move = false;
        for(int n = board[row][column]+1;n<10;n++){
            if(isAllowed(board, row, column, n)){
                board[row][column] = n;
                move = true;
                break;
            }
        }
        if(move){
            if(row == 8 && column == 8) solved = true;
            column = (column+1)%9;
            if(column == 0) row += 1;
        }
        else{
            board[row][column] = 0;
            column = (column-1)%9;
            if(column == 0) row -= 1;
        }
    }
    
}
int main(){
    int board[9][9]={{}};
    printBoard(board);
    auto start = std::chrono::high_resolution_clock::now();
    solve(board);
    auto finish = std::chrono::high_resolution_clock::now();
    std::cout << std::chrono::duration_cast<std::chrono::microseconds>(finish-start).count() << "us\n";
    printBoard(board);
    return 0;
}
