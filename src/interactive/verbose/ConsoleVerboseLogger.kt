package interactive.verbose

import interactive.VerboseLogger
import sudokuSolver.SolverParameters

class ConsoleVerboseLogger(private val solverParameters: SolverParameters) : VerboseLogger {
    override fun printChangeLog(changeLog: String, changeReason: String) {
        if (solverParameters.verboseOutput) {
            if (solverParameters.moreVerboseOutput) printString("$changeLog. $changeReason")
            else printString(changeLog)
        }
    }

    private fun printString(string: String) {
        if (string.isNotEmpty())
            print(string)
    }
}
