package sudokuSolver.sudokuStrategies.lockedCandidate

import sudokuSolver.*
import sudokuSolver.cellChanges.ExcludeChange
import sudokuSolver.cellChanges.ZeroChange

class SudokuLockedCandidate(
        private val box: List<Cell>,
        private val sudokuTable: Table,
        private val strategy: SudokuStrategy,
        private val candidate: Byte = 0) {

    private val indexesList = mutableListOf<Point>()

    fun tryLockedCandidate(): Boolean {
        if (containsCandidate())
            return false

        for (cell in box) {
            if (cell.isEmptyCell() && cell.candidates.contains(candidate))
                indexesList.add(cell.index)

            //optimization
            if (indexesList.isNotEmpty()) {
                if (!(isRow() || isColumn()) || indexesList.count() > 3) {
                    return false
                }
            }
        }

        return indexesList.count() > 1 && (isRow() || isColumn())
    }

    private fun containsCandidate(): Boolean = box
            .map { it.value }
            .contains(candidate)

    fun getAnyChange(): CellChange {
        val index = indexesList.first()

        return when {
            isRow() -> getCellChange(index, sudokuTable.getRowEnumerator(index.rowIndex))
            isColumn() -> getCellChange(index, sudokuTable.getColumnEnumerator(index.columnIndex))
            else -> ZeroChange
        }
    }

    private fun getCellChange(index: Point, group: List<Cell>) = group
            .filter { it.isEmptyCell() }
            .filter { it.index.getBoxIndex() != index.getBoxIndex() }
            .firstOrNull { it.candidates.contains(candidate) }
            ?.let { ExcludeChange(it.index, candidate, strategy, getReason(index)) }
            ?: ZeroChange

    fun tryGetNext() = candidate <= 9

    fun getNext() = SudokuLockedCandidate(box, sudokuTable, strategy, (candidate + 1).toByte())

    private fun isColumn() = isGroup(Point::columnIndex)

    private fun isRow() = isGroup(Point::rowIndex)

    private fun isGroup(indexSelector: (Point) -> Int) = indexesList
            .map { indexSelector(it) }
            .distinct()
            .count() == 1

    private fun getReason(index: Point) =
            "Candidate $candidate contains in box ${index.getBoxIndex() + 1} in ${getGroupName()}"

    private fun getGroupName() =
            when {
                isRow() -> "row ${indexesList.first().rowIndex + 1}"
                isColumn() -> "column ${indexesList.first().columnIndex + 1}"
                else -> ""
            }
}
