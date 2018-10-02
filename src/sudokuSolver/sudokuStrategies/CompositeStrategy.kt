package sudokuSolver.sudokuStrategies

import interactive.VerboseLogger
import sudokuSolver.CellChange
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table
import sudokuSolver.cellChanges.CompositeChange
import sudokuSolver.cellChanges.ZeroChange

class CompositeStrategy {
    private val strategies: MutableList<SudokuStrategy> = mutableListOf()

    fun addStrategy(sudokuStrategy: SudokuStrategy): CompositeStrategy {
        strategies.add(sudokuStrategy)
        return this
    }

    fun updateTable(sudokuTable: Table) {
        strategies
                .forEach { it.updateTable(sudokuTable) }
    }

    fun getAnyChange(strategy: BaseCompositeStrategy): CellChange = strategies
            .map { it.getAnyChange() }
            .firstOrNull { it.hasChange() }
            ?.let { CompositeChange(it, strategy) }
            ?: ZeroChange

    fun printStat(verboseLogger: VerboseLogger) {
        verboseLogger.level++
        strategies
                .forEach { it.printStat(verboseLogger) }
        verboseLogger.level--
    }
}
