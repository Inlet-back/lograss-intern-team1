package com.example.accounting.apiController

import com.example.accounting.domain.journalEntry.JournalEntryHeader
import com.example.accounting.usecase.ListJournalEntryUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController("apiJournalEntryController")
@RequestMapping("/api")
class JournalEntryController(
    private val listUseCase: ListJournalEntryUseCase,
) {

    @GetMapping("/journal-entries")
    fun list(): ResponseEntity<List<JournalEntryHeader>> {
        val journalEntries = listUseCase.execute()
        return ResponseEntity.ok(journalEntries)
    }

} 
