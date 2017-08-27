package interactive.loader

import interactive.SudokuLoader
import sudokuSolver.Table

class SudokuConsoleLoader : SudokuLoader {

    override fun load(): Table {
        println("Enter the Sudoku table line by line")
        val lists = List(9) { _ -> SudokuStringLoader(readLine()).readList() }
        return Table(lists)
    }
}