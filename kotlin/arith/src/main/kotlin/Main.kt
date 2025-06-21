package arith

import arith.parser.Parser.parse;
import java.io.StringReader

const val input = "if false then true else false"

// TODO: support semicolon as a separator
// TODO: support parenthesis
// TODO: add evaluator
fun main() {
    val ast = parse(input.byteInputStream())
    println(ast)
}