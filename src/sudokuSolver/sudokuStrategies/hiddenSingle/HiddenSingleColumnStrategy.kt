package sudokuSolver.sudokuStrategies.hiddenSingle

import sudokuSolver.Point
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table

class HiddenSingleColumnStrategy : BaseHiddenSingleStrategy(Table::getColumnEnumerator), SudokuStrategy {

    override fun getAnyChange() = getAnyChange(this)

    override fun getName() = "Hidden Single in column"

    override fun getReason(num: Byte, point: Point) = "$num only one in column ${point.columnIndex + 1}"
}
