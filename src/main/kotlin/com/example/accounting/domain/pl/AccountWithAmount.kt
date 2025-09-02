package com.example.accounting.domain.pl

import com.example.accounting.domain.account.AccountName

class AccountWithAmount (
    val accountName: AccountName,
    val amount:      PLAccountAmount,
) {
    companion object {
        fun create (
            accountName: AccountName,
            amount:      PLAccountAmount,
        ): AccountWithAmount {
            return AccountWithAmount(accountName, amount)
        }

        fun reconstruct (
            accountName: AccountName,
            amount:      PLAccountAmount,
        ): AccountWithAmount {
            return AccountWithAmount(accountName, amount)
        }
    }

    fun getAmount(
    ):Int {
        return amount.value
    }
}