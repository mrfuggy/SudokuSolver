package sudokuSolver

class Table {

    private val SudokuTable: List<List<Byte>>
    private val Candidates: SudokuCandidates
    private val Matrix: SudokuMatrix

    constructor(table: List<List<Byte>>) {
        SudokuTable = table
        Matrix = SudokuMatrix(table)
        Candidates = SudokuCandidates(Matrix)

        if (!validate()) {
            throw IllegalStateException("sudoku invalid")
        }
    }

    private constructor(table: List<List<Byte>>, matrix: SudokuMatrix, candidates: SudokuCandidates) {
        Matrix = matrix
        Candidates = candidates
        SudokuTable = table
    }

    private fun validate() = Matrix
            .getAllValues()
            .filter { it.value == EmptyCell }
            .any { getCandidates(it.key.rowIndex, it.key.columnIndex).isEmpty() }
            .not()

    private fun getCandidates(rowIndex: Int, columnIndex: Int) = Candidates.getValue(Point(rowIndex, columnIndex))

    fun getCellEnumerator() = Matrix
            .getAllValues()
            .map { getCell(it) }

    fun getRowEnumerator(rowIndex: Int) = Matrix
            .getRowValues(rowIndex)
            .map { getCell(it) }

    fun getColumnEnumerator(columnIndex: Int) = Matrix
            .getColumnValues(columnIndex)
            .map { getCell(it) }

    fun getBoxEnumerator(boxIndex: Int) = Matrix
            .getBoxValues(boxIndex)
            .map { getCell(it) }

    private fun getCell(it: Map.Entry<Point, Byte>) =
            Cell(it.key, it.value, Candidates.getValue(it.key))


    fun hasEmptyCell() = Matrix.hasEmptyCell()

    companion object {
        val EmptyTable = Table(listOf())
        val EmptyCell = 0.toByte()
    }

    fun insert(index: Point, value: Byte): Table {
        val table = SudokuTable.insert(index, value)
        val matrix = Matrix.insert(index, value)
        val candidates = Candidates.excludeAll(index, value)

        return Table(table, matrix, candidates)
    }

    fun excludeCandidate(index: Point, value: Byte): Table {
        val candidates = Candidates.excludeExact(index, value)
        return Table(SudokuTable, Matrix, candidates)
    }
}

private fun List<List<Byte>>.insert(index: Point, value: Byte): List<List<Byte>> {
    val table = this.map { it.toMutableList() }.toMutableList()
    table[index.rowIndex][index.columnIndex] = value
    return table
}
