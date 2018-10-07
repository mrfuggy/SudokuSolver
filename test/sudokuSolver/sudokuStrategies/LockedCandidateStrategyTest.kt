package sudokuSolver.sudokuStrategies

import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

class LockedCandidateStrategyTest : StrategyTest() {

    private val tableString = "070000652800002010000040000090300401002000700108005020000030000030600009986000030"
    private val table = getTable(tableString)
    private val table2 = getTable("090800470706900100050400000069030200000000000004070850000002080001009002027001090")
    private val tableWithoutChange = getTable("701456389834129657695783421917368245268594713543271896479612538386945172152837964")
    private var lockedCandidateStrategy = LockedCandidateStrategy()

    @BeforeMethod
    fun setup() {
        lockedCandidateStrategy = LockedCandidateStrategy()
    }

    @Test(groups = ["LockedCandidate"])
    fun shouldBeReturnExcludeChange() {
        strategyExcludeTest(lockedCandidateStrategy, table)
    }

    @Test(groups = ["LockedCandidate"])
    fun shouldBeReturnZeroChange() {
        strategyZeroTest(lockedCandidateStrategy, tableWithoutChange)
    }

    @Test(groups = ["LockedCandidate"])
    fun shouldBeRowRightChange() {
        strategyApplyChangeTest(
                lockedCandidateStrategy,
                table,
                tableString,
                "not insert change"
        )
    }

    @Test(groups = ["LockedCandidate", "VerboseOutput"])
    fun shouldRowPrintVerboseLog() {
        strategyPrintVerboseLog(
                lockedCandidateStrategy,
                table,
                "Exclude 8 at [3,4] by Locked Candidate. Candidate 8 contains in box 3 in row 3"
        )
    }

    @Test(groups = ["LockedCandidate", "VerboseOutput"])
    fun shouldColumnPrintVerboseLog() {
        strategyPrintVerboseLog(
                lockedCandidateStrategy,
                table2,
                "Exclude 1 at [4,1] by Locked Candidate. Candidate 1 contains in box 1 in column 1"
        )
    }
}
