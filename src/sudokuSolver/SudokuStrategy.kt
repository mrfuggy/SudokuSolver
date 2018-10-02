package sudokuSolver

import interactive.VerboseLogger

interface SudokuStrategy {
    fun updateTable(sudokuTable: Table)
    fun getAnyChange(): CellChange
    fun incChange()
    fun getName(): String
    fun printStat(verboseLogger: VerboseLogger)
}
