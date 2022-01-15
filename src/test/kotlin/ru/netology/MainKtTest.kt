package ru.netology

import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun isDiscount_card_correct() {
        // arrange
        val cardType = "Maestro"
        val transaction = 15000000

        // act
        isDiscount(
            cardType = cardType
        )

        // assert
        assertEquals(isDiscount(cardType),"Перевод на сумму " + transaction/100 + "р. принят. Комиссия 0 коп.")

    }

    @Test
    fun checkLimitsVkpay_is_correct() {
        // arrange
        val cardType = "Vkpay"
        val transaction = 15000000
        val monthTransactions = 7400000
        val limitVkpayOnce = 1500000
        val limitVkpayMonth = 4000000

        // act
        checkLimitsVkpay()

        // assert
        assertEquals(checkLimitsVkpay(),"Превышен месячный лимит в 40000р")


    }
    @Test
    fun checkLimitsVisaMir_is_correct() {
        // arrange
        val cardType = "Visa"

        // act
        checkLimitsVisaMir()

        // assert
        assertEquals(checkLimitsVisaMir(),"Перевод на сумму 150000 р. принят. Комиссия 112500.0 коп")
    }

}