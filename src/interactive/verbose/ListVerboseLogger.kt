package interactive.verbose

import interactive.VerboseLogger
import sudokuSolver.SolverParameters

class ListVerboseLogger(
        private val solverParameters: SolverParameters,
        private val log: MutableList<String> = mutableListOf()) : VerboseLogger {

    override var level = 0

    override fun printChangeLog(changeLog: String, changeReason: String) {
        if (solverParameters.verboseOutput) {
            if (solverParameters.moreVerboseOutput) addString("$changeLog. $changeReason")
            else addString(changeLog)
        }
    }

    override fun printStrategyStat(strategyName: String, countChanges: Int) {
        addString("${" ".repeat(level * 2)}$strategyName: $countChanges")
    }

    fun getLog() = log.toList()

    private fun addString(string: String) {
        if (string.isNotEmpty())
            log.add(string)
    }
}
