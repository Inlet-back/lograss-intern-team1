package com.example.accounting.usecase

import com.example.accounting.domain.pl.PL
import com.example.accounting.domain.pl.PLRepository
import org.springframework.stereotype.Service

@Service
class GetPLWithMonthUseCase(
    private val plRepository: PLRepository,
) {
    fun execute(year: Int, month: Int): PL {
        require(month in 1..12) { "month は 1..12" }
        return plRepository.getPLWithMonth(year, month)
    }
}
