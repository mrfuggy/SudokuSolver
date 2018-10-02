package sudokuSolver.sudokuStrategies

import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import sudokuSolver.sudokuStrategies.nakedPair.NakedPairBoxStrategy
import sudokuSolver.sudokuStrategies.nakedPair.NakedPairColumnStrategy
import sudokuSolver.sudokuStrategies.nakedPair.NakedPairRowStrategy

class NakedPairCompositeStrategyTest : StrategyTest() {

    private val tableString = "652000470014002800070040000401320097705000302020005108000030000009600030037000986"
    private val table = getTable(tableString)
    private val tableWithoutChange = getTable("090800470706900100050400000069030200000000000004070850000002080001009002027001090")
    private var nakedPairStrategy = NakedPairCompositeStrategy()
    private var nakedPairBoxStrategy = NakedPairBoxStrategy()
    private var nakedPairRowStrategy = NakedPairRowStrategy()
    private var nakedPairColumnStrategy = NakedPairColumnStrategy()

    @BeforeMethod
    fun setUp() {
        nakedPairBoxStrategy = NakedPairBoxStrategy()
        nakedPairStrategy = NakedPairCompositeStrategy()
        nakedPairRowStrategy = NakedPairRowStrategy()
        nakedPairColumnStrategy = NakedPairColumnStrategy()
    }

    @Test(groups = ["NakedPair", "NakedPairRow"])
    fun shouldBeRowReturnExcludeChange() {
        strategyExcludeTest(nakedPairRowStrategy, table)
    }

    @Test(groups = ["NakedPair", "NakedPairRow"])
    fun shouldBeRowReturnZeroChange() {
        strategyZeroTest(nakedPairRowStrategy, tableWithoutChange)
    }

    @Test(groups = ["NakedPair", "NakedPairRow"])
    fun shouldBeRowRightChange() {
        strategyApplyChangeTest(
                nakedPairRowStrategy,
                table,
                tableString,
                "not insert change"
        )
    }

    @Test(groups = ["NakedPair", "NakedPairRow", "VerboseOutput"])
    fun shouldRowPrintVerboseLog() {
        strategyPrintVerboseLog(
                nakedPairRowStrategy,
                table,
                "Exclude 6 at [4,7] by Naked Pair in row. Pair (6,8) found in row 4 at [4,2] and [4,6]"
        )
    }

    @Test(groups = ["NakedPair", "NakedPairColumn"])
    fun shouldBeColumnReturnExcludeChange() {
        strategyExcludeTest(nakedPairColumnStrategy, table)
    }

    @Test(groups = ["NakedPair", "NakedPairColumn"])
    fun shouldBeColumnReturnZeroChange() {
        strategyZeroTest(nakedPairColumnStrategy, tableWithoutChange)
    }

    @Test(groups = ["NakedPair", "NakedPairColumn"])
    fun shouldBeColumnRightChange() {
        strategyApplyChangeTest(
                nakedPairColumnStrategy,
                table,
                tableString,
                "not insert change"
        )
    }

    @Test(groups = ["NakedPair", "NakedPairColumn", "VerboseOutput"])
    fun shouldColumnPrintVerboseLog() {
        strategyPrintVerboseLog(
                nakedPairColumnStrategy,
                table,
                "Exclude 3 at [3,1] by Naked Pair in column. Pair (3,9) found in column 1 at [2,1] and [6,1]"
        )
    }

    @Test(groups = ["NakedPair", "NakedPairBox"])
    fun shouldBeBoxReturnExcludeChange() {
        strategyExcludeTest(nakedPairBoxStrategy, table)
    }

    @Test(groups = ["NakedPair", "NakedPairBox"])
    fun shouldBeBoxReturnZeroChange() {
        strategyZeroTest(nakedPairBoxStrategy, tableWithoutChange)
    }

    @Test(groups = ["NakedPair", "NakedPairBox"])
    fun shouldBeBoxRightChange() {
        strategyApplyChangeTest(
                nakedPairBoxStrategy,
                table,
                tableString,
                "not insert change"
        )
    }

    @Test(groups = ["NakedPair", "NakedPairBox", "VerboseOutput"])
    fun shouldBoxPrintVerboseLog() {
        strategyPrintVerboseLog(
                nakedPairBoxStrategy,
                table,
                "Exclude 6 at [4,7] by Naked Pair in box. Pair (4,6) found in box 6 at [5,8] and [6,8]"
        )
    }

    @Test(groups = ["NakedPair"])
    fun shouldBeReturnExcludeChange() {
        strategyCompositeTest(nakedPairStrategy, table)
    }

    @Test(groups = ["NakedPair"])
    fun shouldBeReturnZeroChange() {
        strategyZeroTest(nakedPairStrategy, tableWithoutChange)
    }

    @Test(groups = ["NakedPair"])
    fun shouldBeRightChange() {
        strategyApplyChangeTest(
                nakedPairStrategy,
                table,
                tableString,
                "not insert change"
        )
    }

    @Test(groups = ["NakedPair", "VerboseOutput"])
    fun shouldPrintVerboseLog() {
        strategyPrintVerboseLog(
                nakedPairStrategy,
                table,
                "Exclude 6 at [4,7] by Naked Pair in row. Pair (6,8) found in row 4 at [4,2] and [4,6]"
        )
    }
}
