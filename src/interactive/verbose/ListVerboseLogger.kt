package interactive.verbose

import interactive.VerboseLogger
import sudokuSolver.SolverParameters

class ListVerboseLogger(
        private val solverParameters: SolverParameters,
        private val log: MutableList<String> = mutableListOf()) : VerboseLogger {

    override fun printChangeLog(changeLog: String, changeReason: String) {
        if (solverParameters.verboseOutput) {
            if (solverParameters.moreVerboseOutput) addString("$changeLog. $changeReason")
            else addString(changeLog)
        }
    }

    private fun addString(string: String) {
        if (string.isNotEmpty())
            log.add(string)
    }

    fun getLog() = log.toList()
}
