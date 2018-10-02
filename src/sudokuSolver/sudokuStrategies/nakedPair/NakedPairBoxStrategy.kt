package sudokuSolver.sudokuStrategies.nakedPair

import sudokuSolver.Point
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table

class NakedPairBoxStrategy : BaseNakedPairStrategy(Table::getBoxEnumerator), SudokuStrategy {

    override fun getAnyChange() = getAnyChange(this)

    override fun getName() = "Naked Pair in box"

    override fun getReason(pair: Set<Byte>, point1: Point, point2: Point) =
            "Pair (${pair.joinToString(separator = ",")}) found in box ${point1.getBoxIndex() + 1} at $point1 and $point2"
}
