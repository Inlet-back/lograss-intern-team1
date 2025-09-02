package com.example.accounting.infrastructure

import com.example.accounting.domain.pl.PL
import com.example.accounting.domain.pl.PLRepository
import com.example.accounting.domain.journalEntry.JournalEntryRepository
import com.example.accounting.domain.account.AccountRepository
import org.springframework.stereotype.Repository

@Repository
class PLJDBCRepository(
    private val journalEntryRepository: JournalEntryRepository,
    private val accountRepository: AccountRepository,
) : PLRepository {
    override fun getPL(): PL {
        val headers = journalEntryRepository.list()
        val accounts = accountRepository.list()
        return PL.create(headers, accounts)
    }

    override fun getPLWithMonth(year: Int, month: Int): PL {
        val headers = journalEntryRepository.list()
            .filter { header ->
                // JournalEntryDate が value(LocalDate) を持つ想定。必要なら header.date.year に調整
                val d = header.date.value
                d.year == year && d.monthValue == month
            }
        val accounts = accountRepository.list()
        return PL.create(headers, accounts)
    }
}