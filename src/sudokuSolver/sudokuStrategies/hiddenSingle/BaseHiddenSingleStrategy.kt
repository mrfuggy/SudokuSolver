package sudokuSolver.sudokuStrategies.hiddenSingle

import sudokuSolver.*
import sudokuSolver.cellChanges.InsertChange
import sudokuSolver.cellChanges.ZeroChange
import sudokuSolver.sudokuStrategies.BaseSudokuStrategy

abstract class BaseHiddenSingleStrategy(private val getEnumeration: (Table, Int) -> List<Cell>) : BaseSudokuStrategy(),
        SudokuStrategy {

    protected fun getAnyChange(strategy: BaseHiddenSingleStrategy): CellChange {
        for (num in 1..9) {
            for (i in 0..8) {
                val hiddenVariants = getEnumeration(sudokuTable, i)
                        .filter { it.isEmptyCell() }
                        .filter { it.candidates.contains(num.toByte()) }
                        .map { it.index }

                if (hiddenVariants.count() == 1) {
                    return InsertChange(
                            hiddenVariants.first(),
                            num.toByte(),
                            strategy,
                            strategy.getReason(num.toByte(), hiddenVariants.first()))
                }
            }
        }
        return ZeroChange
    }

    abstract fun getReason(num: Byte, point: Point): String
}
