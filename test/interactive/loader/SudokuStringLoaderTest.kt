package interactive.loader

import com.sun.javaws.exceptions.InvalidArgumentException
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class SudokuStringLoaderTest {

    @Test(groups = arrayOf("StringLoader"))
    fun shouldBeLineLength9() {
        val row = SudokuStringLoader("123456789", 9).readList()
        assertEquals(9, row.size)
    }

    @Test(groups = arrayOf("StringLoader"), expectedExceptions = arrayOf(InvalidArgumentException::class))
    fun shouldBeThrowException() {
        SudokuStringLoader("23456789", 9).readList()
    }

    @Test(groups = arrayOf("StringLoader"), expectedExceptions = arrayOf(InvalidArgumentException::class))
    fun shouldBeThrowException2() {
        SudokuStringLoader("1123456789", 9).readList()
    }

    @Test(groups = arrayOf("StringLoader"), expectedExceptions = arrayOf(NumberFormatException::class))
    fun shouldBeThrowException3() {
        SudokuStringLoader("1a3456789", 9).readList()
    }
}