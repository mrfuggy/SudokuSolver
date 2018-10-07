package sudokuSolver.sudokuStrategies

import sudokuSolver.CellChange
import sudokuSolver.SudokuStrategy
import sudokuSolver.cellChanges.ZeroChange
import sudokuSolver.sudokuStrategies.lockedCandidate.SudokuLockedCandidate

class LockedCandidateStrategy : BaseSudokuStrategy(), SudokuStrategy {
    override fun getAnyChange(): CellChange {
        for (i in 0..8) {
            val box = sudokuTable.getBoxEnumerator(i)

            var sudokuLC = SudokuLockedCandidate(box, sudokuTable, this)
            while (sudokuLC.tryGetNext()) {
                sudokuLC = sudokuLC.getNext()
                if (sudokuLC.tryLockedCandidate()) {
                    val change = sudokuLC.getAnyChange()

                    if (change.hasChange()) {
                        return change
                    }
                }
            }
        }

        return ZeroChange
    }

    override fun getName() = "Locked Candidate"
}
