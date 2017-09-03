package sudokuSolver

class Table {

    val SudokuTable: List<List<Byte>>
    private val Candidates: Map<Point, Set<Byte>>
    private val Matrix: Map<Point, Byte>

    private val EmptyCell = 0.toByte()

    constructor(table: List<List<Byte>>) {
        SudokuTable = table
        Candidates = fillCandidates()
        Matrix = table.getMatrix()

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
                .any { getCandidates(it.key.i, it.key.j).isEmpty() }
                .not()

    private fun getCandidates(i: Int, j: Int) = Candidates.getValue(Point(i, j))

    private fun fillCandidates(): Map<Point, Set<Byte>> {
        TODO("not implemented")
    }

    fun getCellEnumerator() =
            Matrix
                    .asIterable()
                    .map { Cell(it.key, it.value, Candidates[it.key].orEmpty()) }


    fun hasEmptyCell() = SudokuTable
                .flatten()
                .any { it == EmptyCell }

    companion object {
        fun empty() = Table(listOf())
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

private fun Map<Point, Set<Byte>>.excludeAll(index: Point, value: Byte) = this
        .excludeInColumn(index, value)
        .excludeInRow(index, value)
        .excludeInBox(index, value)

private fun Map<Point, Set<Byte>>.excludeInRow(index: Point, value: Byte) = this
        .excludeIn(index.i, value) { it.i }

private fun Map<Point, Set<Byte>>.excludeInColumn(index: Point, value: Byte) = this
        .excludeIn(index.i, value) { it.j }

private fun Map<Point, Set<Byte>>.excludeInBox(index: Point, value: Byte) = this
        .excludeIn(index.getBoxNumber(), value) { it.getBoxNumber() }

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
                it.key.i == index.i && it.key.j == index.j -> it.value.filter { it != value }.toSet()
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
    table[index.i][index.j] = value
    return table
}

private fun <E> Collection<Collection<E>>.getMatrix(): Map<Point, E> {
    return this
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
}
