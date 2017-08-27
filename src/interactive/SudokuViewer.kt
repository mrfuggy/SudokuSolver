package interactive

import sudokuSolver.Table

interface SudokuViewer {
    fun view(table: Table)
}