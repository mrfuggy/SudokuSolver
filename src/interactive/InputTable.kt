package interactive

import interactive.loader.SudokuConsoleLoader
import interactive.loader.SudokuFileLoader
import interactive.verbose.ConsoleVerboseLogger
import interactive.verbose.ZeroVerboseLogger
import interactive.viewer.SudokuConsoleViewer
import sudokuSolver.SolverParameters
import sudokuSolver.SudokuSolver
import sudokuSolver.sudokuStrategies.HiddenSingleCompositeStrategy
import sudokuSolver.sudokuStrategies.LockedCandidateStrategy
import sudokuSolver.sudokuStrategies.NakedPairCompositeStrategy
import sudokuSolver.sudokuStrategies.NakedSingleStrategy

fun main(args: Array<String>) {
    val loader =
            if (args.any()) SudokuFileLoader(args[0])
            else SudokuConsoleLoader()

    val sudokuTable = loader.load()
    val solverParameters = SolverParameters(
            verboseOutput = true,
            moreVerboseOutput = true)
    val verboseLogger = getVerboseLogger(solverParameters)

    val sudokuSolver = SudokuSolver(sudokuTable, verboseLogger)
    sudokuSolver.addStrategy(NakedSingleStrategy())
    sudokuSolver.addStrategy(HiddenSingleCompositeStrategy())
    sudokuSolver.addStrategy(LockedCandidateStrategy())
    sudokuSolver.addStrategy(NakedPairCompositeStrategy())

    sudokuSolver.solve()

    val viewer: SudokuViewer = SudokuConsoleViewer()
    viewer.view(sudokuSolver.getTable())
}

fun getVerboseLogger(solverParameters: SolverParameters) =
        if (solverParameters.verboseOutput) ConsoleVerboseLogger(solverParameters)
        else ZeroVerboseLogger()
