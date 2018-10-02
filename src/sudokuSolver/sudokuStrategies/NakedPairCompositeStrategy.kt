package sudokuSolver.sudokuStrategies

import sudokuSolver.SudokuStrategy
import sudokuSolver.sudokuStrategies.nakedPair.NakedPairBoxStrategy
import sudokuSolver.sudokuStrategies.nakedPair.NakedPairColumnStrategy
import sudokuSolver.sudokuStrategies.nakedPair.NakedPairRowStrategy

class NakedPairCompositeStrategy :
        BaseCompositeStrategy(
                listOf(
                        NakedPairRowStrategy(),
                        NakedPairColumnStrategy(),
                        NakedPairBoxStrategy()
                )),
        SudokuStrategy {

    override fun getName() = "Naked Pair"
}
