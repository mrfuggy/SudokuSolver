package interactive.loader

import interactive.SudokuLoader
import sudokuSolver.Table
import java.io.File

class SudokuFileLoader(private val fileName: String) : SudokuLoader {
    override fun load(): Table {
        val file = File(fileName)
        val lines = file.readLines()
        return Table(lines.map { s -> SudokuStringLoader(s).readList() })
    }
}
