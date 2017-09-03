package sudokuSolver.sudokuStrategies.hiddenSingle

import sudokuSolver.CellChange
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table

class HiddenSingleBoxStrategy : BaseHiddenSingleStrategy(Table::getBoxEnumerator), SudokuStrategy {

    override fun getAnyChange(): CellChange = getAnyChange(this)
}
