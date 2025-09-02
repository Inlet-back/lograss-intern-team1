package com.example.accounting.domain.pl

class ProfitAmount (
    val amount: Int
    ) {
    companion object {
        fun of (amount: Int): ProfitAmount{
            return ProfitAmount(amount)
        }
    }

    fun getAmount(): Int {
        return amount
    }
}