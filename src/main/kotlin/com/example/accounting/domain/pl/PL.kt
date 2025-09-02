package com.example.accounting.domain.pl

import com.example.accounting.domain.journalEntry.JournalEntryHeader
import com.example.accounting.domain.journalEntry.JournalEntryType
import com.example.accounting.domain.journalEntry.JournalEntryAmount
import com.example.accounting.domain.account.Account
import com.example.accounting.domain.account.AccountType


class PL(
    val plAmount: PLAmount,
    val parentAccountWithAmountList: MutableList<ParentAccountWithAmount>,
    val accountWithAmountList: MutableList<AccountWithAmount>,
) {
    companion object {
        fun create(
          journalEntryHeaders: List<JournalEntryHeader>
          accountList: List<Account>
        ): PL {
            var accountWithAmountList = makeAccountWithAmountList(journalEntryHeaders, accountList)

            var parentAccountWithAmountList = makeParentAccountWithAmountList(accountWithAmountList, accountList)
            var profitAmount: Int = calcParentAccountWithAmountSum(parentAccountWithAmountList)

            
            
            // var parentAmountSum = calcparentAmountSum(parentAccountWithAmountList)
            var plAmount = calcPlAmount(profitAmount, lossAmount)
            return PL(plAmount, profitAmount, lossAmount, parentAccountWithAmountList, accountWithAmountList)
        }

        fun reconstruct(
            plAmount: PLAmount,
            // val profitAmount: Int,
            // val lossAmount: Int,
            parentAccountWithAmountList: MutableList<ParentAccountWithAmount>,
            accountWithAmountList: MutableList<AccountWithAmount>,
        ): PL {
            return PL(plAmount, parentAccountWithAmountList, accountWithAmountList)
        }
        private fun calcPlAmount(profitAmount: ProfitAmount, lossAmount: LossAmount):PLAmount  {
            return PLAmount.of(profitAmount, lossAmount)
        }
        private fun calcParentAccountWithAmountSum(parentAccountWithAmountList: MutableList<ParentAccountWithAmount>, accountType: AccountType): Int {
            var sumAmount: Int = 
            
            return 0
        }
        private fun makeParentAccountWithAmountList(accountWithAmountList: MutableList<AccountWithAmount>, accountList: List<Account>): MutableList<ParentAccountWithAmount> {
            var parentAccountWithAmountList: MutableList<ParentAccountWithAmount> = mutableListOf()
            
            for(account in accountList) {
                
                if(account.parentCode != null) {
                    continue 
                }
                
                // P/L対象科目（収益・費用）のみ処理
                if(account.accountType != AccountType.PROFIT && account.accountType != AccountType.LOSS) {
                    continue
                }
                
                var sumAmount: Int = 0

                // 該当する親科目の子科目の金額を合計
                for(accountWithAmount in accountWithAmountList) {
                    val childAccount = accountList.find { it.name.value == accountWithAmount.accountName.value }
                    if(childAccount?.parentCode == account.code && childAccount.accountType == account.accountType) {
                        sumAmount += accountWithAmount.amount.value
                    }
                }
                


                // Insert made list
                val parentAccountWithAmount = ParentAccountWithAmount.create(account.name, JournalEntryAmount.of(sumAmount), account.accountType)
                parentAccountWithAmountList.add(parentAccountWithAmount)
            }
            return parentAccountWithAmountList
        }
        private fun makeAccountWithAmountList(journalEntryHeaderList: List<JournalEntryHeader>, accountList: List<Account>): MutableList<AccountWithAmount> {
            // 科目と金額をもったリストを作成
            var accountWithAmountList: MutableList<AccountWithAmount> = mutableListOf()
            
            for(account in accountList) {
                
                if(account.parentCode == null) {
                    continue 
                }
                var sumAmount: Int = 0

                for(journalEntryHeader in journalEntryHeaderList) {
                    for(journalEntryDetail in journalEntryHeader.details) {
                        if(journalEntryDetail.account.name == account.name) {
                            if((journalEntryDetail.journalEntryType == JournalEntryType.DEBIT && journalEntryDetail.account.accountType == AccountType.PROFIT) || (journalEntryDetail.journalEntryType == JournalEntryType.CREDIT && journalEntryDetail.account.accountType == AccountType.LOSS)) {
                                sumAmount += journalEntryDetail.amount.value
                            } else if((journalEntryDetail.journalEntryType == JournalEntryType.CREDIT && journalEntryDetail.account.accountType == AccountType.PROFIT) || (journalEntryDetail.journalEntryType == JournalEntryType.DEBIT && journalEntryDetail.account.accountType == AccountType.LOSS)) {
                                sumAmount -= journalEntryDetail.amount.value
                            }
                        
                        }
                    }
                }
                


                // Insert made list
                val accountWithAmount = AccountWithAmount.create(account.name, JournalEntryAmount.of(sumAmount))
                accountWithAmountList.add(accountWithAmount)
            }


            
            return accountWithAmountList
        }
        }
    }