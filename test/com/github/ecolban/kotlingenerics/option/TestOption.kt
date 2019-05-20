package com.github.ecolban.kotlingenerics.option

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class TestOption {

    @Test
    fun `test Option on indexOf`() {
        fun <T> List<T>.indexOfOption(x: T): Option<Int> {
            val idx = this.indexOf(x)
            return if (idx >= 0) Some(idx) else None
        }

        val myList: List<String> = listOf("5", "21", "42")

        assertEquals(Some(0), myList.indexOfOption("5"))
        assertEquals(Some(1), myList.indexOfOption("21"))
        assertEquals(2, myList.indexOfOption("42").value)
        assertEquals(None, myList.indexOfOption("10"))
        assertFailsWith<NoSuchElementException> {
            myList.indexOfOption(10).value
        }
    }

    @Test
    fun `test Option on max`() {
        fun <T : Comparable<T>> Collection<T>.maxOption(): Option<T> {
            val maxValue = this.max()
            return if (maxValue != null) Some(maxValue) else None
        }

        assertEquals(None, emptyList<Int>().maxOption())
        assertTrue(emptyList<Int>().maxOption() is None)
        assertTrue(listOf(1, 2, 3).maxOption() is Some<*>)
        assertFailsWith<NoSuchElementException> {
            emptyList<Int>().maxOption().value
        }
        assertEquals(Some(5), listOf(1, 2, 5, 3).maxOption())
        assertEquals(5, listOf(1, 2, 5, 3).maxOption().value)
    }

    @Test
    fun `test toString`() {
        assertEquals("Some(value=5)", Some(5).toString())
        assertEquals("None", None.toString())
    }

    @Test
    fun `test map and flatMap`() {
        assertEquals(None, None.map { n: Int -> 2 * n })
        assertEquals(Some(6), Some(3).map { 2 * it })
        assertEquals(None, Some(3).map { null })
        assertEquals(None, Some(3).flatMap { None })

        fun f(opt: Option<Int>): Option<Int> = opt.map { if (it % 2 == 0) it / 2 else null }
        val lst = (1..10).asSequence()
                .map { Some(it) }
                .map(::f)
                .map(::f)
                .map(::f)
                .map { if (it != None) it.value else null }
                .toList()

        assertEquals(listOf(null, null, null, null, null, null, null, 1, null, null), lst)

        // Equivalent not using options
        fun g(n: Int?): Int? = if (n != null && n % 2 == 0) n / 2 else null
        val lst2 = (1..10).asSequence()
                .map(::g)
                .map(::g)
                .map(::g)
                .toList()

        assertEquals(listOf(null, null, null, null, null, null, null, 1, null, null), lst2)
    }


    @Test
    fun `test covariance`() {
        val opt = Some(1)
        assertTrue { opt is Option<Int> }
        assertTrue { opt is Some<Int> }
        assertTrue { opt is Option<Number> }
        assertTrue { opt is Some<Number> }
        assertTrue { opt is Option<Any> }
    }

}
