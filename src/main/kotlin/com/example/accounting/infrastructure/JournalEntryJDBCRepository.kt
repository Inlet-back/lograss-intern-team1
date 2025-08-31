package com.example.accounting.infrastructure

import com.example.accounting.domain.journalEntry.*
import com.example.accounting.domain.department.Department
import com.example.accounting.domain.department.DepartmentCode
import com.example.accounting.domain.department.DepartmentName
import com.example.accounting.domain.account.Account
import com.example.accounting.domain.account.AccountCode
import com.example.accounting.domain.account.AccountName
import com.example.accounting.domain.account.AccountType
import jooq.tables.JournalEntryHeader.JOURNAL_ENTRY_HEADER
import jooq.tables.JournalEntryDetail.JOURNAL_ENTRY_DETAIL
import jooq.tables.Departments.DEPARTMENTS
import jooq.tables.Accounts.ACCOUNTS
import org.jooq.DSLContext
import org.springframework.stereotype.Repository


@Repository
class JournalEntryJDBCRepository(
    private val jooq: DSLContext,
) : JournalEntryRepository {
    override fun list(): List<JournalEntryHeader> {
        val result = jooq
            .select(
                JOURNAL_ENTRY_HEADER.JOURNAL_ENTRY_NUMBER,
                JOURNAL_ENTRY_HEADER.JOURNAL_ENTRY_DATE,
                JOURNAL_ENTRY_HEADER.DEPARTMENT_CODE,
                DEPARTMENTS.NAME,
                DEPARTMENTS.PARENT_DEPARTMENT_CODE,
                JOURNAL_ENTRY_DETAIL.ACCOUNT_CODE,
                ACCOUNTS.NAME,
                ACCOUNTS.ACCOUNT_TYPE,
                ACCOUNTS.PARENT_ACCOUNT_CODE,
                JOURNAL_ENTRY_DETAIL.JOURNAL_ENTRY_TYPE,
                JOURNAL_ENTRY_DETAIL.AMOUNT
            )
            .from(JOURNAL_ENTRY_HEADER)
            .innerJoin(JOURNAL_ENTRY_DETAIL)
                .on(JOURNAL_ENTRY_HEADER.JOURNAL_ENTRY_NUMBER.eq(JOURNAL_ENTRY_DETAIL.JOURNAL_ENTRY_NUMBER))
            .leftJoin(DEPARTMENTS)
                .on(JOURNAL_ENTRY_HEADER.DEPARTMENT_CODE.eq(DEPARTMENTS.DEPARTMENT_CODE))
            .leftJoin(ACCOUNTS)
                .on(JOURNAL_ENTRY_DETAIL.ACCOUNT_CODE.eq(ACCOUNTS.ACCOUNT_CODE))
            .orderBy(JOURNAL_ENTRY_HEADER.JOURNAL_ENTRY_DATE, JOURNAL_ENTRY_HEADER.JOURNAL_ENTRY_NUMBER)
            .fetch()

        return result
            .groupBy { record ->
                record.get(JOURNAL_ENTRY_HEADER.JOURNAL_ENTRY_NUMBER)
            }
            .map { (journalEntryNumber, records) ->
                val firstRecord = records.first()
                val department = Department.reconstruct(
                    DepartmentCode.of(firstRecord.get(JOURNAL_ENTRY_HEADER.DEPARTMENT_CODE)),
                    DepartmentName.of(firstRecord.get(DEPARTMENTS.NAME) ?: ""),
                    firstRecord.get(DEPARTMENTS.PARENT_DEPARTMENT_CODE)?.let { DepartmentCode.of(it) }                  
                )
                

                val details = records.map { record ->
                    JournalEntryDetail.reconstruct(
                        Account.reconstruct(
                            AccountCode.of(record.get(JOURNAL_ENTRY_DETAIL.ACCOUNT_CODE)),
                            AccountName.of(record.get(ACCOUNTS.NAME) ?: ""),
                            AccountType.valueOf(record.get(ACCOUNTS.ACCOUNT_TYPE) ?: "ASSET"),
                            record.get(ACCOUNTS.PARENT_ACCOUNT_CODE)?.let { AccountCode.of(it) }
                        ),
                        JournalEntryType.valueOf(record.get(JOURNAL_ENTRY_DETAIL.JOURNAL_ENTRY_TYPE).uppercase()),
                        JournalEntryAmount.of(record.get(JOURNAL_ENTRY_DETAIL.AMOUNT).toInt())
                    )
                }

                JournalEntryHeader.reconstruct(
                    JournalEntryDate.of(firstRecord.get(JOURNAL_ENTRY_HEADER.JOURNAL_ENTRY_DATE)),
                    JournalEntryNumber.of(firstRecord.get(JOURNAL_ENTRY_HEADER.JOURNAL_ENTRY_NUMBER)),
                    department,
                    details
                )
            }
    }
}

