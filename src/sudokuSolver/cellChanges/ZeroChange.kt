package sudokuSolver.cellChanges

import interactive.VerboseLogger
import sudokuSolver.CellChange
import sudokuSolver.Table

object ZeroChange : CellChange {
    override fun apply(table: Table) = table

    override fun hasChange() = false

    override fun printVerboseLog(verboseLogger: VerboseLogger) {}
}
