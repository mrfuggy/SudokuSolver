package sudokuSolver.sudokuStrategies

import interactive.loader.SudokuStringLoader
import interactive.verbose.ListVerboseLogger
import interactive.viewer.SudokuStringViewer
import org.testng.Assert.*
import sudokuSolver.SolverParameters
import sudokuSolver.SudokuStrategy
import sudokuSolver.Table
import sudokuSolver.batch
import sudokuSolver.cellChanges.CompositeChange
import sudokuSolver.cellChanges.ExcludeChange
import sudokuSolver.cellChanges.InsertChange
import sudokuSolver.cellChanges.ZeroChange

abstract class StrategyTest {

    protected var stringViewer = SudokuStringViewer()
    private val solverParameters = SolverParameters(
            verboseOutput = true,
            moreVerboseOutput = true)
    private val verboseLogger = ListVerboseLogger(solverParameters)

    protected fun strategyInsertTest(strategy: SudokuStrategy, tableData: Table) {
        strategy.updateTable(tableData)

        val actualChange = strategy.getAnyChange()

        assertEquals(actualChange::class, InsertChange::class, "should be insert")
        assertTrue(actualChange.hasChange(), "should have change")
    }

    protected fun strategyCompositeTest(strategy: SudokuStrategy, tableData: Table) {
        strategy.updateTable(tableData)

        val actualChange = strategy.getAnyChange()

        assertEquals(actualChange::class, CompositeChange::class, "should be CompositeChange > change")
        assertTrue(actualChange.hasChange(), "should have change")
    }

    protected fun strategyExcludeTest(strategy: SudokuStrategy, tableData: Table) {
        strategy.updateTable(tableData)

        val actualChange = strategy.getAnyChange()

        assertEquals(actualChange::class, ExcludeChange::class, "should be exclude")
        assertTrue(actualChange.hasChange(), "should have change")
    }

    protected fun strategyZeroTest(strategy: SudokuStrategy, tableData: Table) {
        strategy.updateTable(tableData)

        val actualChange = strategy.getAnyChange()

        assertEquals(actualChange, ZeroChange, "should be zero")
        assertFalse(actualChange.hasChange(), "should not have change")
    }

    protected fun strategyApplyChangeTest(
            strategy: SudokuStrategy,
            tableData: Table,
            expected: String,
            message: String) {
        strategy.updateTable(tableData)

        val actualChange = strategy.getAnyChange()
        val newTable = actualChange.apply(tableData)
        stringViewer.view(newTable)

        assertEquals(stringViewer.getString(), expected, message)
    }

    protected fun strategyPrintVerboseLog(
            strategy: SudokuStrategy,
            tableData: Table,
            expected: String) {
        strategy.updateTable(tableData)

        val change = strategy.getAnyChange()
        change.printVerboseLog(verboseLogger)

        assertEquals(verboseLogger.getLog().last(), expected, "verbose log")
    }

    protected fun getTable(line: String): Table {
        return Table(
                SudokuStringLoader(line, 81)
                        .readList()
                        .asSequence()
                        .batch(9)
                        .toList())
    }
}
