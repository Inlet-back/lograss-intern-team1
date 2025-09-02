package com.example.accounting.domain.pl

import com.example.accounting.domain.account.AccountName
import com.example.accounting.domain.journalEntry.JournalEntryAmount

class AccountWithAmount (
    val accountName: AccountName,
    val amount:      JournalEntryAmount,
) {
    companion object {
        fun create (
            accountName: AccountName,
            amount:      JournalEntryAmount,
        ): AccountWithAmount {
            return AccountWithAmount(accountName, amount)
        }

        fun reconstruct (
            accountName: AccountName,
            amount:      JournalEntryAmount,
        ): AccountWithAmount {
            return AccountWithAmount(accountName, amount)
        }
    }

    fun getAmount(
    ):Int {
        return amount.value
    }
}