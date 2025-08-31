package com.example.accounting.domain.journalEntry
import JournalEntryDetail
import com.example.accounting.domain.department.Department

class JournalEntryHeader(
    val date: JournalEntryDate,
    val number: JournalEntryNumber,
    val department: Department,
    val details: List<JournalEntryDetail>,
) {
    companion object {
        fun create(
            date: JournalEntryDate,
            number: JournalEntryNumber,
            department: Department,
            details: List<JournalEntryDetail>,
        ): JournalEntryHeader {
            validate(department,details)
            return JournalEntryHeader(date, number, department, details)
        }

        fun reconstruct(
            date: JournalEntryDate,
            number: JournalEntryNumber,
            department: Department,
            details: List<JournalEntryDetail>,
        ): JournalEntryHeader {
            return JournalEntryHeader(date, number, department, details)
        }
    
     private fun validate(department: Department, details: List<JournalEntryDetail>) {
            if (department.parentCode != null) {
                throw RuntimeException("親部署は部署として設定できません")
            }
            if(!checkDetailAmount(details)){
                throw RuntimeException("借方と貸方の合計金額が一致していません")
            }
        }

    private fun checkDetailAmount(details: List<JournalEntryDetail>): Boolean {
            if(details.isEmpty())
                return  false
            var sum: Int = 0
            for (detail in details) {
                sum += detail.getSignedAmount()
            }

            return sum == 0
        }
    }

}

