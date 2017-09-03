package sudokuSolver.cellChanges

import sudokuSolver.CellChange
import sudokuSolver.Point
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table

class ExcludeChange(private val index: Point, private val value: Byte, private val strategy: SudokuStrategy) : CellChange {
    override fun apply(table: Table): Table {
        strategy.incChange()
        return table.excludeCandidate(index, value)
    }

    override fun hasChange() = true
}
