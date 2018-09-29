package sudokuSolver.sudokuStrategies

import sudokuSolver.Table

open class BaseSudokuStrategy {

    protected var sudokuTable: Table = sudokuSolver.Table.EmptyTable

    private var changeCount = 0

    open fun updateTable(sudokuTable: Table) {
        this.sudokuTable = sudokuTable
    }

    fun incChange() {
        changeCount++
    }
}
