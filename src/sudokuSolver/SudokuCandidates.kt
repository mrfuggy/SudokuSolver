package sudokuSolver

class SudokuCandidates {

    private val candidates: Map<Point, Set<Byte>>

    constructor(sudokuMatrix: SudokuMatrix) {
        candidates = fillCandidates(sudokuMatrix)
    }

    private constructor(candidates: Map<Point, Set<Byte>>) {
        this.candidates = candidates
    }

    fun getValue(point: Point) = candidates.getOrDefault(point, setOf())

    fun excludeAll(index: Point, value: Byte) = candidates
            .excludeSelfCell(index)
            .excludeInColumn(index, value)
            .excludeInRow(index, value)
            .excludeInBox(index, value)
            .forward { SudokuCandidates(it) }

    fun excludeExact(index: Point, value: Byte) = candidates
            .excludeIn(index, value) { it.getCellIndex() }
            .forward { SudokuCandidates(it) }

    private fun getValues(values: Map<Point, Byte>): Set<Byte> = values
            .map { it.value }
            .filter { it != Cell.EmptyValue }
            .distinct()
            .toSet()

    private fun Map<Point, Set<Byte>>.excludeSelfCell(index: Point) = this
            .mapValues {
                when (index.getCellIndex()) {
                    it.key.getCellIndex() -> setOf()
                    else -> it.value
                }
            }

    private fun Map<Point, Set<Byte>>.excludeInRow(index: Point, value: Byte) = this
            .excludeIn(index, value) { it.rowIndex }

    private fun Map<Point, Set<Byte>>.excludeInColumn(index: Point, value: Byte) = this
            .excludeIn(index, value) { it.columnIndex }

    private fun Map<Point, Set<Byte>>.excludeInBox(index: Point, value: Byte) = this
            .excludeIn(index, value) { it.getBoxIndex() }

    private fun Map<Point, Set<Byte>>.excludeIn(index: Point, value: Byte, intoGroup: (Point) -> Int) = this
            .mapValues {
                when (intoGroup(index)) {
                    intoGroup(it.key) -> it.value.filter { v -> v != value }.toSet()
                    else -> it.value
                }
            }

    private fun fillCandidates(sudokuMatrix: SudokuMatrix) =
            sudokuMatrix
                    .getAllValues()
                    .map { cell ->
                        if (cell.value == Cell.EmptyValue) {
                            Pair(
                                    cell.key,
                                    setOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8, 9)
                                            - sudokuMatrix.getRowValues(cell.key.rowIndex).forward { getValues(it) }
                                            - sudokuMatrix.getColumnValues(cell.key.columnIndex).forward { getValues(it) }
                                            - sudokuMatrix.getBoxValues(cell.key.getBoxIndex()).forward { getValues(it) })
                        } else {
                            Pair(cell.key, setOf())
                        }
                    }
                    .toMap()
}
