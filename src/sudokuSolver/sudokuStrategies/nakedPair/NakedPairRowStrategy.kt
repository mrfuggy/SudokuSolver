package sudokuSolver.sudokuStrategies.nakedPair

import sudokuSolver.Point
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table

class NakedPairRowStrategy : BaseNakedPairStrategy(Table::getRowEnumerator), SudokuStrategy {

    override fun getAnyChange() = getAnyChange(this)

    override fun getName() = "Naked Pair in row"

    override fun getReason(pair: Set<Byte>, point1: Point, point2: Point) =
            "Pair (${pair.joinToString(separator = ",")}) found in row ${point1.rowIndex + 1} at $point1 and $point2"
}
