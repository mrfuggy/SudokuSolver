package interactive

interface VerboseLogger {
    var level: Int
    fun printChangeLog(changeLog: String, changeReason: String)
    fun printStrategyStat(strategyName: String, countChanges: Int)
}
