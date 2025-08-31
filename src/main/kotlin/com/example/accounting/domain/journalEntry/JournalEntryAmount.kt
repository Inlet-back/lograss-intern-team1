package com.example.accounting.domain.journalEntry

class JournalEntryAmount(
    val value: Int,
) {
    companion object {


        fun of(value: Int): JournalEntryAmount {
            validate(value)
            return JournalEntryAmount(value)
        }

        private fun validate(value: Int) {
            if (value <= 0) {
                throw RuntimeException("金額は0以上の数字で入力してください")
            }
        }
    }

}