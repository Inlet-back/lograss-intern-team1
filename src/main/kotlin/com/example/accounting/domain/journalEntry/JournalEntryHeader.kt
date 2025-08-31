package com.example.accounting.domain.journalEntry
import JournalEntryDetail
import com.example.accounting.domain.department.Department

class JournalEntryHeader(
    val date: JournalEntryDate,
    val number: JournalEntryNumber,
    val department: Department,
    val detail: List<JournalEntryDetail>,
) {
    companion object {
        fun create(
            date: JournalEntryDate,
            number: JournalEntryNumber,
            department: Department,
            detail: List<JournalEntryDetail>,
        ): JournalEntryHeader {
            return JournalEntryHeader(date, number, department, detail)
        }

        fun reconstruct(
            date: JournalEntryDate,
            number: JournalEntryNumber,
            department: Department,
            detail: List<JournalEntryDetail>,
        ): JournalEntryHeader {
            return JournalEntryHeader(date, number, department, detail)
        }
    }
}

