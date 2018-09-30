package sudokuSolver.sudokuStrategies

import sudokuSolver.CellChange
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table
import sudokuSolver.cellChanges.CompositeChange
import sudokuSolver.cellChanges.ZeroChange

class CompositeStrategy : BaseSudokuStrategy(), SudokuStrategy {
    private val strategies: MutableList<SudokuStrategy> = mutableListOf()

    fun addStrategy(sudokuStrategy: SudokuStrategy): CompositeStrategy {
        strategies.add(sudokuStrategy)
        return this
    }

    override fun updateTable(sudokuTable: Table) {
        strategies
                .forEach { it.updateTable(sudokuTable) }
    }

    override fun getAnyChange(): CellChange {
        return strategies
                .map { it.getAnyChange() }
                .firstOrNull { it.hasChange() }
                ?.let { CompositeChange(it, this) }
                ?: ZeroChange()
    }

    override fun getName() = "Composite"
}
