package ru.netology

import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun isDiscount_card_correct() {
        // arrange
        val test_cardType = "Maestro"
        val test_transaction = 1500000000
        val test_monthTransactions = 300000
        val test_limitVkpayOnce = 1500000
        val test_limitVkpayMonth = 4000000
        val test_limitCardPerDay = 15000000
        val test_limitCardPerMonth = 60000000
        val test_limitMasterMaestro = 7500000

        // act
        val result = isDiscount(
            cardType = test_cardType,
            transaction = test_transaction,
            limitVkpayOnce= test_limitVkpayOnce,
            monthTransactions = test_monthTransactions,
            limitVkpayMonth = test_limitVkpayMonth,
            limitCardPerDay = test_limitCardPerDay,
            limitCardPerMonth = test_limitCardPerMonth,
            limitMasterMaestro = test_limitMasterMaestro)

        // assert
        assertEquals(result,"Перевод на сумму " + test_transaction/100 + "р. принят. Комиссия 0 коп.")

    }
    @Test
    fun isDiscount_card_incorrect() {
          // arrange
        val test_cardType = "Maestro"
        val test_transaction = 150000000
        val test_monthTransactions = 300000
        val test_limitVkpayOnce = 1500000
        val test_limitVkpayMonth = 4000000
        val test_limitCardPerDay = 15000000
        val test_limitCardPerMonth = 60000000
        val test_limitMasterMaestro = 7500000

        // act
        val result = isDiscount(
            cardType = test_cardType,
            transaction = test_transaction,
            limitVkpayOnce= test_limitVkpayOnce,
            monthTransactions = test_monthTransactions,
            limitVkpayMonth = test_limitVkpayMonth,
            limitCardPerDay = test_limitCardPerDay,
            limitCardPerMonth = test_limitCardPerMonth,
            limitMasterMaestro = test_limitMasterMaestro)

        // assert
        assertEquals(result,"Сумма привышает лимит на ежедневный перевод. Лимит в день 150000р")

    }

    @Test
    fun checkLimitsVkpay_is_correct() {
        // arrange
        val test_transaction = 150000
        val test_monthTransactions = 300000
        val test_limitVkpayOnce = 1500000
        val test_limitVkpayMonth = 4000000

        // act
        val result = checkLimitsVkpay(
            transaction = test_transaction,
            monthTransactions = test_monthTransactions,
            limitVkpayOnce = test_limitVkpayOnce,
            limitVkpayMonth = test_limitVkpayMonth
        )

        // assert
        assertEquals(result, "Перевод на сумму " + test_transaction / 100 + "р. принят. Комиссия 0 коп.")
    }

    @Test
    fun checkLimitsVkpay_is_monthLimit_exceed() {
        // arrange
        val test_transaction = 15000000
        val test_monthTransactions = 300000
        val test_limitVkpayOnce = 1500000
        val test_limitVkpayMonth = 4000000

        // act
        val result = checkLimitsVkpay(
            transaction = test_transaction,
            monthTransactions = test_monthTransactions,
            limitVkpayOnce = test_limitVkpayOnce,
            limitVkpayMonth = test_limitVkpayMonth
        )

        // assert
        assertEquals(result, "Сумма привышает лимит на разовый перевод. Сумма максимального разового перевода 15000р")
    }

    @Test
    fun checkLimitsVkpay_is_onceLimit_exceed() {
        // arrange
        //val cardType = "Vkpay"
        val test_transaction = 150000
        val test_monthTransactions = 30000000
        val test_limitVkpayOnce = 1500000
        val test_limitVkpayMonth = 4000000

        // act
        val result = checkLimitsVkpay(
            transaction = test_transaction,
            monthTransactions = test_monthTransactions,
            limitVkpayOnce = test_limitVkpayOnce,
            limitVkpayMonth = test_limitVkpayMonth
        )

        // assert
        assertEquals(result, "Превышен месячный лимит в 40000р")
    }

    @Test
    fun checkLimitsMasterMaestro_is_daylyLimit_exceed() {
        // arrange
        val test_transaction = 150000000
        val test_monthTransactions = 30000000
        val test_limitCardPerDay = 15000000
        val test_limitCardPerMonth = 60000000
        val test_limitMasterMaestro = 7500000

        // act
        val result = checkLimitsMasterMaestro(
            transaction = test_transaction,
            monthTransactions = test_monthTransactions,
            limitCardPerDay = test_limitCardPerDay,
            limitCardPerMonth = test_limitCardPerMonth,
            limitMasterMaestro = test_limitMasterMaestro
        )

        // assert
        assertEquals(result, "Сумма привышает лимит на ежедневный перевод. Лимит в день 150000р")
    }

    @Test
    fun checkLimitsMasterMaestro_is_monthlyLimit_exceed() {
        // arrange
        val test_transaction = 1500000
        val test_monthTransactions = 300000000
        val test_limitCardPerDay = 15000000
        val test_limitCardPerMonth = 60000000
        val test_limitMasterMaestro = 7500000

        // act
        val result = checkLimitsMasterMaestro(
            transaction = test_transaction,
            monthTransactions = test_monthTransactions,
            limitCardPerDay = test_limitCardPerDay,
            limitCardPerMonth = test_limitCardPerMonth,
            limitMasterMaestro = test_limitMasterMaestro
        )

        // assert
        assertEquals(result, "Превышен месячный лимит в 600000р")
    }

    @Test
    fun checkLimitsMasterMaestro_is_correct_noInterest() {
        // arrange
        val test_transaction = 1500000
        val test_monthTransactions = 3000000
        val test_limitCardPerDay = 15000000
        val test_limitCardPerMonth = 60000000
        val test_limitMasterMaestro = 7500000

        // act
        val result = checkLimitsMasterMaestro(
            transaction = test_transaction,
            monthTransactions = test_monthTransactions,
            limitCardPerDay = test_limitCardPerDay,
            limitCardPerMonth = test_limitCardPerMonth,
            limitMasterMaestro = test_limitMasterMaestro
        )

        // assert
        assertEquals(result, "Перевод на сумму " + test_transaction / 100 + "р. принят. Комиссия 0 коп.")
    }

    @Test
    fun checkLimitsMasterMaestro_is_correct_withInterest() {
        // arrange
        val test_transaction = 1500000
        val test_monthTransactions = 7600000
        val test_limitCardPerDay = 15000000
        val test_limitCardPerMonth = 60000000
        val test_limitMasterMaestro = 7500000
        val interest: Double = test_transaction * 0.006 + 2000
        // act
        val result = checkLimitsMasterMaestro(
            transaction = test_transaction,
            monthTransactions = test_monthTransactions,
            limitCardPerDay = test_limitCardPerDay,
            limitCardPerMonth = test_limitCardPerMonth,
            limitMasterMaestro = test_limitMasterMaestro
        )

        // assert
        assertEquals(result, "Перевод на сумму " + test_transaction / 100 + " р. принят. Комиссия $interest коп.")
    }

    @Test
    fun checkLimitsVisaMir_is_correct_daylyLimit_exceed() {
        // arrange
        val test_transaction = 150000000
        val test_monthTransactions = 7600000
        val test_limitCardPerDay = 15000000
        val test_limitCardPerMonth = 60000000
        val interest: Double = test_transaction * 0.006 + 2000

        // act
        val result = checkLimitsVisaMir(
            transaction = test_transaction,
            monthTransactions = test_monthTransactions,
            limitCardPerDay = test_limitCardPerDay,
            limitCardPerMonth = test_limitCardPerMonth
        )

        // assert
        assertEquals(result, "Сумма привышает лимит на ежедневный перевод. Лимит в день 150000р")
    }
    @Test
    fun checkLimitsVisaMir_is_correct_monthlyLimit_exceed() {
        // arrange
        val test_transaction = 150000
        val test_monthTransactions = 76000000
        val test_limitCardPerDay = 15000000
        val test_limitCardPerMonth = 60000000


        // act
        val result = checkLimitsVisaMir(
            transaction = test_transaction,
            monthTransactions = test_monthTransactions,
            limitCardPerDay = test_limitCardPerDay,
            limitCardPerMonth = test_limitCardPerMonth
        )

        // assert
        assertEquals(result, "Превышен месячный лимит в 600000р")
    }
    @Test
    fun checkLimitsVisaMir_is_min_interest() {
        // arrange
        val test_transaction = 150000
        val test_monthTransactions = 6000000
        val test_limitCardPerDay = 15000000
        val test_limitCardPerMonth = 60000000
        val interest = 3500.00

        // act
        val result = checkLimitsVisaMir(
            transaction = test_transaction,
            monthTransactions = test_monthTransactions,
            limitCardPerDay = test_limitCardPerDay,
            limitCardPerMonth = test_limitCardPerMonth
        )

        // assert
        assertEquals(result, "Перевод на сумму " + test_transaction/100 + " р. принят. Комиссия $interest коп.")
    }
    @Test
    fun checkLimitsVisaMir_is_normal_interest() {
        // arrange
        val test_transaction = 1500000
        val test_monthTransactions = 6000000
        val test_limitCardPerDay = 15000000
        val test_limitCardPerMonth = 60000000
        val interest = test_transaction * 0.0075

        // act
        val result = checkLimitsVisaMir(
            transaction = test_transaction,
            monthTransactions = test_monthTransactions,
            limitCardPerDay = test_limitCardPerDay,
            limitCardPerMonth = test_limitCardPerMonth
        )

        // assert
        assertEquals(result, "Перевод на сумму " + test_transaction/100 + " р. принят. Комиссия $interest коп.")
    }
}