package sudokuSolver

import org.testng.Assert.assertTrue
import org.testng.annotations.Test
import sudokuSolver.sudokuStrategies.StrategyTest
import kotlin.test.assertFalse

class TableTest : StrategyTest() {

    @Test(groups = ["Table", "Pilot"])
    fun testHasEmptyCell() {
        val table = getTable("701456389834129657695783421917368245268594713543271896479612538386945172152837964")

        val actual = table.hasEmptyCell()
        assertTrue(actual, "has empty cell")
    }

    @Test(groups = ["Table", "Pilot"])
    fun testNotHasEmptyCell() {
        val table = getTable("721456389834129657695783421917368245268594713543271896479612538386945172152837964")

        val actual = table.hasEmptyCell()
        assertFalse(actual, "has empty cell")
    }

    @Test(groups = ["Table", "Validate", "Pilot"], expectedExceptions = [(IllegalStateException::class)])
    fun testHasZeroCandidates() {
        getTable("701524389030109000000083000000008205060000010503200000000610000000905070002000904")
    }

    @Test(groups = ["Table", "Validate", "Pilot"], expectedExceptions = [(IllegalStateException::class)])
    fun testHasDuplicatesValue() {
        getTable("731000300030109000000083000000008205060000010503200000000610000000905070002000904")
    }
}
