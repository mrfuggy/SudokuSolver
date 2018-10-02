package sudokuSolver

class Table {

    private val sudokuTable: List<List<Byte>>
    private val candidates: SudokuCandidates
    private val matrix: SudokuMatrix

    constructor(table: List<List<Byte>>) {
        sudokuTable = table
        matrix = SudokuMatrix(table)
        candidates = SudokuCandidates(matrix)

        if (!validateCells() || !validateCandidates()) {
            throw IllegalStateException("sudoku invalid")
        }
    }

    private constructor(table: List<List<Byte>>, matrix: SudokuMatrix, candidates: SudokuCandidates) {
        this.matrix = matrix
        this.candidates = candidates
        sudokuTable = table
    }

    private fun validateCandidates() = getCellEnumerator()
            .filter { it.isEmptyCell() }
            .all { it.candidates.isNotEmpty() }

    private fun validateCells(): Boolean {
        val validateRows = validateGroup { getRowEnumerator(it) }
        val validateColumns = validateGroup { getColumnEnumerator(it) }
        val validateBox = validateGroup { getBoxEnumerator(it) }

        return validateRows && validateColumns && validateBox
    }

    private fun validateGroup(getGroup: (Int) -> List<Cell>) = (0..8)
            .map {
                getGroup(it)
                        .forward { validateGroup(it) }
            }
            .all { it }

    private fun validateGroup(group: List<Cell>) =
            group
                    .filter { it.isEmptyCell().not() }
                    .map { it.value }
                    .count() ==
                    group
                            .filter { it.isEmptyCell().not() }
                            .map { it.value }
                            .distinct()
                            .count()

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
