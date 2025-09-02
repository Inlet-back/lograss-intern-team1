package com.example.accounting.domain.pl

import com.example.accounting.domain.account.AccountName
import com.example.accounting.domain.account.AccountCode

class AccountWithAmount (
    val parentCode: AccountCode?,
    val accountName: AccountName,
    val amount:      PLAccountAmount,
) {
    companion object {
        fun create (
            parentCode: AccountCode?, 
            accountName: AccountName,
            amount:      PLAccountAmount,
        ): AccountWithAmount {
            return AccountWithAmount(parentCode, accountName, amount)
        }

        fun reconstruct (
            parentCode: AccountCode?,
            accountName: AccountName,
            amount:      PLAccountAmount,
        ): AccountWithAmount {
            return AccountWithAmount(parentCode, accountName, amount)
        }
    }

    fun getAmount(
    ):Int {
        return amount.value
    }
}