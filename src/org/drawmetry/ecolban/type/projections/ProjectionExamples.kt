package org.drawmetry.ecolban.type.projections

/**
 * All occurrences of T in Array<in T> that are in "out position" are replaced by Any?
 */
fun <T : Number> getFromInProjection(a: Array<in T>) {
    val b = a[0]
}

/**
 * All occurrences of T in Array<out T> that are in "in position" are replaced by Nothing
 */
fun <T : Number> insertIntoOutProjection(a: Array<out T>, b: T) {
//    a[0] = b
}


fun main(vararg args: String) {
    val readOnlyArray: Array<out Number> = arrayOf(1, 2, 3)
//    readOnlyArray[0] = 0
    val writableArray: Array<Number> = readOnlyArray as Array<Number>
    writableArray[0] = 0
    println(readOnlyArray.asList())
//  Now the same for List
    val readOnlyList: List<Number> = listOf(1, 2, 3)
//    readOnlyList[0] = "d"
    val writableList = readOnlyList as MutableList<Number>
    writableList[0] = 0
    println(readOnlyList)
}

