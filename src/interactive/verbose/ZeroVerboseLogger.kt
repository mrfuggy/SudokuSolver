package interactive.verbose

import interactive.VerboseLogger

class ZeroVerboseLogger : VerboseLogger {
    override var level: Int = 0
    override fun printChangeLog(changeLog: String, changeReason: String) {}
    override fun printStrategyStat(strategyName: String, countChanges: Int) {}
}
