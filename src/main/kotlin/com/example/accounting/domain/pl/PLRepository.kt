package com.example.accounting.domain.pl

interface PLRepository {
    fun getPL(): PL
    fun getPLWithMonth(year: Int, month: Int): PL
}