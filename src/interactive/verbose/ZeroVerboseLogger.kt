package interactive.verbose

import interactive.VerboseLogger

class ZeroVerboseLogger : VerboseLogger {
    override fun printChangeLog(changeLog: String, changeReason: String) {}
}
