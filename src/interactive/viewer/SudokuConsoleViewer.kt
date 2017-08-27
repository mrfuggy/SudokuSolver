package interactive.viewer

import interactive.SudokuViewer
import sudokuSolver.Table

class SudokuConsoleViewer : SudokuViewer {
    override fun view(table: Table) {
        table.table.withIndex().forEach { (rowIndex, row) ->
            row.withIndex().forEach { (columnIndex, num) ->
                print(num)
                if (columnIndex == 2 || columnIndex == 5) {
                    print("|")
                }
            }
            if (rowIndex == 2 || rowIndex == 5) {
                print("---+---+---")
            }
        }
    }
}