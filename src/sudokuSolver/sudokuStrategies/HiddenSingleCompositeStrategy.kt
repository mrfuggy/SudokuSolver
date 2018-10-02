package sudokuSolver.sudokuStrategies

import sudokuSolver.SudokuStrategy
import sudokuSolver.sudokuStrategies.hiddenSingle.HiddenSingleBoxStrategy
import sudokuSolver.sudokuStrategies.hiddenSingle.HiddenSingleColumnStrategy
import sudokuSolver.sudokuStrategies.hiddenSingle.HiddenSingleRowStrategy

class HiddenSingleCompositeStrategy :
        BaseCompositeStrategy(
                listOf(
                        HiddenSingleRowStrategy(),
                        HiddenSingleColumnStrategy(),
                        HiddenSingleBoxStrategy())),
        SudokuStrategy {

    override fun getName() = "Hidden Single"
}
