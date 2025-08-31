import com.example.accounting.domain.account.Account
import com.example.accounting.domain.journalEntry.JournalEntryAmount
import com.example.accounting.domain.journalEntry.JournalEntryNumber
import com.example.accounting.domain.journalEntry.JournalEntryType

class JournalEntryDetail (
    val account:       Account,
    val journalEntryType:   JournalEntryType,
    val amount:             JournalEntryAmount,
) {
    companion object {
        fun create(
            account:       Account,
            journalEntryType:   JournalEntryType,
            amount:             JournalEntryAmount,
        ): JournalEntryDetail {
            return JournalEntryDetail(account, journalEntryType, amount)
        }

        fun reconstruct(
            account:            Account,
            journalEntryType:   JournalEntryType,
            amount:             JournalEntryAmount,
        ): JournalEntryDetail {
            return JournalEntryDetail(account, journalEntryType, amount)
        }
    }
      private fun validate(account: Account) {
            if (account.parentCode == null) {
                throw RuntimeException("親科目は科目として設定できません")
            }
         
        }

        fun getSignedAmount(): Int {
            if (journalEntryType == JournalEntryType.DEBIT) {
                return amount.value
            }
            return -amount.value
        }


}