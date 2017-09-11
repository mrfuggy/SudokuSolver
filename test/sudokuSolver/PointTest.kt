package sudokuSolver

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class PointTest {

    @Test(groups = arrayOf("PointTest"))
    fun shouldBeGetBoxIndex1() {
        val p = Point(0, 0)

        val index = p.getBoxIndex()

        assertEquals(index, 0)
    }

    @Test(groups = arrayOf("PointTest"))
    fun shouldBeGetBoxIndex2() {
        val p = Point(2, 2)

        val index = p.getBoxIndex()

        assertEquals(index, 0)
    }

    @Test(groups = arrayOf("PointTest"))
    fun shouldBeGetBoxIndex3() {
        val p = Point(3, 0)

        val index = p.getBoxIndex()

        assertEquals(index, 1)
    }

    @Test(groups = arrayOf("PointTest"))
    fun shouldBeGetBoxIndex4() {
        val p = Point(0, 3)

        val index = p.getBoxIndex()

        assertEquals(index, 3)
    }

    @Test(groups = arrayOf("PointTest"))
    fun shouldBeGetBoxIndex5() {
        val p = Point(0, 6)

        val index = p.getBoxIndex()

        assertEquals(index, 6)
    }

    @Test(groups = arrayOf("PointTest"))
    fun shouldBeGetBoxIndex6() {
        val p = Point(0, 5)

        val index = p.getBoxIndex()

        assertEquals(index, 3)
    }

    @Test(groups = arrayOf("PointTest"))
    fun shouldBeGetBoxIndex7() {
        val p = Point(7, 7)

        val index = p.getBoxIndex()

        assertEquals(index, 8)
    }
}