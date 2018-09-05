package org.drawmetry.ecolban.type.erasure

inline fun <reified T> myFun(a: Any) {
    when (a) {
        is T -> println("Yeah! $a is a ${T::class.java.simpleName}")
        else -> println("Oh, no. $a is not a ${T::class.java.simpleName}")
    }
}

/*
Android example

inline fun <reified T : Activity> Context.startActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

startActivity<DetailActivity>()

*/

fun <T : Any> MutableList<T>.addNumber(n: Number) {
    val intList = this as MutableList<Number>
    intList.add(n)
}

fun main(vararg args: String) {
    myFun<String>(123)
    myFun<Number>(123)
    when (123) {
        is Number -> println("Yeah! 123 is a ${Number::class.java.simpleName}")
        else -> println("Oh, no. 123 is not a ${Number::class.java.simpleName}")
    }

    val stringList = mutableListOf("a", "b", "c")
    stringList.addNumber(1)
    println(stringList)
    println(stringList.filterIsInstance<String>())
}