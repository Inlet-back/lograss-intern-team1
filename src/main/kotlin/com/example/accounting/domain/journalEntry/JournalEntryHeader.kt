package com.example.accounting.domain.journalEntry
import JournalEntryDetail
import com.example.accounting.domain.department.DepartmentCode

class JournalEntryHeader(
    val date: JournalEntryDate,
    val number: JournalEntryNumber,
    val departmentCode: DepartmentCode,
    val detail: List<JournalEntryDetail>,
) {
    companion object {
        fun create(
            date: JournalEntryDate,
            number: JournalEntryNumber,
            departmentCode: DepartmentCode,
            detail: List<JournalEntryDetail>,
        ): JournalEntryHeader {
            return JournalEntryHeader(date, number, departmentCode, detail)
        }

        fun reconstruct(
            date: JournalEntryDate,
            number: JournalEntryNumber,
            departmentCode: DepartmentCode,
            detail: List<JournalEntryDetail>,
        ): JournalEntryHeader {
            return JournalEntryHeader(date, number, departmentCode, detail)
        }
    }
}

