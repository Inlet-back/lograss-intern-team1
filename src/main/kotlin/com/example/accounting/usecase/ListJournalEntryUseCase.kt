package com.example.accounting.usecase

import com.example.accounting.domain.journalEntry.JournalEntryHeader
import com.example.accounting.domain.journalEntry.JournalEntryRepository
import com.example.accounting.domain.journalEntry.JournalEntryDate
import com.example.accounting.domain.journalEntry.JournalEntryNumber
import com.example.accounting.domain.journalEntry.JournalEntryType
import com.example.accounting.domain.journalEntry.JournalEntryAmount
import com.example.accounting.domain.department.DepartmentName
import com.example.accounting.domain.account.AccountName
import java.time.LocalDate
import org.springframework.stereotype.Service

@Service
class ListJournalEntryUseCase(
    private val journalEntryRepository: JournalEntryRepository,
) {
    fun execute(): List<ListJournalEntryResponse> {
        val journalEntries = journalEntryRepository.list()

        var responses: MutableList<ListJournalEntryResponse> = mutableListOf()

        for (entry in journalEntries) {
            val response = ListJournalEntryResponse(
                date = entry.date,
                journalEntryNumber = entry.number,
                departmentName = entry.department.name,
                details = entry.details.map { detail ->
                    JournalDetailResponse(
                        journalEntryType = detail.journalEntryType,
                        accountName = detail.account.name,
                        amount = detail.amount
                    )
                }
            )
            responses.add(response)
        }

        return responses
    }
}

class ListJournalEntryResponse(
    val date: JournalEntryDate,
    val journalEntryNumber: JournalEntryNumber,
    val departmentName: DepartmentName,
    val details: List<JournalDetailResponse>
)

class JournalDetailResponse(
    val journalEntryType: JournalEntryType,
    val accountName: AccountName,
    val amount: JournalEntryAmount
)
