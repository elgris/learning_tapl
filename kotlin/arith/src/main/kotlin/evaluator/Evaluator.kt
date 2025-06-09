package arith.evaluator

import arith.ast.False
import arith.ast.If
import arith.ast.IsZero
import arith.ast.Pred
import arith.ast.Succ
import arith.ast.Term
import arith.ast.True
import arith.ast.Zero

class EvaluationException(message: String) : RuntimeException(message)

fun eval(term: Term): Term {
    return when (term) {
        is True, is False, is Zero -> term
        is If -> {
            val condResult = eval(term.cond)
            when (condResult) {
                is True -> eval(term.thenBranch)
                is False -> eval(term.elseBranch)
                else -> throw EvaluationException("Guard of 'if' must be a boolean")
            }
        }
        is Succ -> {
            val inner = eval(term.term)
            // In this untyped system, we check if the term is a numeric value.
            if (isNumericVal(inner)) {
                Succ(inner)
            } else {
                throw EvaluationException("Argument of 'succ' must be a numeric value")
            }
        }
        is Pred -> {
            val inner = eval(term.term)
            when (inner) {
                is Zero -> throw EvaluationException("pred of zero is undefined")
                is Succ -> inner.term
                else -> throw EvaluationException("Argument of 'pred' must be a numeric value")
            }
        }
        is IsZero -> {
            val inner = eval(term.term)
            when {
                inner is Zero -> True
                isNumericVal(inner) -> False // iszero of any other number (succ ...) is false
                else -> throw EvaluationException("Argument of 'iszero' must be a numeric value")
            }
        }
    }
}

// Helper to check if a term is a value and is numeric.
fun isNumericVal(term: Term): Boolean {
    return when (term) {
        is Zero -> true
        is Succ -> isNumericVal(term.term)
        else -> false
    }
}