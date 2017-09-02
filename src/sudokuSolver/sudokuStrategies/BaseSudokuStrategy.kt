package sudokuSolver.sudokuStrategies

import sudokuSolver.Table

open class BaseSudokuStrategy {

    protected var Table: Table = sudokuSolver.Table.empty()

    private var ChangeCount = 0

    fun updateTable(sudokuTable: Table) {
        Table = sudokuTable
    }

    fun incChange() {
        ChangeCount++
    }
}