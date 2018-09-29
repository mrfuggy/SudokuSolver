package sudokuSolver

class Table {

    private val sudokuTable: List<List<Byte>>
    private val candidates: SudokuCandidates
    private val matrix: SudokuMatrix

    constructor(table: List<List<Byte>>) {
        sudokuTable = table
        matrix = SudokuMatrix(table)
        candidates = SudokuCandidates(matrix)

        if (!validate()) {
            throw IllegalStateException("sudoku invalid")
        }
    }

    private constructor(table: List<List<Byte>>, matrix: SudokuMatrix, candidates: SudokuCandidates) {
        this.matrix = matrix
        this.candidates = candidates
        sudokuTable = table
    }

    private fun validate() = matrix
            .getAllValues()
            .filter { it.value == EmptyCell }
            .any { getCandidates(it.key.rowIndex, it.key.columnIndex).isEmpty() }
            .not()

    private fun getCandidates(rowIndex: Int, columnIndex: Int) = candidates.getValue(Point(rowIndex, columnIndex))

    fun getCellEnumerator() = matrix
            .getAllValues()
            .map { getCell(it) }

    fun getRowEnumerator(rowIndex: Int) = matrix
            .getRowValues(rowIndex)
            .map { getCell(it) }

    fun getColumnEnumerator(columnIndex: Int) = matrix
            .getColumnValues(columnIndex)
            .map { getCell(it) }

    fun getBoxEnumerator(boxIndex: Int) = matrix
            .getBoxValues(boxIndex)
            .map { getCell(it) }

    private fun getCell(it: Map.Entry<Point, Byte>) =
            Cell(it.key, it.value, candidates.getValue(it.key))


    fun hasEmptyCell() = matrix.hasEmptyCell()

    companion object {
        val EmptyTable = Table(listOf())
        const val EmptyCell = 0.toByte()
    }

    fun insert(index: Point, value: Byte): Table {
        val table = sudokuTable.insert(index, value)
        val matrix = matrix.insert(index, value)
        val candidates = candidates.excludeAll(index, value)

        return Table(table, matrix, candidates)
    }

    fun excludeCandidate(index: Point, value: Byte): Table {
        val candidates = candidates.excludeExact(index, value)
        return Table(sudokuTable, matrix, candidates)
    }
}

private fun List<List<Byte>>.insert(index: Point, value: Byte): List<List<Byte>> {
    val table = this.map { it.toMutableList() }.toMutableList()
    table[index.rowIndex][index.columnIndex] = value
    return table
}
