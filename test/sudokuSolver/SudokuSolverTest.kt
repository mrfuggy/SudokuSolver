package sudokuSolver

import interactive.verbose.ListVerboseLogger
import org.testng.Assert.assertEquals
import org.testng.Assert.assertTrue
import org.testng.annotations.Test
import sudokuSolver.sudokuStrategies.HiddenSingleCompositeStrategy
import sudokuSolver.sudokuStrategies.NakedSingleStrategy
import sudokuSolver.sudokuStrategies.StrategyTest
import java.io.File

class SudokuSolverTest : StrategyTest() {

    private var table1 = getTable("701000300030109000000083000000008205060000010503200000000610000000905070002000904")
    private var table2 = getTable("070000652800002010000040000090300401002000700108005020000030000030600009986000030")
    private val solverParameters = SolverParameters(
            verboseOutput = true,
            moreVerboseOutput = false)
    private var verboseLogger = ListVerboseLogger(solverParameters)

    private var sudokuSolver = SudokuSolver(table1, verboseLogger)

    private fun createSolver(table: Table) {
        verboseLogger = ListVerboseLogger(solverParameters)

        sudokuSolver = SudokuSolver(table, verboseLogger)
        sudokuSolver.addStrategy(NakedSingleStrategy())
        sudokuSolver.addStrategy(HiddenSingleCompositeStrategy())
    }

    @Test(groups = ["Solver"])
    fun testScenario1Solve() {
        createSolver(table1)
        val expectedLog = File("test/testData/scenario1.txt").readLines()

        (0..40)
                .forEach { moveNext(expectedLog[it]) }

        sudokuSolver.printStat()

        assertEquals(verboseLogger.getLog(), expectedLog, "verbose log")
    }

    @Test(groups = ["Solver"])
    fun testScenario2Solve() {
        createSolver(table2)
        val expectedLog = File("test/testData/scenario2.txt").readLines()

        (0..6)
                .forEach { moveNext(expectedLog[it]) }

        sudokuSolver.printStat()

        assertEquals(verboseLogger.getLog(), expectedLog, "verbose log")
    }

    private fun moveNext(expected: String) {
        val change = sudokuSolver.getAnyChange()
        assertTrue(change.hasChange())
        change.printVerboseLog(verboseLogger)
        val log = verboseLogger.getLog().last()
        assertEquals(log, expected)
        sudokuSolver.applyChange(change)
    }
}
