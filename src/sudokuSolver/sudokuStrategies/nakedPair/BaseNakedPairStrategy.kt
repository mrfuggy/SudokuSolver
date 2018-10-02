package sudokuSolver.sudokuStrategies.nakedPair

import sudokuSolver.*
import sudokuSolver.cellChanges.ZeroChange
import sudokuSolver.sudokuStrategies.BaseSudokuStrategy

abstract class BaseNakedPairStrategy(private val getEnumeration: (Table, Int) -> List<Cell>) : BaseSudokuStrategy(),
        SudokuStrategy {

    protected fun getAnyChange(strategy: BaseNakedPairStrategy): CellChange {
        for (i in 0..8) {
            val group = getEnumeration(sudokuTable, i)

            var sudokuPair = SudokuPair(strategy, group)
            while (sudokuPair.tryGetNextPair()) {
                sudokuPair = sudokuPair.getNext()
                val found = sudokuPair.tryFoundPair()
                if (found) {
                    val change = sudokuPair.getAnyChange()
                    if (change.hasChange())
                        return change
                }
            }
        }

        return ZeroChange
    }

    abstract fun getReason(pair: Set<Byte>, point1: Point, point2: Point): String
}
