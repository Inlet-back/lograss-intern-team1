package com.example.accounting.usecase

import com.example.accounting.domain.journalEntry.JournalEntryHeader
import com.example.accounting.domain.journalEntry.JournalEntryRepository
import org.springframework.stereotype.Service

@Service
class ListJournalEntryUseCase(
    private val journalEntryRepository: JournalEntryRepository,
) {
    fun execute(): List<JournalEntryHeader> {
        return journalEntryRepository.list()
    }
}
