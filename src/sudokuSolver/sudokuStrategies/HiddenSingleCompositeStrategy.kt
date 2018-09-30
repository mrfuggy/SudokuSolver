package sudokuSolver.sudokuStrategies

import sudokuSolver.SudokuStrategy
import sudokuSolver.Table
import sudokuSolver.sudokuStrategies.hiddenSingle.HiddenSingleBoxStrategy
import sudokuSolver.sudokuStrategies.hiddenSingle.HiddenSingleColumnStrategy
import sudokuSolver.sudokuStrategies.hiddenSingle.HiddenSingleRowStrategy

class HiddenSingleCompositeStrategy : SudokuStrategy {
    override fun incChange() {
        innerStrategies.incChange()
    }

    override fun updateTable(sudokuTable: Table) {
        innerStrategies.updateTable(sudokuTable)
    }

    private val innerStrategies: CompositeStrategy = CompositeStrategy()
            .addStrategy(HiddenSingleRowStrategy())
            .addStrategy(HiddenSingleColumnStrategy())
            .addStrategy(HiddenSingleBoxStrategy())

    override fun getAnyChange() = innerStrategies.getAnyChange()

    override fun getName() = "Hidden Single"
}
