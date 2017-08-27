package sudokuSolver

interface CellChange {
    fun apply(table: Table): Table
    fun hasChange(): Boolean
}
