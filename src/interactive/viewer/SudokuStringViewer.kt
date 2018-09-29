package interactive.viewer

import interactive.SudokuViewer
import sudokuSolver.Table

class SudokuStringViewer : SudokuViewer {

    private var str = ""

    override fun view(table: Table) {
        str = table
                .getCellEnumerator()
                .joinToString("") { it.value.toString() }
    }

    fun getString() = str
}
