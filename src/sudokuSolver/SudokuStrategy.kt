package sudokuSolver

interface SudokuStrategy {
    fun updateTable(sudokuTable: Table)
    fun getAnyChange(): CellChange
    fun incChange()
}
