package com.example.accounting.domain.journalEntry
import java.time.LocalDate

class JournalEntryDate(
    val value: LocalDate
) {
    companion object {
        fun of(value: LocalDate): JournalEntryDate {
            validate(value)
            return JournalEntryDate(value)
        }

        private fun validate(value: LocalDate) {
            if (value.isBlank() || value.isEmpty()) {
                throw RuntimeException("日付は空欄にできません")
            }
        }
    }
}