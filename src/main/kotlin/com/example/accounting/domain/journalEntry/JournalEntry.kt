package com.example.accounting.domain.journalEntry
import package com.example.accounting.domain.DepartmentCode

class JournalEntryHeader(
    val date: JournalEntryDate,
    val number: JournalEntryNumber,
    val DepartmentCode: DepartmentCode,
    val detail: List<JournalEntryDetail>,
) {
    companion object {
        fun create(
            val date: JournalEntryDate,
            val number: JournalEntryNumber,
            val DepartmentCode: DepartmentCode,
            val journalEntryDetail: List<JournalEntryDetail>,
        ): JournalEntry {
            return JournalEntry(date, number, debit, credit)
        }

        fun reconstruct(
            val date: JournalEntryDate,
            val number: JournalEntryNumber,
            val debit: List<JournalEntryDebit>,
            val credit: List<JournalEntryCredit>,
        ): JournalEntry {
            return JournalEntry(date, number, debit, credit)
        }
    }
}

class JournalEntryDetail (
    val JournalEntryNumber: JournalEntryNumber,
    val name:               AccountName,
    val type:               AccountType,
    val amount:             AccountAmount,
) {

}