package sudokuSolver

data class Point(val i: Int, val j: Int) {
    fun getBoxNumber(): Int = getBoxIndex(i) + 3 * getBoxIndex(j)

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