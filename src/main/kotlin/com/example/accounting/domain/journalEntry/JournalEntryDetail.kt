import com.example.accounting.domain.account.AccountCode
import com.example.accounting.domain.journalEntry.JournalEntryAmount
import com.example.accounting.domain.journalEntry.JournalEntryNumber
import com.example.accounting.domain.journalEntry.JournalEntryType

class JournalEntryDetail (
    val journalEntryNumber: JournalEntryNumber,
    val accountCode:        AccountCode,
    val journalEntryType:   JournalEntryType,
    val amount:             JournalEntryAmount,
) {
    companion object {
        fun create(
            journalEntryNumber: JournalEntryNumber,
            accountCode:        AccountCode,
            journalEntryType:   JournalEntryType,
            amount:             JournalEntryAmount,
        ): JournalEntryDetail {
            return JournalEntryDetail(journalEntryNumber, accountCode, journalEntryType, amount)
        }

        fun reconstruct(
            journalEntryNumber: JournalEntryNumber,
            accountCode:        AccountCode,
            journalEntryType:   JournalEntryType,
            amount:             JournalEntryAmount,
        ): JournalEntryDetail {
            return JournalEntryDetail(journalEntryNumber, accountCode, journalEntryType, amount)
        }
    }

}