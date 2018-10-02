package sudokuSolver.sudokuStrategies

import sudokuSolver.CellChange
import sudokuSolver.Point
import sudokuSolver.SudokuStrategy
import sudokuSolver.cellChanges.InsertChange
import sudokuSolver.cellChanges.ZeroChange

class NakedSingleStrategy : BaseSudokuStrategy(), SudokuStrategy {

    override fun getAnyChange(): CellChange =
            sudokuTable
                    .getCellEnumerator()
                    .firstOrNull { it.candidates.size == 1 }
                    ?.let { InsertChange(it.index, it.candidates.first(), this, getReason(it.index)) }
                    ?: ZeroChange

    private fun getReason(point: Point) = "Only one candidate at $point"

    override fun getName() = "Naked Single"
}
