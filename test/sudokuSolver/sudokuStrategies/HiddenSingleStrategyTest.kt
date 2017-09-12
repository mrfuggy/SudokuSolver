package sudokuSolver.sudokuStrategies

import interactive.loader.SudokuStringLoader
import interactive.viewer.SudokuStringViewer
import org.testng.Assert.assertEquals
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import sudokuSolver.Table
import sudokuSolver.batch
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

    @BeforeMethod
    fun setup() {
        hiddenSingleStrategy = HiddenSingleCompositeStrategy()
        hiddenSingleRowStrategy = HiddenSingleRowStrategy()
        hiddenSingleColumnStrategy = HiddenSingleColumnStrategy()
        hiddenSingleBoxStrategy = HiddenSingleBoxStrategy()

        table = Table(SudokuStringLoader("002080400850604200740000000009173800000462000004958000000000025005806014006090300", 81)
                .readList()
                .asSequence()
                .batch(9)
                .toList())
    }

    @Test(groups = arrayOf("HiddenSingle", "HiddenSingleRow"))
    fun shouldBeRowReturnInsertChanges() {
        hiddenSingleRowStrategy.updateTable(table)

        val actualChange = hiddenSingleRowStrategy.getAnyChange()

        assertEquals(actualChange::class, InsertChange::class, "should be insert")
        assertEquals(actualChange.hasChange(), true, "should have change")
    }

    @Test(groups = arrayOf("HiddenSingle", "HiddenSingleRow"))
    fun shouldBeRowRightChange() {
        hiddenSingleRowStrategy.updateTable(table)
        val stringViewer = SudokuStringViewer()

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
        hiddenSingleColumnStrategy.updateTable(table)

        val actualChange = hiddenSingleColumnStrategy.getAnyChange()

        assertEquals(actualChange::class, InsertChange::class, "should be insert")
        assertEquals(actualChange.hasChange(), true, "should have change")
    }

    @Test(groups = arrayOf("HiddenSingle", "HiddenSingleColumn"))
    fun shouldBeColumnRightChange() {
        hiddenSingleColumnStrategy.updateTable(table)
        val stringViewer = SudokuStringViewer()

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
        hiddenSingleBoxStrategy.updateTable(table)

        val actualChange = hiddenSingleBoxStrategy.getAnyChange()

        assertEquals(actualChange::class, InsertChange::class, "should be insert")
        assertEquals(actualChange.hasChange(), true, "should have change")
    }

    @Test(groups = arrayOf("HiddenSingle", "HiddenSingleBox"))
    fun shouldBeBoxRightChange() {
        hiddenSingleBoxStrategy.updateTable(table)
        val stringViewer = SudokuStringViewer()

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
        hiddenSingleStrategy.updateTable(table)

        val actualChange = hiddenSingleStrategy.getAnyChange()

        assertEquals(actualChange::class, CompositeChange::class, "should be CompositeChange > insert")
        assertEquals(actualChange.hasChange(), true, "should have change")
    }

    @Test(groups = arrayOf("HiddenSingle"))
    fun shouldBeRightChange() {
        hiddenSingleStrategy.updateTable(table)
        val stringViewer = SudokuStringViewer()

        val actualChange = hiddenSingleStrategy.getAnyChange()
        val newTable = actualChange.apply(table)
        stringViewer.view(newTable)

        assertEquals(
                stringViewer.getString(),
                "002080400850604200740000000009173840000462000004958000000000025005806014006090300",
                "should be change '4'")
    }
}