package sudokuSolver.sudokuStrategies

import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import sudokuSolver.Table
import sudokuSolver.TestExtensions
import sudokuSolver.sudokuStrategies.hiddenSingle.HiddenSingleBoxStrategy
import sudokuSolver.sudokuStrategies.hiddenSingle.HiddenSingleColumnStrategy
import sudokuSolver.sudokuStrategies.hiddenSingle.HiddenSingleRowStrategy

class HiddenSingleStrategyTest : StrategyTest() {

    private var table: Table = Table.EmptyTable
    private var tableWithoutChange: Table = Table.EmptyTable
    private var hiddenSingleStrategy = HiddenSingleCompositeStrategy()
    private var hiddenSingleRowStrategy = HiddenSingleRowStrategy()
    private var hiddenSingleColumnStrategy = HiddenSingleColumnStrategy()
    private var hiddenSingleBoxStrategy = HiddenSingleBoxStrategy()

    @BeforeMethod
    fun setup() {
        table = TestExtensions.getTable("002080400850604200740000000009173800000462000004958000000000025005806014006090300")
        tableWithoutChange = TestExtensions.getTable("901000040870000019543196287405310078010080004089005102234971800190000723050000491")

        hiddenSingleStrategy = HiddenSingleCompositeStrategy()

        hiddenSingleRowStrategy = HiddenSingleRowStrategy()

        hiddenSingleColumnStrategy = HiddenSingleColumnStrategy()

        hiddenSingleBoxStrategy = HiddenSingleBoxStrategy()
    }

    @Test(groups = arrayOf("HiddenSingle", "HiddenSingleRow"))
    fun shouldBeRowReturnInsertChanges() {
        strategyInsertTest(hiddenSingleRowStrategy, table)
    }

    @Test(groups = arrayOf("HiddenSingle", "HiddenSingleRow"))
    fun shouldBeRowReturnZeroChanges() {
        strategyZeroTest(hiddenSingleRowStrategy, tableWithoutChange)
    }

    @Test(groups = arrayOf("HiddenSingle", "HiddenSingleRow"))
    fun shouldBeRowRightChange() {
        strategyApplyChangeTest(
                hiddenSingleRowStrategy,
                table,
                "002080400850604200740000000009173840000462000004958000000000025005806014006090300",
                "should be change '4'")
    }

    @Test(groups = arrayOf("HiddenSingle", "HiddenSingleColumn"))
    fun shouldBeColumnReturnInsertChanges() {
        strategyInsertTest(hiddenSingleColumnStrategy, table)
    }

    @Test(groups = arrayOf("HiddenSingle", "HiddenSingleColumn"))
    fun shouldBeColumnReturnZeroChanges() {
        strategyZeroTest(hiddenSingleColumnStrategy, tableWithoutChange)
    }

    @Test(groups = arrayOf("HiddenSingle", "HiddenSingleColumn"))
    fun shouldBeColumnRightChange() {
        strategyApplyChangeTest(
                hiddenSingleColumnStrategy,
                table,
                "002080400850604200740000000009173800000462000004958000000040025005806014006090300",
                "should be change '4'")
    }

    @Test(groups = arrayOf("HiddenSingle", "HiddenSingleBox"))
    fun shouldBeBoxReturnInsertChanges() {
        strategyInsertTest(hiddenSingleBoxStrategy, table)
    }

    @Test(groups = arrayOf("HiddenSingle", "HiddenSingleBox"))
    fun shouldBeBoxReturnZeroChanges() {
        strategyZeroTest(hiddenSingleBoxStrategy, tableWithoutChange)
    }

    @Test(groups = arrayOf("HiddenSingle", "HiddenSingleBox"))
    fun shouldBeBoxRightChange() {
        strategyApplyChangeTest(
                hiddenSingleBoxStrategy,
                table,
                "002080400850604200740000000009173800000462000004958000000040025005806014006090300",
                "should be change '4'")
    }

    @Test(groups = arrayOf("HiddenSingle"))
    fun shouldBeReturnInsertChanges() {
        strategyCompositeInsertTest(hiddenSingleStrategy, table)
    }

    @Test(groups = arrayOf("HiddenSingle"))
    fun shouldBeReturnZeroChanges() {
        strategyZeroTest(hiddenSingleStrategy, tableWithoutChange)
    }

    @Test(groups = arrayOf("HiddenSingle"))
    fun shouldBeRightChange() {
        strategyApplyChangeTest(
                hiddenSingleStrategy,
                table,
                "002080400850604200740000000009173840000462000004958000000000025005806014006090300",
                "should be change '4'")
    }
}