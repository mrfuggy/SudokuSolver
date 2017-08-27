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