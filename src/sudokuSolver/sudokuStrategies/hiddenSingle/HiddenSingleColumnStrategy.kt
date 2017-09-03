package sudokuSolver.sudokuStrategies.hiddenSingle

import sudokuSolver.CellChange
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table

class HiddenSingleColumnStrategy : BaseHiddenSingleStrategy(Table::getColumnEnumerator), SudokuStrategy {

    override fun getAnyChange(): CellChange = getAnyChange(this)
}
