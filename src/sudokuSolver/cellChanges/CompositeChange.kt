package sudokuSolver.cellChanges

import interactive.VerboseLogger
import sudokuSolver.CellChange
import sudokuSolver.Table
import sudokuSolver.sudokuStrategies.BaseCompositeStrategy

class CompositeChange(private val change: CellChange, private val strategy: BaseCompositeStrategy) : CellChange {
    override fun apply(table: Table): Table {
        strategy.incChange()
        return change.apply(table)
    }

    override fun hasChange() = change.hasChange()

    override fun printVerboseLog(verboseLogger: VerboseLogger) {
        change.printVerboseLog(verboseLogger)
    }
}
