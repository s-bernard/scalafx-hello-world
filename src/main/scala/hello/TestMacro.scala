package hello

import scala.quoted.*

// anywhere
inline def myMacro(inline param1: String): Int = ${
  MyMacro.myMacroImpl('param1)
}

// The main macro invocation still needs to be statically accessible
object MyMacro {
  def myMacroImpl(param1: Expr[String])(using Quotes): Expr[Int] =
    new MyMacro().myMacroImpl(param1)
}

class MyMacro(using Quotes) {
  import quotes.reflect.*

  def myMacroImpl(param1: Expr[String]): Expr[Int] =
    myHelperMethod.asExprOf[Int]

  private def myHelperMethod: Term = '{ 42 }.asTerm
}
