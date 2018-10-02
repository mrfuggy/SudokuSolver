package sudokuSolver.sudokuStrategies

import interactive.VerboseLogger
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table

abstract class BaseSudokuStrategy : SudokuStrategy {

    protected var sudokuTable: Table = sudokuSolver.Table.EmptyTable

    private var changeCount = 0

    override fun updateTable(sudokuTable: Table) {
        this.sudokuTable = sudokuTable
    }

    override fun incChange() {
        changeCount++
    }

    override fun printStat(verboseLogger: VerboseLogger) {
        verboseLogger.printStrategyStat(getName(), changeCount)
    }
}
