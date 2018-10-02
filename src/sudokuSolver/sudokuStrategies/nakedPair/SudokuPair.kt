package sudokuSolver.sudokuStrategies.nakedPair

import sudokuSolver.Cell
import sudokuSolver.CellChange
import sudokuSolver.cellChanges.ExcludeChange
import sudokuSolver.cellChanges.ZeroChange

class SudokuPair(
        private val strategy: BaseNakedPairStrategy,
        private val group: List<Cell>,
        startIndex: Int = 0) {

    private var index1 = startIndex - 1
    private var index2 = startIndex - 2
    private var cell1 = Cell.EmptyCell
        get() = getCell(index1)
    private var cell2 = Cell.EmptyCell
        get() = getCell(index2)

    fun getAnyChange(): CellChange {
        //sure that pair found
        val candidates = cell1.candidates
        return candidates
                .map { getAnyChange(it, candidates) }
                .firstOrNull { it.hasChange() }
                ?: ZeroChange()
    }

    fun tryFoundPair(): Boolean {
        while (tryIncIndex(1)) {
            while (tryIncIndex(2)) {
                if (cell1.isPair() && cell2.isPair()) {
                    if (cell1.candidates == cell2.candidates) {
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun getAnyChange(candidate: Byte, candidates: Set<Byte>): CellChange = (0..8)
            .filter { it != index1 && it != index2 }
            .map { group[it] }
            .filter { it.isEmptyCell() }
            .firstOrNull { it.candidates.contains(candidate) }
            ?.let { ExcludeChange(it.index, candidate, strategy, getReason(candidates)) }
            ?: ZeroChange()

    private fun getReason(candidates: Set<Byte>) =
            strategy.getReason(
                    candidates,
                    cell1.index,
                    cell2.index)

    private fun getCell(index: Int) =
            if (index1 != -1) group[index]
            else Cell.EmptyCell

    private fun tryIncIndex(indexNum: Int) =
            when (indexNum) {
                1 -> if (index1 != 7) {
                    index1++
                    index2 = index1
                    true
                } else {
                    false
                }
                2 -> if (index2 != 8) {
                    index2++
                    true
                } else {
                    false
                }
                else -> false
            }

    fun tryGetNextPair() = index1 < 7

    //increase index1 because 3 naked pairs are not real
    fun getNext() = SudokuPair(strategy, group, index1 + 1)
}
