package sudokuSolver

import org.testng.Assert.assertEquals
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class PointTest {

    @get:DataProvider(name = "boxIndexData")
    val boxIndexData = arrayOf(
            arrayOf(0, 0, 0),
            arrayOf(2, 2, 0),
            arrayOf(3, 0, 1),
            arrayOf(0, 3, 3),
            arrayOf(0, 6, 6),
            arrayOf(0, 5, 3),
            arrayOf(7, 7, 8)
    )

    @Test(groups = arrayOf("PointTest"), dataProvider = "boxIndexData")
    fun shouldBeGetBoxIndex(rowIndex: Int, columnIndex: Int, expected: Int) {
        val p = Point(rowIndex, columnIndex)

        val index = p.getBoxIndex()

        assertEquals(index, expected)
    }

    @get:DataProvider(name = "cellIndexData")
    val cellIndexData = arrayOf(
            arrayOf(0, 0, 0),
            arrayOf(8, 8, 80),
            arrayOf(0, 1, 9),
            arrayOf(1, 0, 1),
            arrayOf(2, 0, 2),
            arrayOf(1, 3, 28),
            arrayOf(3, 1, 12),
            arrayOf(5, 5, 50)
    )

    @Test(groups = arrayOf("PointTest"), dataProvider = "cellIndexData")
    fun shouldBeGetCellIndex(rowIndex: Int, columnIndex: Int, expected: Int) {
        val p = Point(rowIndex, columnIndex)

        val index = p.getCellIndex()

        assertEquals(index, expected)
    }
}
