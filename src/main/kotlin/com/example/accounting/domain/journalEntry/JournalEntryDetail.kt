import com.example.accounting.domain.account.Account
import com.example.accounting.domain.journalEntry.JournalEntryAmount
import com.example.accounting.domain.journalEntry.JournalEntryNumber
import com.example.accounting.domain.journalEntry.JournalEntryType

class JournalEntryDetail (
    val journalEntryNumber: JournalEntryNumber,
    val account:       Account,
    val journalEntryType:   JournalEntryType,
    val amount:             JournalEntryAmount,
) {
    companion object {
        fun create(
            journalEntryNumber: JournalEntryNumber,
            account:       Account,
            journalEntryType:   JournalEntryType,
            amount:             JournalEntryAmount,
        ): JournalEntryDetail {
            return JournalEntryDetail(journalEntryNumber, account, journalEntryType, amount)
        }

        fun reconstruct(
            journalEntryNumber: JournalEntryNumber,
            account:            Account,
            journalEntryType:   JournalEntryType,
            amount:             JournalEntryAmount,
        ): JournalEntryDetail {
            return JournalEntryDetail(journalEntryNumber, account, journalEntryType, amount)
        }
    }

}