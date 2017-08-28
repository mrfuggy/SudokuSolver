package interactive.loader

import com.sun.javaws.exceptions.InvalidArgumentException

class SudokuStringLoader(private val line: String?) {
    fun readList(): List<Byte> {
        if (line != null && line.length == 9) {
            return parseRow(line)
        } else {
            throw InvalidArgumentException(arrayOf("Line length must be 9."))
        }
    }

    private fun parseRow(str: String) = str.map { s -> s.toString().toByte() }
}