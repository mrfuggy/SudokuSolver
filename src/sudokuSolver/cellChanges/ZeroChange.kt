package sudokuSolver.cellChanges

import interactive.VerboseLogger
import sudokuSolver.CellChange
import sudokuSolver.Table

class ZeroChange : CellChange {
    override fun apply(table: Table) = table

    override fun hasChange() = false

    override fun printVerboseLog(verboseLogger: VerboseLogger) {}
}
