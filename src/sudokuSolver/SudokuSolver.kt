package sudokuSolver

class SudokuSolver(private var sudokuTable: Table) {

    private val Strategies: MutableList<SudokuStrategy> = mutableListOf()

    fun addStrategy(strategy: SudokuStrategy) {
        //strategy.UpdateTable(sudokuTable)
        Strategies.add(strategy)
    }

    fun getTable() = sudokuTable
    fun Solve() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}