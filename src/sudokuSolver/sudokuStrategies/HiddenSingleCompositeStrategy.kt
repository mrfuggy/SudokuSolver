package sudokuSolver.sudokuStrategies

import sudokuSolver.CellChange
import sudokuSolver.SudokuStrategy
import sudokuSolver.sudokuStrategies.hiddenSingle.HiddenSingleBoxStrategy
import sudokuSolver.sudokuStrategies.hiddenSingle.HiddenSingleColumnStrategy
import sudokuSolver.sudokuStrategies.hiddenSingle.HiddenSingleRowStrategy

class HiddenSingleCompositeStrategy : BaseSudokuStrategy(), SudokuStrategy {
    private val InnerStrategies: CompositeStrategy
            = CompositeStrategy()
            .addStrategy(HiddenSingleRowStrategy())
            .addStrategy(HiddenSingleColumnStrategy())
            .addStrategy(HiddenSingleBoxStrategy())

    override fun getAnyChange(): CellChange = InnerStrategies.getAnyChange()
}