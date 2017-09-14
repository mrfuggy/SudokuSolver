package sudokuSolver.sudokuStrategies

import interactive.viewer.SudokuStringViewer
import org.testng.Assert.assertEquals
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table
import sudokuSolver.cellChanges.CompositeChange
import sudokuSolver.cellChanges.InsertChange
import sudokuSolver.cellChanges.ZeroChange

abstract class StrategyTest {

    protected var stringViewer = SudokuStringViewer()

    protected fun strategyInsertTest(strategy: SudokuStrategy, tableData: Table) {
        strategy.updateTable(tableData)

        val actualChange = strategy.getAnyChange()

        assertEquals(actualChange::class, InsertChange::class, "should be insert")
        assertEquals(actualChange.hasChange(), true, "should have change")
    }

    protected fun strategyCompositeInsertTest(strategy: SudokuStrategy, tableData: Table) {
        strategy.updateTable(tableData)

        val actualChange = strategy.getAnyChange()

        assertEquals(actualChange::class, CompositeChange::class, "should be CompositeChange > insert")
        assertEquals(actualChange.hasChange(), true, "should have change")
    }

    protected fun strategyZeroTest(strategy: SudokuStrategy, tableData: Table) {
        strategy.updateTable(tableData)

        val actualChange = strategy.getAnyChange()

        assertEquals(actualChange::class, ZeroChange::class, "should be zero")
        assertEquals(actualChange.hasChange(), false, "should not have change")
    }

    protected fun strategyApplyChangeTest(strategy: SudokuStrategy, tableData: Table, expected: String, message: String) {
        strategy.updateTable(tableData)

        val actualChange = strategy.getAnyChange()
        val newTable = actualChange.apply(tableData)
        stringViewer.view(newTable)

        assertEquals(stringViewer.getString(), expected, message)
    }
}