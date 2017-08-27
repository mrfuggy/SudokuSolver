package sudokuSolver.cellChanges

import sudokuSolver.CellChange
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table

class ExcludeChange(private val strategy: SudokuStrategy) : CellChange {
    override fun apply(table: Table): Table {
        strategy.incChange()
        TODO("not implemented")
    }

    override fun hasChange() = true
}
