package sudokuSolver.cellChanges

import sudokuSolver.CellChange
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table

class InsertChange(private val strategy: SudokuStrategy) : CellChange {
    override fun apply(table: Table): Table {
        TODO("not implemented")
    }

    override fun hasChange() = true
}
