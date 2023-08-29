# SudokuSolver 
I did this project a while back in college. It's a simple Java program that solves sudoku.

It basically runs from App.java, creates a Puzzle object from gridSquares objects and uses simple strategies to solve sudoku.
Each square gets a list of possible values after looking down rows, columns, and the 3x3 square it's in, and if there's only 1 value, it inputs it and updates any squares that can see the value.
Otherwise, it brute forces a solution by guessing and checking.
Horribly unoptimized, but it's a proof of concept for myself.
