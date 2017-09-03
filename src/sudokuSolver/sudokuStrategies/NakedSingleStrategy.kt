package sudokuSolver.sudokuStrategies

import sudokuSolver.CellChange
import sudokuSolver.SudokuStrategy
import sudokuSolver.cellChanges.InsertChange
import sudokuSolver.cellChanges.ZeroChange

class NakedSingleStrategy : BaseSudokuStrategy(), SudokuStrategy {

    override fun getAnyChange(): CellChange =
            SudokuTable
                    .getCellEnumerator()
                    .firstOrNull { it.candidates.size == 1 }
                    ?.let { InsertChange(it.index, it.candidates.first(), this) }
                    ?: ZeroChange()
}