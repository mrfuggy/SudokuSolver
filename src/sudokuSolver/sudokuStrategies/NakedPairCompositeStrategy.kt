package sudokuSolver.sudokuStrategies

import sudokuSolver.SudokuStrategy
import sudokuSolver.Table
import sudokuSolver.sudokuStrategies.nakedPair.NakedPairBoxStrategy
import sudokuSolver.sudokuStrategies.nakedPair.NakedPairColumnStrategy
import sudokuSolver.sudokuStrategies.nakedPair.NakedPairRowStrategy

class NakedPairCompositeStrategy : SudokuStrategy {
    override fun incChange() {
        innerStrategies.incChange()
    }

    override fun updateTable(sudokuTable: Table) {
        innerStrategies.updateTable(sudokuTable)
    }

    private val innerStrategies: CompositeStrategy = CompositeStrategy()
            .addStrategy(NakedPairRowStrategy())
            .addStrategy(NakedPairColumnStrategy())
            .addStrategy(NakedPairBoxStrategy())

    override fun getAnyChange() = innerStrategies.getAnyChange()

    override fun getName() = "Naked Pair"
}
