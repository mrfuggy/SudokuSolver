package sudokuSolver

class SudokuSolver(private var sudokuTable: Table) {

    private val Strategies: MutableList<SudokuStrategy> = mutableListOf()

    fun addStrategy(strategy: SudokuStrategy) {
        Strategies.add(strategy)
    }

    fun getTable() = sudokuTable

    fun Solve() {
        var changed = false
        while (!changed) {
            changed = false
            for (strategy in Strategies) {
                strategy.updateTable(sudokuTable)
                val change = strategy.getAnyChange()
                if (change.hasChange()) {
                    sudokuTable = change.apply(sudokuTable)
                    changed = true
                    break
                }
            }
        }
    }
}
