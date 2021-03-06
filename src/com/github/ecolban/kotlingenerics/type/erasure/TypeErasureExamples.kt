package com.github.ecolban.kotlingenerics.type.erasure

inline fun <reified T> checkType(a: Any) {
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

fun main() {
    checkType<String>(123)
    checkType<String>("blah")
    checkType<Number>(123)
    checkType<Number>("blah")
    when (val a = 123) {
        is Number -> println("Yeah! $a is a ${Number::class.java.simpleName}")
        else -> println("Oh, no. $a is not a ${Number::class.java.simpleName}")
    }

    val stringList: MutableList<String> = mutableListOf("a", "b", "c")
    stringList.addNumber(1)
    println(stringList)
    println(stringList.filterIsInstance<String>())
}