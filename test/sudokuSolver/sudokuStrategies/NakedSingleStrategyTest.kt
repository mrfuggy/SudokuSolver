package sudokuSolver.sudokuStrategies

import org.testng.Assert.assertEquals
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

class NakedSingleStrategyTest : StrategyTest() {

    private var table = getTable("039612000000735000020489063305000000700308002000000804260000070000807000000024950")
    private var tableWithoutChange = getTable("901000040870000000543196287405300070000080000080005102000971800000000023050000401")
    private var nakedSingleStrategy = NakedSingleStrategy()

    @BeforeMethod
    fun setup() {
        nakedSingleStrategy = NakedSingleStrategy()
    }

    @Test(groups = ["NakedSingle"])
    fun shouldBeReturnInsertChanges() {
        strategyInsertTest(nakedSingleStrategy, table)
    }

    @Test(groups = ["NakedSingle"])
    fun shouldBeReturnZeroChanges() {
        strategyZeroTest(nakedSingleStrategy, tableWithoutChange)
    }

    @Test(groups = ["NakedSingle"])
    fun shouldBeRightChange() {
        strategyApplyChangeTest(
                nakedSingleStrategy,
                table,
                "039612000000735000020489063305000000700308002000000804260000070000807000000124950",
                "should be change '1'")
    }

    @Test(groups = ["NakedSingle", "Pilot"])
    fun shouldBeRightChangeThen() {
        nakedSingleStrategy.updateTable(table)

        val change1 = nakedSingleStrategy.getAnyChange()
        val table1 = change1.apply(table)
        nakedSingleStrategy.updateTable(table1)

        val actualChange = nakedSingleStrategy.getAnyChange()
        val newTable = actualChange.apply(table1)
        nakedSingleStrategy.updateTable(newTable)
        stringViewer.view(newTable)

        assertEquals(
                stringViewer.getString(),
                "039612000000735000020489063305000000700308002000000804260003070000807000000124950",
                "should be change '3'")

        val actualChange2 = nakedSingleStrategy.getAnyChange()
        val newTable2 = actualChange2.apply(newTable)
        stringViewer.view(newTable2)

        assertEquals(
                stringViewer.getString(),
                "039612000000735000020489063305000000700308002000000804260003070000807000800124950",
                "should be change '8'")
    }
}