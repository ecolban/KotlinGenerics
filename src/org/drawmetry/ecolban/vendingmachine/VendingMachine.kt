package org.drawmetry.ecolban.vendingmachine

class VendingMachine<in T : PaymentMethod, in U : Merchandise>(private val merchandises: MutableMap<U, Int>) {

    fun <R : U> buy(item: R, payMethod: T): R? = merchandises[item]?.let {
        payMethod.pay(item.price)
        println("Bought $item for $%.2f".format(item.price / 100.0))
        if (it == 1) {
            merchandises.remove(item)
        } else {
            merchandises[item] = it - 1
        }
        item
    } ?: run {
        println("Sorry, we don't sell $item")
        null
    }
}

fun main(args: Array<String>) {
    val merchandises = mutableMapOf(KitKat to 4, Snickers to 6, Chips to 1, TrailMix to 7)
    val vendingMachine = VendingMachine<PaymentMethod, Merchandise>(merchandises)
    val vendingMachineBobWants: VendingMachine<DollarBills, ChocolateBar> = vendingMachine
    val myCash = DollarBills(3)
    val myChocolate = vendingMachineBobWants.buy(KitKat, myCash)
    val myChips = vendingMachine.buy(Chips, myCash)
    myChocolate?.let { println("I've got $it") }
}

