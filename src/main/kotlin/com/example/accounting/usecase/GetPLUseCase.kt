package com.example.accounting.usecase

import com.example.accounting.domain.pl.PL
import com.example.accounting.domain.pl.PLRepository
import org.springframework.stereotype.Service

@Service
class GetPLUseCase(
    private val plRepository: PLRepository,
) {
    fun execute(): PL = plRepository.getPL()
}
