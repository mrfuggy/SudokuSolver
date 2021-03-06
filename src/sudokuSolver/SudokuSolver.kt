package sudokuSolver

import interactive.VerboseLogger
import sudokuSolver.cellChanges.ZeroChange

class SudokuSolver(
        private var sudokuTable: Table,
        private val verboseLogger: VerboseLogger) {

    private val strategies: MutableList<SudokuStrategy> = mutableListOf()

    private var changeCount = 0

    fun addStrategy(strategy: SudokuStrategy) {
        strategies.add(strategy)
    }

    fun getTable() = sudokuTable

    fun solve() {
        var changed = false
        while (!changed && sudokuTable.hasEmptyCell()) {
            val change = getAnyChange()
            changed = change.hasChange()
            change.printVerboseLog(verboseLogger)
            applyChange(change)
        }
    }

    fun applyChange(change: CellChange) {
        if (change.hasChange())
            changeCount++
        sudokuTable = change.apply(sudokuTable)
    }

    fun getAnyChange(): CellChange = strategies
            .map {
                it.updateTable(sudokuTable)
                it.getAnyChange()
            }
            .firstOrNull { it.hasChange() }
            ?: ZeroChange

    fun printStat() {
        strategies
                .forEach { it.printStat(verboseLogger) }
        verboseLogger.printStrategyStat("Total", changeCount)
    }
}
