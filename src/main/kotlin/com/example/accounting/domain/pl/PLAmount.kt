package com.example.accounting.domain.pl

class PLAmount (
    val amount: Int,
) {
    companion object {
        fun of(profitAmount: ProfitAmount, lossAmount: LossAmount): PLAmount {
            val amount = calcAmount(profitAmount, lossAmount)

            return PLAmount(amount)
        }
        private fun calcAmount (profitAmount: ProfitAmount, lossAmount: LossAmount): Int {
            
            
            return profitAmount.getAmount() - lossAmount.getAmount()
        }
    }


}