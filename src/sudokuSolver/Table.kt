package sudokuSolver

data class Table(val table: List<List<Byte>>) {

    private var Candidates: Map<Point, Set<Byte>>
    private val Matrix: Map<Point, Byte>

    init {
        if (!validate()) {
            throw IllegalStateException("sudoku invalid")
        }
        Candidates = fillCandidates()
        Matrix = table.getMatrix()
    }

    private val EmptyCell = 0.toByte()

    private fun validate(): Boolean {
        return Matrix
                .filter { it.value == EmptyCell }
                .any { getCandidates(it.key.i, it.key.j).isEmpty() }
                .not()
    }

    private fun getCandidates(i: Int, j: Int): Set<Byte> = Candidates.getValue(Point(i, j))

    private fun fillCandidates(): Map<Point, Set<Byte>> {
        TODO("not implemented")
    }

    fun hasEmptyCell(): Boolean {
        return table
                .flatten()
                .any { it == EmptyCell }
    }

    companion object {
        fun empty(): Table = Table(listOf())
    }
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


