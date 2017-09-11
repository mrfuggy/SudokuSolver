package sudokuSolver

class Table {

    val SudokuTable: List<List<Byte>>
    private val Candidates: Map<Point, Set<Byte>>
    private val Matrix: Map<Point, Byte>

    constructor(table: List<List<Byte>>) {
        SudokuTable = table
        Matrix = table.getMatrix()
        Candidates = fillCandidates(Matrix)

        if (!validate()) {
            throw IllegalStateException("sudoku invalid")
        }
    }

    private constructor(table: List<List<Byte>>, matrix: Map<Point, Byte>, candidates: Map<Point, Set<Byte>>) {
        Matrix = matrix
        Candidates = candidates
        SudokuTable = table
    }

    private fun validate() = Matrix
            .filter { it.value == EmptyCell }
            .any { getCandidates(it.key.rowIndex, it.key.columnIndex).isEmpty() }
            .not()

    private fun getCandidates(i: Int, j: Int) = Candidates.getValue(Point(i, j))

    private fun fillCandidates(matrix: Map<Point, Byte>): Map<Point, Set<Byte>> =
            matrix
                    .map { cell ->
                        if (cell.value == EmptyCell) {
                            Pair(cell.key, setOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8, 9)
                                    - matrix.getRowValues(cell.key.rowIndex).forward { getValues(it) }
                                    - matrix.getColumnValues(cell.key.columnIndex).forward { getValues(it) }
                                    - matrix.getBoxValues(cell.key.getBoxIndex()).forward { getValues(it) })
                        } else {
                            Pair(cell.key, setOf())
                        }
                    }
                    .toMap()

    private fun getValues(values: Map<Point, Byte>): Set<Byte> = values
            .map { it.value }
            .filter { it != EmptyCell }
            .distinct()
            .toSet()

    fun getCellEnumerator() = Matrix
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
            Cell(it.key, it.value, Candidates[it.key].orEmpty())


    fun hasEmptyCell() = Matrix
            .any { it.value == EmptyCell }

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

private fun Map<Point, Byte>.getBoxValues(boxIndex: Int) = this
        .filter { it.key.getBoxIndex() == boxIndex }

private fun Map<Point, Byte>.getColumnValues(columnIndex: Int) = this
        .filter { it.key.columnIndex == columnIndex }

private fun Map<Point, Byte>.getRowValues(rowIndex: Int) = this
        .filter { it.key.rowIndex == rowIndex }

private fun Map<Point, Set<Byte>>.excludeAll(index: Point, value: Byte) = this
        .excludeInColumn(index, value)
        .excludeInRow(index, value)
        .excludeInBox(index, value)

private fun Map<Point, Set<Byte>>.excludeInRow(index: Point, value: Byte) = this
        .excludeIn(index.rowIndex, value) { it.rowIndex }

private fun Map<Point, Set<Byte>>.excludeInColumn(index: Point, value: Byte) = this
        .excludeIn(index.columnIndex, value) { it.columnIndex }

private fun Map<Point, Set<Byte>>.excludeInBox(index: Point, value: Byte) = this
        .excludeIn(index.getBoxIndex(), value) { it.getBoxIndex() }

private fun Map<Point, Set<Byte>>.excludeIn(index: Int, value: Byte, intoGroup: (Point) -> Int) = this
        .mapValues {
            when (index) {
                intoGroup(it.key) -> it.value.filter { it != value }.toSet()
                else -> it.value
            }
        }

private fun Map<Point, Set<Byte>>.excludeExact(index: Point, value: Byte) = this
        .mapValues {
            when {
                it.key.rowIndex == index.rowIndex && it.key.columnIndex == index.columnIndex -> it.value.filter { it != value }.toSet()
                else -> it.value
            }
        }

private fun Map<Point, Byte>.insert(index: Point, value: Byte) = this
        .mapValues {
            when (index) {
                it.key -> value
                else -> it.value
            }
        }

private fun List<List<Byte>>.insert(index: Point, value: Byte): List<List<Byte>> {
    val table = this.map { it.toMutableList() }.toMutableList()
    table[index.rowIndex][index.columnIndex] = value
    return table
}

private fun <E> Collection<Collection<E>>.getMatrix(): Map<Point, E> = this
        .withIndex()
        .flatMap { (rowIndex, row)
            ->
            row
                    .withIndex()
                    .map { (columnIndex, value)
                        ->
                        Pair(Point(rowIndex, columnIndex), value)
                    }
        }
        .toMap()
