package sudokuSolver.sudokuStrategies

import sudokuSolver.CellChange
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table
import sudokuSolver.cellChanges.CompositeChange
import sudokuSolver.cellChanges.ZeroChange

class CompositeStrategy : BaseSudokuStrategy(), SudokuStrategy {
    private val Strategies: MutableList<SudokuStrategy> = mutableListOf()

    fun addStrategy(sudokuStrategy: SudokuStrategy): CompositeStrategy {
        Strategies.add(sudokuStrategy)
        return this
    }

    override fun updateTable(sudokuTable: Table) {
        Strategies
                .forEach { it.updateTable(sudokuTable) }
    }

    override fun getAnyChange(): CellChange {
        return Strategies
                .map { it.getAnyChange() }
                .firstOrNull { it.hasChange() }
                ?.let { CompositeChange(it, this) }
                ?: ZeroChange()
    }
}

