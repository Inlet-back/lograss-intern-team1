package com.example.accounting.domain.pl

import com.example.accounting.domain.account.AccountName
import com.example.accounting.domain.account.AccountType
import com.example.accounting.domain.journalEntry.JournalEntryAmount

class ParentAccountWithAmount (
    val accountName: AccountName,
    val amount:      JournalEntryAmount,
    val accountType: AccountType,
) {
    companion object {
        fun create (
            accountName: AccountName,
            amount:      JournalEntryAmount,
            accountType: AccountType,
        ): ParentAccountWithAmount {
            return ParentAccountWithAmount(accountName, amount, accountType)
        }

        fun reconstruct (
            accountName: AccountName,
            amount:      JournalEntryAmount,
            accountType: AccountType,
        ): ParentAccountWithAmount {
            return ParentAccountWithAmount(accountName, amount, accountType)
        }
    }

    fun getAmount(
    ):Int {
        return amount.value
    }
}