package com.example.accounting.domain.journalEntry

class JournalEntryAmount(
    val value: Long,
) {
    companion object {


        fun of(value: Long): JournalEntryAmount {
            validate(value)
            return JournalEntryAmount(value)
        }

        private fun validate(value: Long) {
            if (value <= 0) {
                throw RuntimeException("金額は0以上の数字で入力してください")
            }
        }
    }

}