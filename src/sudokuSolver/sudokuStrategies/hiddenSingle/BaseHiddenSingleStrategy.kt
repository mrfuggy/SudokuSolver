package sudokuSolver.sudokuStrategies.hiddenSingle

import sudokuSolver.Cell
import sudokuSolver.CellChange
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table
import sudokuSolver.cellChanges.InsertChange
import sudokuSolver.cellChanges.ZeroChange
import sudokuSolver.sudokuStrategies.BaseSudokuStrategy

open class BaseHiddenSingleStrategy(private val getEnumeration: (Table, Int) -> List<Cell>) : BaseSudokuStrategy() {

    fun getAnyChange(strategy: SudokuStrategy): CellChange {
        for (num in 0..8) {
            for (i in 0..8) {
                val hiddenVariants = getEnumeration(sudokuTable, i)
                        .filter { it.value == Table.EmptyCell }
                        .filter { it.candidates.contains(num.toByte()) }
                        .map { it.index }
                if (hiddenVariants.count() == 1) {
                    return InsertChange(hiddenVariants.first(), num.toByte(), strategy)
                }
            }
        }
        return ZeroChange()
    }
}
