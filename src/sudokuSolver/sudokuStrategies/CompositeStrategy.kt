package sudokuSolver.sudokuStrategies

import sudokuSolver.CellChange
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table

class CompositeStrategy : SudokuStrategy {

    private lateinit var table: Table
    private val Strategies: MutableList<SudokuStrategy> = mutableListOf()
    private var changeCount = 0

    override fun updateTable(sudokuTable: Table) {
        table = sudokuTable
    }

    fun addStrategy(sudokuStrategy: SudokuStrategy): CompositeStrategy {
        Strategies.add(sudokuStrategy)
        return this
    }

    override fun incChange() {
        changeCount++
    }

    override fun getAnyChange(): CellChange {
        TODO("not implemented")
    }
}
