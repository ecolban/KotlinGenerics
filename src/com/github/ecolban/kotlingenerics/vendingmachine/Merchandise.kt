package com.github.ecolban.kotlingenerics.vendingmachine

interface Merchandise {
    val price: Int

}

sealed class ChocolateBar(override val price: Int) : Merchandise {
    override fun toString(): String {
        return javaClass.simpleName
    }
}

object Snickers : ChocolateBar(price = 125)

object KitKat : ChocolateBar(price = 75)

object Hershey : ChocolateBar(price = 100)


sealed class SnackFood(override val price: Int) : Merchandise {
    override fun toString(): String {
        return javaClass.simpleName
    }
}

object Chips : SnackFood(price = 200)

object Peanuts : SnackFood(price = 150)

object TrailMix : SnackFood(price = 200)
