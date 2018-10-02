package sudokuSolver.sudokuStrategies.nakedPair

import interactive.verbose.ListVerboseLogger
import org.testng.Assert.*
import org.testng.annotations.Test
import sudokuSolver.Cell
import sudokuSolver.Point
import sudokuSolver.SolverParameters

class SudokuPairTest {

    private val group = getGroup()
    private val group2 = getGroup2()
    private val strategy = NakedPairBoxStrategy()
    private var sudokuPair = SudokuPair(strategy, group)
    private val solverParameters = SolverParameters(
            verboseOutput = true,
            moreVerboseOutput = true)
    private val verboseLogger = ListVerboseLogger(solverParameters)

    private fun getGroup(): List<Cell> {
        return listOf(
                Cell(Point(0, 0), 7, setOf()),
                Cell(Point(0, 1), 0, setOf(2, 4)), //pair
                Cell(Point(0, 2), 1, setOf()),
                Cell(Point(1, 0), 0, setOf(6, 8)),
                Cell(Point(1, 1), 3, setOf()),
                Cell(Point(1, 2), 0, setOf(2, 4)), //pair
                Cell(Point(2, 0), 0, setOf(4, 5)), //exclude
                Cell(Point(2, 1), 0, setOf(7)),
                Cell(Point(2, 2), 0, setOf(8))
        )
    }

    private fun getGroup2(): List<Cell> {
        return listOf(
                Cell(Point(0, 0), 7, setOf()),
                Cell(Point(0, 1), 0, setOf(2, 4)), //pair without exclude
                Cell(Point(0, 2), 1, setOf()),
                Cell(Point(1, 0), 0, setOf(6, 8)), //pair
                Cell(Point(1, 1), 3, setOf()),
                Cell(Point(1, 2), 0, setOf(2, 4)), //pair without exclude
                Cell(Point(2, 0), 0, setOf(5, 8)),
                Cell(Point(2, 1), 0, setOf(7)),
                Cell(Point(2, 2), 0, setOf(6, 8)) //pair
        )
    }

    @Test(groups = ["SudokuPair", "Pilot"])
    fun shouldBeFoundPair() {
        sudokuPair = SudokuPair(strategy, group)

        val found = sudokuPair.tryFoundPair()
        assertTrue(found, "pair found")

        val change = sudokuPair.getAnyChange()
        assertTrue(change.hasChange(), "exclude change exists")

        change.printVerboseLog(verboseLogger)
        assertEquals(
                verboseLogger.getLog().last(),
                "Exclude 4 at [3,1] by Naked Pair in box. Pair (2,4) found in box 1 at [1,2] and [2,3]",
                "right change")
    }

    @Test(groups = ["SudokuPair", "Pilot"])
    fun shouldBeFoundSecondPair() {
        sudokuPair = SudokuPair(strategy, group2)

        val found = sudokuPair.tryFoundPair()
        assertTrue(found, "pair found")

        val change = sudokuPair.getAnyChange()
        assertFalse(change.hasChange(), "not any change")

        val canNextPair = sudokuPair.tryGetNextPair()
        assertTrue(canNextPair, "has second pair")

        sudokuPair = sudokuPair.getNext()
        val found2 = sudokuPair.tryFoundPair()
        assertTrue(found2, "second pair found")

        val change2 = sudokuPair.getAnyChange()
        assertTrue(change2.hasChange(), "second exclude change exists")

        change2.printVerboseLog(verboseLogger)
        assertEquals(
                verboseLogger.getLog().last(),
                "Exclude 8 at [3,1] by Naked Pair in box. Pair (6,8) found in box 1 at [2,1] and [3,3]",
                "right change")
    }
}
