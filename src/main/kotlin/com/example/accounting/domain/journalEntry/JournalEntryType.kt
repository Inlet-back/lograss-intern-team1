package com.example.accounting.domain.journalEntry

enum class JournalEntryType {
    DEBIT,
    CREDIT;
  

    companion object {
        fun of(value: String): JournalEntryType {
            return when (value) {
                "DEBIT" -> DEBIT
                "CREDIT" -> CREDIT
                else -> throw RuntimeException("DEBIT, CREDITで入力してください")
            }
        }
    }
}
