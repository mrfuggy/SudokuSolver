package sudokuSolver.sudokuStrategies

import sudokuSolver.CellChange
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table
import sudokuSolver.cellChanges.CompositeChange
import sudokuSolver.cellChanges.ZeroChange

class CompositeStrategy : SudokuStrategy {

    private lateinit var Table: Table
    private val Strategies: MutableList<SudokuStrategy> = mutableListOf()
    private var ChangeCount = 0

    override fun updateTable(sudokuTable: Table) {
        Table = sudokuTable
    }

    fun addStrategy(sudokuStrategy: SudokuStrategy): CompositeStrategy {
        Strategies.add(sudokuStrategy)
        return this
    }

    override fun incChange() {
        ChangeCount++
    }

    override fun getAnyChange(): CellChange {
        return Strategies
                .map { it.getAnyChange() }
                .firstOrNull { it.hasChange() }
                ?.let { CompositeChange(it, this) }
                ?: ZeroChange()
    }
}

