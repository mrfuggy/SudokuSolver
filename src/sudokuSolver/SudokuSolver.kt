package sudokuSolver

import sudokuSolver.cellChanges.ZeroChange

class SudokuSolver(private var sudokuTable: Table) {

    private val Strategies: MutableList<SudokuStrategy> = mutableListOf()

    fun addStrategy(strategy: SudokuStrategy) {
        Strategies.add(strategy)
    }

    fun getTable() = sudokuTable

    fun Solve() {
        var changed = false
        while (!changed) {
            val change = Strategies
                    .map {
                        it.updateTable(sudokuTable)
                        it.getAnyChange()
                    }
                    .firstOrDefault({ ZeroChange() }) { it.hasChange() }
            changed = change.hasChange()
            sudokuTable = change.apply(sudokuTable)
        }
    }
}
