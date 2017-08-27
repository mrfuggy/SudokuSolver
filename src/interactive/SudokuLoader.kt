package interactive

import sudokuSolver.Table

interface SudokuLoader {
    fun load(): Table
}