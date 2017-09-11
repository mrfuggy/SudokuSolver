package interactive.loader

import com.sun.javaws.exceptions.InvalidArgumentException
import interactive.SudokuLoader
import sudokuSolver.Table

class SudokuConsoleLoader : SudokuLoader {

    override fun load(): Table {
        println("Enter the Sudoku table line by line")
        val lists = List(9) { _ -> repeatRead() }
        return Table(lists)
    }

    private fun repeatRead(): List<Byte> {
        while (true) {
            try {
                return SudokuStringLoader(readLine(), 9).readList()
            } catch (ex: InvalidArgumentException) {
                println(ex.message)
            } catch (ex: NumberFormatException) {
                println(ex.message)
            }
        }
    }
}