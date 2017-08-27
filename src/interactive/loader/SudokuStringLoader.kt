package interactive.loader

class SudokuStringLoader(private val line: String?) {
    fun readList(): List<Byte> {
        while (true) {
            if (line != null && line.length == 9) {
                return parseRow(line)
            } else {
                println("Line length must be 9. Please repeat")
            }
        }
    }

    private fun parseRow(str: String) = str.map { s -> s.toString().toByte() }
}