package arith.ast

sealed interface Term

object True : Term
object False : Term
data class If(val cond: Term, val thenBranch: Term, val elseBranch: Term) : Term
object Zero : Term
data class Succ(val term: Term) : Term
data class Pred(val term: Term) : Term
data class IsZero(val term: Term) : Term