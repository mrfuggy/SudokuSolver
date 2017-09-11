package sudokuSolver

data class Point(val rowIndex: Int, val columnIndex: Int) {
    fun getBoxIndex(): Int = getBoxIndex(rowIndex) + 3 * getBoxIndex(columnIndex)

    private fun getBoxIndex(index: Int) =
            when (index) {
                0 -> 0
                1 -> 0
                2 -> 0
                3 -> 1
                4 -> 1
                5 -> 1
                6 -> 2
                7 -> 2
                8 -> 2
                else -> throw IllegalArgumentException("index")
            }
}