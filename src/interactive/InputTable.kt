package interactive

import sudokuSolver.SudokuSolver

fun main(args: Array<String>) {
    val loader = if (args.any()) {
        SudokuFileLoader(args[0])
    } else {
        SudokuConsoleLoader()
    }
    val sudokuTable = loader.load()
    val sudokuSolver = SudokuSolver(sudokuTable)
}