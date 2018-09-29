package sudokuSolver

import sudokuSolver.cellChanges.ZeroChange

class SudokuSolver(private var sudokuTable: Table) {

    private val strategies: MutableList<SudokuStrategy> = mutableListOf()

    fun addStrategy(strategy: SudokuStrategy) {
        strategies.add(strategy)
    }

    fun getTable() = sudokuTable

    fun solve() {
        var changed = false
        while (!changed && sudokuTable.hasEmptyCell()) {
            val change = strategies
                    .map {
                        it.updateTable(sudokuTable)
                        it.getAnyChange()
                    }
                    .firstOrNull { it.hasChange() }
                    ?: ZeroChange()
            changed = change.hasChange()
            sudokuTable = change.apply(sudokuTable)
        }
    }
}
