package arith

import arith.parser.Parser;
fun main() {
    val parser = Parser(System.`in`);
    val ast = parser.Parse();
}