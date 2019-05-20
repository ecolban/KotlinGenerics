package com.github.ecolban.kotlingenerics.option

sealed class Option<out T: Any> {
    abstract val value: T
    abstract fun <R: Any> map(f: (T) -> R?): Option<R>
    abstract fun <R: Any> flatMap(f: (T) -> Option<R>): Option<R>
}

data class Some<out T: Any>(override val value: T) : Option<T>() {
    override fun <R: Any> map(f: (T) -> R?): Option<R> {
        val y = f(value)
        return if (y != null) Some(y) else None
    }

    override fun <R: Any> flatMap(f: (T) -> Option<R>): Option<R> = f(value)
}

object None : Option<Nothing>() {

    override val value: Nothing
        get() {
            throw NoSuchElementException()
        }

    override fun <R: Any> map(f: (Nothing) -> R?) = None
    override fun <R: Any> flatMap(f: (Nothing) -> Option<R>) = None

    override fun toString() = "None"
}
