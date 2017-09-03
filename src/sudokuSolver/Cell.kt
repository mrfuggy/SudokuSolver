package sudokuSolver

data class Cell(val index: Point, val value: Byte, val candidates: Set<Byte>)