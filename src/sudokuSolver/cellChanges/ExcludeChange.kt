package sudokuSolver.cellChanges

import interactive.VerboseLogger
import sudokuSolver.CellChange
import sudokuSolver.Point
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table

class ExcludeChange(
        private val index: Point,
        private val value: Byte,
        private val strategy: SudokuStrategy,
        private val reason: String) : CellChange {

    override fun apply(table: Table): Table {
        strategy.incChange()
        return table.excludeCandidate(index, value)
    }

    override fun hasChange() = true

    override fun printVerboseLog(verboseLogger: VerboseLogger) {
        verboseLogger.printChangeLog(
                "Exclude $value at $index by ${strategy.getName()}",
                reason
        )
    }
}
