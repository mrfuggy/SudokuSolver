package interactive.viewer

import interactive.SudokuViewer
import sudokuSolver.Table

class SudokuConsoleViewer : SudokuViewer {
    override fun view(table: Table) {
        for (rowIndex in 0..8) {
            table.getRowEnumerator(rowIndex)
                    .sortedBy { it.index.columnIndex }
                    .forEach { (index, value) ->
                        print(value)
                        if (index.columnIndex == 2 || index.columnIndex == 5) {
                            print("|")
                        }
                    }
            if (rowIndex == 2 || rowIndex == 5) {
                print("---+---+---")
            }
        }
    }
}
