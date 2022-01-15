package ru.netology

import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun isDiscount_card_correct() {
        // arrange
        val cardType = "Maestro"
        val transaction = 15000000
        /*val monthTransactions = 7400000

        val limitMasterMaestro = 7500000
        val limitCardPerDay = 15000000
        val limitCardPerMonth = 60000000
        val limitVkpayOnce = 1500000
        val limitVkpayMonth = 4000000*/

        // act
        isDiscount(
            cardType = cardType
        )

        // assert
        assertEquals(isDiscount(cardType),"Перевод на сумму " + transaction/100 + "р. принят. Комиссия 0 коп.")

    }
}