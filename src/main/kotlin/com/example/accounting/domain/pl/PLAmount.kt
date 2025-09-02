package com.example.accounting.domain.pl

class PLAmount (
    val amount: Int,
) {
    companion object {
        fun of(profitAmount: Int, lossAmount: Int): PLAmount {
            val amount = calcAmount(profitAmount, lossAmount)

            return PLAmount(amount)
        }
        private fun calcAmount (profitAmount: Int, lossAmount: Int): Int {
            
            
            return profitAmount - lossAmount
        }
    }


}