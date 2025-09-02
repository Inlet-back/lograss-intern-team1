package com.example.accounting.domain.pl

import com.example.accounting.domain.account.AccountName
import com.example.accounting.domain.account.AccountType
import com.example.accounting.domain.journalEntry.JournalEntryAmount

class ParentAccountWithAmount (
    val accountName: AccountName,
    val amount:      PLAccountAmount,
    val accountType: AccountType,
    val children: List<AccountWithAmount>,
) {
    companion object {
        fun create (
            accountName: AccountName,
            amount:      PLAccountAmount,
            accountType: AccountType,
            children: List<AccountWithAmount>,
        ): ParentAccountWithAmount {
            return ParentAccountWithAmount(accountName, amount, accountType, children)
        }

        fun reconstruct (
            accountName: AccountName,
            amount:      PLAccountAmount,
            accountType: AccountType,
            children: List<AccountWithAmount>,
        ): ParentAccountWithAmount {
            return ParentAccountWithAmount(accountName, amount, accountType, children)
        }
    }

    fun getAmount(
    ):Int {
        return amount.value
    }
}