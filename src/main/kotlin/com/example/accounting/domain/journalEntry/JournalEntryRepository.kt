package com.example.accounting.domain.journalEntry

interface JournalEntryRepository {
   fun list(): List<JournalEntryHeader>
}