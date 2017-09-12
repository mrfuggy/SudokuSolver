package sudokuSolver

class SudokuMatrix {
    private val matrix: Map<Point, Byte>

    constructor(table: List<List<Byte>>) {
        matrix = getMatrix(table)
    }

    private constructor(matrix: Map<Point, Byte>) {
        this.matrix = matrix
    }

    fun insert(index: Point, value: Byte) = matrix
            .mapValues {
                when (index) {
                    it.key -> value
                    else -> it.value
                }
            }
            .forward { SudokuMatrix(it) }

    fun hasEmptyCell() = matrix
            .any { it.value == Table.EmptyCell }

    fun getAllValues() = matrix

    fun getRowValues(rowIndex: Int) = matrix
            .filter { it.key.rowIndex == rowIndex }

    fun getColumnValues(columnIndex: Int) = matrix
            .filter { it.key.columnIndex == columnIndex }

    fun getBoxValues(boxIndex: Int) = matrix
            .filter { it.key.getBoxIndex() == boxIndex }

    private fun getMatrix(table: List<List<Byte>>): Map<Point, Byte> = table
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
