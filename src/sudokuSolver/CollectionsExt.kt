package sudokuSolver

inline fun <T> Iterable<T>.firstOrDefault(defaultValue: () -> T, predicate: (T) -> Boolean): T {
    this
            .filter { predicate(it) }
            .forEach { return it }

    return defaultValue()
}

inline fun <T, R> Iterable<T>.firstOrDefault(defaultValue: () -> R, predicate: (T) -> Boolean, result: (T) -> R): R {
    this
            .filter { predicate(it) }
            .forEach { return result(it) }

    return defaultValue()
}

inline fun <S, T> Iterable<T>.reduce(acc: S, reducer: (acc: S, value: T) -> S): S {
    this
            .forEach { reducer(acc, it) }

    return acc
}

fun List<List<Byte>>.insert(index: Point, value: Byte): List<List<Byte>> {
    val table = this.map { it.toMutableList() }.toMutableList()
    table[index.rowIndex][index.columnIndex] = value
    return table
}
