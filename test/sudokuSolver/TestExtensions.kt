package sudokuSolver

import interactive.loader.SudokuStringLoader

class TestExtensions {
    companion object {
        fun getTable(str: String) = Table(SudokuStringLoader(str, 81)
                .readList()
                .asSequence()
                .batch(9)
                .toList())
    }
}