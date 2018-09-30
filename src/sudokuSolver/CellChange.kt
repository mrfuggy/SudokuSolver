package sudokuSolver

import interactive.VerboseLogger

interface CellChange {
    fun apply(table: Table): Table
    fun hasChange(): Boolean
    fun printVerboseLog(verboseLogger: VerboseLogger)
}
