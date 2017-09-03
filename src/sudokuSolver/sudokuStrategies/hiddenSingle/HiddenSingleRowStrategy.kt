package sudokuSolver.sudokuStrategies.hiddenSingle

import sudokuSolver.CellChange
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table

class HiddenSingleRowStrategy : BaseHiddenSingleStrategy(Table::getRowEnumerator), SudokuStrategy {

    override fun getAnyChange(): CellChange = getAnyChange(this)
}
