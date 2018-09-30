package sudokuSolver.sudokuStrategies.hiddenSingle

import sudokuSolver.Point
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table

class HiddenSingleBoxStrategy : BaseHiddenSingleStrategy(Table::getBoxEnumerator), SudokuStrategy {

    override fun getAnyChange() = getAnyChange(this)

    override fun getName() = "Hidden Single in box"

    override fun getReason(num: Byte, point: Point) = "$num only one in box ${point.getBoxIndex() + 1}"
}
