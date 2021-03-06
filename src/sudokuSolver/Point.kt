package sudokuSolver

data class Point(val rowIndex: Int, val columnIndex: Int) {

    fun getBoxIndex() = 3 * getBoxIndex(rowIndex) + getBoxIndex(columnIndex)

    fun getCellIndex() = rowIndex + columnIndex * 9

    private fun getBoxIndex(index: Int) =
            when (index) {
                in 0..8 -> index / 3
                else -> throw IllegalArgumentException("index")
            }

    override fun toString() = "[${rowIndex + 1},${columnIndex + 1}]"
}
