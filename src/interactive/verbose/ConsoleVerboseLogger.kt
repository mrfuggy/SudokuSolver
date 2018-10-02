package interactive.verbose

import interactive.VerboseLogger
import sudokuSolver.SolverParameters

class ConsoleVerboseLogger(private val solverParameters: SolverParameters) : VerboseLogger {

    override var level = 0

    override fun printChangeLog(changeLog: String, changeReason: String) {
        if (solverParameters.verboseOutput) {
            if (solverParameters.moreVerboseOutput) printString("$changeLog. $changeReason")
            else printString(changeLog)
        }
    }

    override fun printStrategyStat(strategyName: String, countChanges: Int) {
        printString("${" ".repeat(level * 2)}$strategyName: $countChanges")
    }

    private fun printString(string: String) {
        if (string.isNotEmpty())
            print(string)
    }
}
