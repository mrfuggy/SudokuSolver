package interactive

import interactive.loader.SudokuConsoleLoader
import interactive.loader.SudokuFileLoader
import interactive.viewer.SudokuConsoleViewer
import sudokuSolver.SudokuSolver

fun main(args: Array<String>) {
    val loader = if (args.any()) {
        SudokuFileLoader(args[0])
    } else {
        SudokuConsoleLoader()
    }
    val sudokuTable = loader.load()

    val sudokuSolver = SudokuSolver(sudokuTable)
    sudokuSolver.solve()

    val viewer: SudokuViewer = SudokuConsoleViewer()
    viewer.view(sudokuSolver.getTable())
}
