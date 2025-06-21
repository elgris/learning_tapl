package arith

import arith.parser.Parser.parse;
import java.io.StringReader

val input = """
0;
succ (pred 0);
iszero (pred (succ (succ 0)));    
""".trimIndent()

fun main() {
    val ast = parse(input.byteInputStream())
    println(ast)
}