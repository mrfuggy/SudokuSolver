package sudokuSolver

data class Cell(val index: Point, val value: Byte, val candidates: Set<Byte>) {
    companion object {
        val EmptyCell = Cell(Point(0, 0), 0, setOf())
        const val EmptyValue = 0.toByte()
    }

    fun isEmptyCell() = value == EmptyValue
    fun isPair() = candidates.count() == 2
}
