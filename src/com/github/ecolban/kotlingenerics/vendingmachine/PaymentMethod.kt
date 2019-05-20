package com.github.ecolban.kotlingenerics.vendingmachine

interface PaymentMethod {

    fun pay(amount: Int)
}

sealed class Cash(var amount: Int) : PaymentMethod {

    override fun pay(amount: Int) {
        if (amount > this.amount) {
            println("Sorry, not enough cash")
        } else {
            this.amount -= amount
            println("Pay $%.2f in cash".format(amount / 100.0))
        }
    }
}

class DollarBills(numBills: Int) : Cash(numBills * 100)

class Card(var paymentLimit: Int) : PaymentMethod {

    override fun pay(amount: Int) {
        if (amount > paymentLimit) {

        } else {
            paymentLimit -= amount
            println("Pay %.2f with card".format(amount / 100.0))
        }
    }
}

