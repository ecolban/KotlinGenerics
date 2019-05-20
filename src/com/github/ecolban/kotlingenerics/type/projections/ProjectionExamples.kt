package com.github.ecolban.kotlingenerics.type.projections

/**
 * All occurrences of T in Array<T> that are in "in position" are replaced by Int
 * All occurrences of T in Array<T> that are in "out position" are replaced by Any?
 */
fun getFromInProjection(a: Array<in Int>): Any? {
    a[0] = 1
    return a[0] // return type is Any?
}

/**
 * All occurrences of T in Array<T> that are in "in position" are replaced by Nothing
 * All occurrences of T in Array<T> that are in "out position" are replaced by Int
 */
fun insertIntoOutProjection(a: Array<out Int>) {
//    a[0] = 1 // Not possible
    val b = a[0]
}

/**
 * All occurrences of T in Array<T> that are in "in position" are replaced by Nothing
 * All occurrences of T in Array<T> that are in "out position" are replaced by Any?
 */
fun getFromAndInsertIntoStarProjection(a: Array<*>) {
//    a[0] = 1 // Not possible
    val b = a[0] // b's type is Any?
}


fun main(vararg args: String) {
    val readOnlyArray: Array<out Number> = arrayOf(1, 2, 3)
//    readOnlyArray[0] = 0 // Not possible
    val writableArray: Array<Number> = readOnlyArray as Array<Number>
    writableArray[0] = 0
    println(readOnlyArray.asList())
//  Now the same for List
    val readOnlyList: List<Number> = listOf(1, 2, 3)
//    readOnlyList[0] = 0 // Not possible
    val writableList = readOnlyList as MutableList<Number>
    writableList[0] = 0
    println(readOnlyList)
}

