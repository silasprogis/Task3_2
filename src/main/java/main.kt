package ru.netology

val cardType = "Maestro"
val monthTransactions = 7400000
val transaction = 15000000
val limitMasterMaestro = 7500000
val limitCardPerDay = 15000000
val limitCardPerMonth = 60000000
val limitVkpayOnce = 1500000
val limitVkpayMonth = 4000000

fun main () {
    print(isDiscount(cardType, transaction, limitVkpayOnce, monthTransactions, limitVkpayMonth,
        limitCardPerDay, limitCardPerMonth, limitMasterMaestro))
}

fun isDiscount (cardType: String, transaction: Int, limitVkpayOnce: Int, monthTransactions: Int, limitVkpayMonth: Int,
                limitCardPerDay: Int, limitCardPerMonth: Int, limitMasterMaestro: Int):String {
    when (cardType) {
        "Visa", "Мир" -> return checkLimitsVisaMir(transaction, limitCardPerDay, monthTransactions, limitCardPerMonth)
        "Mastercard", "Maestro" ->return checkLimitsMasterMaestro(transaction,limitCardPerDay, monthTransactions,
            limitCardPerMonth, limitMasterMaestro)
        else ->return checkLimitsVkpay(transaction, limitVkpayOnce, monthTransactions, limitVkpayMonth)
    }

}
fun checkLimitsVkpay (transaction: Int, limitVkpayOnce: Int, monthTransactions: Int, limitVkpayMonth: Int): String {
    if (transaction > limitVkpayOnce && monthTransactions < limitVkpayMonth) {
        println("Сумма привышает лимит на разовый перевод. Сумма максимального разового перевода 15000р")
        return "Сумма привышает лимит на разовый перевод. Сумма максимального разового перевода 15000р"
    } else if (monthTransactions > limitVkpayMonth) {
        println("Превышен месячный лимит в 40000р")
        return "Превышен месячный лимит в 40000р"
    } else {
        println("Перевод на сумму " + transaction/100 + "р. принят. Комиссия 0 коп.")
        return "Перевод на сумму " + transaction/100 + "р. принят. Комиссия 0 коп."
    }
}

fun checkLimitsMasterMaestro (
    transaction: Int,
    limitCardPerDay: Int,
    monthTransactions: Int,
    limitCardPerMonth: Int,
    limitMasterMaestro: Int): String {
    if (transaction > limitCardPerDay && monthTransactions < limitCardPerMonth) {
        println("Сумма привышает лимит на ежедневный перевод. Лимит в день 150000р")
        return "Сумма привышает лимит на ежедневный перевод. Лимит в день 150000р"
    } else if (monthTransactions > limitCardPerMonth) {
        println("Превышен месячный лимит в 600000р")
        return "Превышен месячный лимит в 600000р"
    } else if (monthTransactions < limitMasterMaestro) {
        println("Перевод на сумму " + transaction/100 + "р. принят. Комиссия 0 коп.")
        return "Перевод на сумму " + transaction/100 + "р. принят. Комиссия 0 коп."
    } else {
        val interest: Double = transaction * 0.006 + 2000
        println("Перевод на сумму " + transaction/100 + " р. принят. Комиссия $interest коп.")
        return "Перевод на сумму " + transaction/100 + " р. принят. Комиссия $interest коп."
    }
}

fun checkLimitsVisaMir (transaction: Int, limitCardPerDay: Int, monthTransactions: Int, limitCardPerMonth: Int): String {
    if (transaction > limitCardPerDay && monthTransactions < limitCardPerMonth) {
        println("Сумма привышает лимит на ежедневный перевод. Лимит в день 150000р")
        return "Сумма привышает лимит на ежедневный перевод. Лимит в день 150000р"
    } else if (monthTransactions > limitCardPerMonth) {
        println("Превышен месячный лимит в 600000р")
        return "Превышен месячный лимит в 600000р"
    } else {
        val interest: Double = if ((transaction * 0.0075) > 3500.00) (transaction * 0.0075) else 3500.00
        println("Перевод на сумму " + transaction/100 + " р. принят. Комиссия $interest коп.")
        return "Перевод на сумму " + transaction/100 + " р. принят. Комиссия $interest коп."
    }
}
