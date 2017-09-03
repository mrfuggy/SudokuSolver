package sudokuSolver.sudokuStrategies.hiddenSingle

import sudokuSolver.*
import sudokuSolver.cellChanges.InsertChange
import sudokuSolver.cellChanges.ZeroChange
import sudokuSolver.sudokuStrategies.BaseSudokuStrategy

open class BaseHiddenSingleStrategy(private val getEnumeration: (Table, Int) -> List<Cell>) : BaseSudokuStrategy() {

    fun getAnyChange(strategy: SudokuStrategy): CellChange {
        for (num in 0..8) {
            for (i in 0..8) {
                var index = Point(0, 0)
                if (getEnumeration(SudokuTable, i)
                        .filter { it.value == Table.EmptyCell }
                        .filter {
                            index = it.index
                            it.value == num.toByte()
                        }
                        .count() == 1) {
                    return InsertChange(index, num.toByte(), strategy)
                }
            }
        }
        return ZeroChange()
    }
}