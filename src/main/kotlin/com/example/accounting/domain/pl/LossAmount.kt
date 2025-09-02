package com.example.accounting.domain.pl

class LossAmount (
    val amount: Int
    ) {
    companion object {
        fun of (amount: Int): LossAmount{
            return LossAmount(amount)
        }
    }

    fun getAmount(): Int {
        return amount
    }
}