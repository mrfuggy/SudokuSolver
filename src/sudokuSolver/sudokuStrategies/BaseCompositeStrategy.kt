package sudokuSolver.sudokuStrategies

import interactive.VerboseLogger
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table
import sudokuSolver.reduce

abstract class BaseCompositeStrategy(strategies: List<SudokuStrategy>) : BaseSudokuStrategy() {

    private val innerStrategies = strategies.reduce(CompositeStrategy(), CompositeStrategy::addStrategy)

    override fun getAnyChange() = innerStrategies.getAnyChange(this)

    override fun updateTable(sudokuTable: Table) {
        innerStrategies.updateTable(sudokuTable)
    }

    override fun printStat(verboseLogger: VerboseLogger) {
        super.printStat(verboseLogger)
        innerStrategies.printStat(verboseLogger)
    }
}
