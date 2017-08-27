package sudokuSolver.cellChanges

import sudokuSolver.CellChange
import sudokuSolver.Table

class ZeroChange : CellChange {
    override fun apply(table: Table) = table

    override fun hasChange() = false
}
