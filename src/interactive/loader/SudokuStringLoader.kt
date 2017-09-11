package interactive.loader

import com.sun.javaws.exceptions.InvalidArgumentException

class SudokuStringLoader(private val line: String?, private val count: Int) {
    fun readList(): List<Byte> {
        if (line != null && line.length == count) {
            return parseRow(line)
        } else {
            throw InvalidArgumentException(arrayOf("Line length must be {$count}."))
        }
    }

    private fun parseRow(str: String) = str.map { s -> s.toString().toByte() }
}