package sudokuSolver.sudokuStrategies

import interactive.loader.SudokuStringLoader
import interactive.viewer.SudokuStringViewer
import org.testng.Assert.assertEquals
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test
import sudokuSolver.Table
import sudokuSolver.batch
import sudokuSolver.cellChanges.InsertChange

class NakedSingleStrategyTest {

    private var table: Table = Table.EmptyTable
    private var nakedSingleStrategy = NakedSingleStrategy()

    @BeforeTest
    fun setup() {
        nakedSingleStrategy = NakedSingleStrategy()

        table = Table(SudokuStringLoader("039612000000735000020489063305000000700308002000000804260000070000807000000024950", 81)
                .readList()
                .asSequence()
                .batch(9)
                .toList())
    }

    @Test(groups = arrayOf("NakedSingle"))
    fun shouldBeReturnInsertChanges() {
        nakedSingleStrategy.updateTable(table)

        val actualChange = nakedSingleStrategy.getAnyChange()

        assertEquals(actualChange::class, InsertChange::class, "should be insert")
        assertEquals(actualChange.hasChange(), true, "should have change")
    }

    @Test(groups = arrayOf("NakedSingle"))
    fun shouldBeRightChange() {
        nakedSingleStrategy.updateTable(table)
        val stringViewer = SudokuStringViewer()

        val actualChange = nakedSingleStrategy.getAnyChange()
        val newTable = actualChange.apply(table)
        stringViewer.view(newTable)

        assertEquals(
                stringViewer.getString(),
                "039612000000735000020489063305000000700308002000000804260000070000807000000124950",
                "should be change '1'")
    }

    @Test(groups = arrayOf("NakedSingle"))
    fun shouldBeRightChangeThen() {
        nakedSingleStrategy.updateTable(table)
        val stringViewer = SudokuStringViewer()

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