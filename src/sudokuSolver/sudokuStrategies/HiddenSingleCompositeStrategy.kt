package sudokuSolver.sudokuStrategies

import sudokuSolver.CellChange
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table
import sudokuSolver.sudokuStrategies.hiddenSingle.HiddenSingleBoxStrategy
import sudokuSolver.sudokuStrategies.hiddenSingle.HiddenSingleColumnStrategy
import sudokuSolver.sudokuStrategies.hiddenSingle.HiddenSingleRowStrategy

class HiddenSingleCompositeStrategy : SudokuStrategy {
    override fun incChange() {
        InnerStrategies.incChange()
    }

    override fun updateTable(sudokuTable: Table) {
        InnerStrategies.updateTable(sudokuTable)
    }

    private val InnerStrategies: CompositeStrategy
            = CompositeStrategy()
            .addStrategy(HiddenSingleRowStrategy())
            .addStrategy(HiddenSingleColumnStrategy())
            .addStrategy(HiddenSingleBoxStrategy())

    override fun getAnyChange(): CellChange = InnerStrategies.getAnyChange()
}