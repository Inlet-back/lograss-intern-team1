package com.example.accounting.domain.journalEntry

class JournalEntryNumber(
    val value: String
) {
    companion object {
        private const val length = 10

        fun of(value: String): JournalEntryNumber {
            validate(value)
            return JournalEntryNumber(value)
        }

        private fun validate(value: String) {
            if (value.isBlank() || value.isEmpty()) {
                throw RuntimeException("仕訳Noは空欄にできません")
            }
            if (value.length != length) {
                throw RuntimeException("仕訳Noは${length}文字で入力してください")
            }
            if (!value.matches(Regex("^[0-9]{10}$"))) {
                throw RuntimeException("仕訳Noは10桁の数字で入力してください")
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        other as JournalEntryNumber

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return value
    }
}