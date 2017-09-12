package sudokuSolver.cellChanges

import sudokuSolver.CellChange
import sudokuSolver.Table
import sudokuSolver.sudokuStrategies.CompositeStrategy

class CompositeChange(private val change: CellChange, private val strategy: CompositeStrategy) : CellChange {
    override fun apply(table: Table): Table {
        strategy.incChange()
        return change.apply(table)
    }

    override fun hasChange() = change.hasChange()
}