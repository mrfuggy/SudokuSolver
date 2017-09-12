package sudokuSolver.sudokuStrategies

import interactive.viewer.SudokuStringViewer
import org.testng.Assert.assertEquals
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import sudokuSolver.Table
import sudokuSolver.TestExtensions
import sudokuSolver.cellChanges.CompositeChange
import sudokuSolver.cellChanges.InsertChange
import sudokuSolver.sudokuStrategies.hiddenSingle.HiddenSingleBoxStrategy
import sudokuSolver.sudokuStrategies.hiddenSingle.HiddenSingleColumnStrategy
import sudokuSolver.sudokuStrategies.hiddenSingle.HiddenSingleRowStrategy

class HiddenSingleStrategyTest {

    private var table: Table = Table.EmptyTable
    private var hiddenSingleStrategy = HiddenSingleCompositeStrategy()
    private var hiddenSingleRowStrategy = HiddenSingleRowStrategy()
    private var hiddenSingleColumnStrategy = HiddenSingleColumnStrategy()
    private var hiddenSingleBoxStrategy = HiddenSingleBoxStrategy()
    private var stringViewer = SudokuStringViewer()

    @BeforeMethod
    fun setup() {
        table = TestExtensions.getTable("002080400850604200740000000009173800000462000004958000000000025005806014006090300")

        hiddenSingleStrategy = HiddenSingleCompositeStrategy()
        hiddenSingleStrategy.updateTable(table)

        hiddenSingleRowStrategy = HiddenSingleRowStrategy()
        hiddenSingleRowStrategy.updateTable(table)

        hiddenSingleColumnStrategy = HiddenSingleColumnStrategy()
        hiddenSingleColumnStrategy.updateTable(table)

        hiddenSingleBoxStrategy = HiddenSingleBoxStrategy()
        hiddenSingleBoxStrategy.updateTable(table)

        stringViewer = SudokuStringViewer()
    }

    @Test(groups = arrayOf("HiddenSingle", "HiddenSingleRow"))
    fun shouldBeRowReturnInsertChanges() {
        val actualChange = hiddenSingleRowStrategy.getAnyChange()

        assertEquals(actualChange::class, InsertChange::class, "should be insert")
        assertEquals(actualChange.hasChange(), true, "should have change")
    }

    @Test(groups = arrayOf("HiddenSingle", "HiddenSingleRow"))
    fun shouldBeRowRightChange() {
        val actualChange = hiddenSingleRowStrategy.getAnyChange()
        val newTable = actualChange.apply(table)
        stringViewer.view(newTable)

        assertEquals(
                stringViewer.getString(),
                "002080400850604200740000000009173840000462000004958000000000025005806014006090300",
                "should be change '4'")
    }

    @Test(groups = arrayOf("HiddenSingle", "HiddenSingleColumn"))
    fun shouldBeColumnReturnInsertChanges() {
        val actualChange = hiddenSingleColumnStrategy.getAnyChange()

        assertEquals(actualChange::class, InsertChange::class, "should be insert")
        assertEquals(actualChange.hasChange(), true, "should have change")
    }

    @Test(groups = arrayOf("HiddenSingle", "HiddenSingleColumn"))
    fun shouldBeColumnRightChange() {
        val actualChange = hiddenSingleColumnStrategy.getAnyChange()
        val newTable = actualChange.apply(table)
        stringViewer.view(newTable)

        assertEquals(
                stringViewer.getString(),
                "002080400850604200740000000009173800000462000004958000000040025005806014006090300",
                "should be change '4'")
    }

    @Test(groups = arrayOf("HiddenSingle", "HiddenSingleBox"))
    fun shouldBeBoxReturnInsertChanges() {
        val actualChange = hiddenSingleBoxStrategy.getAnyChange()

        assertEquals(actualChange::class, InsertChange::class, "should be insert")
        assertEquals(actualChange.hasChange(), true, "should have change")
    }

    @Test(groups = arrayOf("HiddenSingle", "HiddenSingleBox"))
    fun shouldBeBoxRightChange() {
        val actualChange = hiddenSingleBoxStrategy.getAnyChange()
        val newTable = actualChange.apply(table)
        stringViewer.view(newTable)

        assertEquals(
                stringViewer.getString(),
                "002080400850604200740000000009173800000462000004958000000040025005806014006090300",
                "should be change '4'")
    }

    @Test(groups = arrayOf("HiddenSingle"))
    fun shouldBeReturnInsertChanges() {
        val actualChange = hiddenSingleStrategy.getAnyChange()

        assertEquals(actualChange::class, CompositeChange::class, "should be CompositeChange > insert")
        assertEquals(actualChange.hasChange(), true, "should have change")
    }

    @Test(groups = arrayOf("HiddenSingle"))
    fun shouldBeRightChange() {
        val actualChange = hiddenSingleStrategy.getAnyChange()
        val newTable = actualChange.apply(table)
        stringViewer.view(newTable)

        assertEquals(
                stringViewer.getString(),
                "002080400850604200740000000009173840000462000004958000000000025005806014006090300",
                "should be change '4'")
    }
}