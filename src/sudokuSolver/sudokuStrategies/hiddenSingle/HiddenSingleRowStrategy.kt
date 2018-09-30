package sudokuSolver.sudokuStrategies.hiddenSingle

import sudokuSolver.Point
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table

class HiddenSingleRowStrategy : BaseHiddenSingleStrategy(Table::getRowEnumerator), SudokuStrategy {

    override fun getAnyChange() = getAnyChange(this)

    override fun getName() = "Hidden Single in row"

    override fun getReason(num: Byte, point: Point) = "$num only one in row ${point.rowIndex + 1}"
}
