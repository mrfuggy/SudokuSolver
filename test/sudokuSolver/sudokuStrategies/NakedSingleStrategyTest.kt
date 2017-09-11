package sudokuSolver.sudokuStrategies

import interactive.loader.SudokuStringLoader
import org.testng.Assert.assertEquals
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test
import sudokuSolver.Table
import sudokuSolver.batch
import sudokuSolver.cellChanges.InsertChange

class NakedSingleStrategyTest {

    private var table: Table = Table.EmptyTable

    @BeforeTest
    fun setup() {
        table = Table(SudokuStringLoader("039612000000735000020489063305000000700308002000000804260000070000807000000024950", 81)
                .readList()
                .asSequence()
                .batch(9)
                .toList())
    }

    @Test(groups = arrayOf("NakedSingle"))
    fun shouldBeReturnInsertChanges() {
        val testableStrategy = NakedSingleStrategy()
        testableStrategy.updateTable(table)

        val actualChange = testableStrategy.getAnyChange()

        assertEquals(actualChange::class, InsertChange::class, "should be insert")
        assertEquals(actualChange.hasChange(), true, "should have change")

    }

    @Test(groups = arrayOf("NakedSingle"))
    fun shouldBeRightChange() {
        val testableStrategy = NakedSingleStrategy()
        testableStrategy.updateTable(table)

        val actualChange = testableStrategy.getAnyChange()
        val newTable = actualChange.apply(table)
        val newTextTable = newTable
                .getCellEnumerator()
                .joinToString(separator = "") { it.value.toString() }

        assertEquals(
                newTextTable,
                "039612000000735000020489063305000000700308002000000804260000070000807000000124950",
                "should be change '1'")
    }

    @Test(groups = arrayOf("NakedSingle"))
    fun shouldBeRightChangeThen() {
        val testableStrategy = NakedSingleStrategy()
        testableStrategy.updateTable(table)

        val change1 = testableStrategy.getAnyChange()
        val table1 = change1.apply(table)
        testableStrategy.updateTable(table1)

        val actualChange = testableStrategy.getAnyChange()
        val newTable = actualChange.apply(table1)
        testableStrategy.updateTable(newTable)

        val newTextTable = newTable
                .getCellEnumerator()
                .joinToString(separator = "") { it.value.toString() }
        assertEquals(
                newTextTable,
                "039612000000735000020489063305000000700308002000000804260003070000807000000124950",
                "should be change '3'")

        val actualChange2 = testableStrategy.getAnyChange()
        val newTable2 = actualChange2.apply(newTable)

        val newTextTable2 = newTable2
                .getCellEnumerator()
                .joinToString(separator = "") { it.value.toString() }
        assertEquals(
                newTextTable2,
                "039612000000735000020489063305000000700308002000000804260000070000807000800124950",
                "should be change '8'")
    }
}