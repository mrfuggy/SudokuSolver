package sudokuSolver.sudokuStrategies

import sudokuSolver.Table

open class BaseSudokuStrategy {

    protected var SudokuTable: Table = sudokuSolver.Table.EmptyTable

    private var ChangeCount = 0

    fun updateTable(sudokuTable: Table) {
        this.SudokuTable = sudokuTable
    }

    fun incChange() {
        ChangeCount++
    }
}