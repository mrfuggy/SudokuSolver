package sudokuSolver

import interactive.verbose.ListVerboseLogger
import org.testng.Assert.assertEquals
import org.testng.Assert.assertTrue
import org.testng.annotations.Test
import sudokuSolver.sudokuStrategies.*
import java.io.File

class SudokuSolverTest : StrategyTest() {

    private var table1 = getTable("701000300030109000000083000000008205060000010503200000000610000000905070002000904")
    private var table2 = getTable("070000652800002010000040000090300401002000700108005020000030000030600009986000030")
    private var table3 = getTable("000000020008050000070200108400300000005009000000800009609004300001060002300520010")
    private var table4 = getTable("000000000200804901000106320000005040800423006030900000063709000409502008000000000")
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
        sudokuSolver.addStrategy(LockedCandidateStrategy())
        sudokuSolver.addStrategy(NakedPairCompositeStrategy())
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

        (0..8)
                .forEach { moveNext(expectedLog[it]) }

        sudokuSolver.printStat()

        assertEquals(verboseLogger.getLog(), expectedLog, "verbose log")
    }

    @Test(groups = ["Solver"])
    fun testScenario3Solve() {
        createSolver(table3)
        val expectedLog = File("test/testData/scenario3.txt").readLines()

        (0..18)
                .forEach { moveNext(expectedLog[it]) }

        sudokuSolver.printStat()

        assertEquals(verboseLogger.getLog(), expectedLog, "verbose log")
    }

    @Test(groups = ["Solver"])
    fun testScenario4Solve() {
        createSolver(table4)
        val expectedLog = File("test/testData/scenario4.txt").readLines()

        (0..26)
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
