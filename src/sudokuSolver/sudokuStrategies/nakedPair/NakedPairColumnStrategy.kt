package sudokuSolver.sudokuStrategies.nakedPair

import sudokuSolver.Point
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table

class NakedPairColumnStrategy : BaseNakedPairStrategy(Table::getColumnEnumerator), SudokuStrategy {

    override fun getAnyChange() = getAnyChange(this)

    override fun getName() = "Naked Pair in column"

    override fun getReason(pair: Set<Byte>, point1: Point, point2: Point) =
            "Pair (${pair.joinToString(separator = ",")}) found in column ${point1.columnIndex + 1} at $point1 and $point2"
}
