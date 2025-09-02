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
          journalEntryHeaders: List<JournalEntryHeader>,
          accountList: List<Account>
        ): PL {
            var accountWithAmountList = makeAccountWithAmountList(journalEntryHeaders, accountList)

            var parentAccountWithAmountList = makeParentAccountWithAmountList(accountWithAmountList, accountList)
            var profitAmount: Int = calcParentAccountWithAmountSum(parentAccountWithAmountList, AccountType.PROFIT)
            var lossAmount: Int = calcParentAccountWithAmountSum(parentAccountWithAmountList, AccountType.LOSS)

            var plAmount = makePlAmount(profitAmount, lossAmount)
            return PL(plAmount, parentAccountWithAmountList, accountWithAmountList)
        }

        fun reconstruct(
            journalEntryHeaders: List<JournalEntryHeader>,
          accountList: List<Account>
        ): PL {
            var accountWithAmountList = makeAccountWithAmountList(journalEntryHeaders, accountList)

            var parentAccountWithAmountList = makeParentAccountWithAmountList(accountWithAmountList, accountList)

            var profitAmount: Int = calcParentAccountWithAmountSum(parentAccountWithAmountList, AccountType.PROFIT)
            var lossAmount: Int = calcParentAccountWithAmountSum(parentAccountWithAmountList, AccountType.LOSS)

            var plAmount = makePlAmount(profitAmount, lossAmount)
            return PL(plAmount, parentAccountWithAmountList, accountWithAmountList)
        }

        private fun makePlAmount(profitAmount: Int, lossAmount: Int):PLAmount  {
            return PLAmount.of(profitAmount, lossAmount)
        }
        private fun calcParentAccountWithAmountSum(parentAccountWithAmountList: MutableList<ParentAccountWithAmount>, accountType: AccountType): Int {
            var sumAmount: Int = 0

            for(parentAccountWithAmount in parentAccountWithAmountList) {
                if(parentAccountWithAmount.accountType == accountType) {
                    sumAmount += parentAccountWithAmount.amount.value
                }
            }
            
            return sumAmount
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
                val parentAccountWithAmount = ParentAccountWithAmount.create(account.name, PLAccountAmount.of(sumAmount), account.accountType)
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

                if(account.accountType != AccountType.PROFIT && account.accountType != AccountType.LOSS) {
                    continue
                }
                var sumAmount: Int = 0

                for(journalEntryHeader in journalEntryHeaderList) {
                    for(journalEntryDetail in journalEntryHeader.details) {
                        if(journalEntryDetail.account.name.value == account.name.value) {
                            if((journalEntryDetail.journalEntryType == JournalEntryType.CREDIT && journalEntryDetail.account.accountType == AccountType.PROFIT) || (journalEntryDetail.journalEntryType == JournalEntryType.DEBIT && journalEntryDetail.account.accountType == AccountType.LOSS)) {
                                sumAmount += journalEntryDetail.amount.value
                            } else if((journalEntryDetail.journalEntryType == JournalEntryType.DEBIT && journalEntryDetail.account.accountType == AccountType.PROFIT) || (journalEntryDetail.journalEntryType == JournalEntryType.CREDIT && journalEntryDetail.account.accountType == AccountType.LOSS)) {
                                sumAmount -= journalEntryDetail.amount.value
                            }
                        
                        }
                    }
                }
                


                // Insert made list
                val accountWithAmount = AccountWithAmount.create(account.name, PLAccountAmount.of(sumAmount))
                accountWithAmountList.add(accountWithAmount)
            }


            
            return accountWithAmountList
        }
        }
    }