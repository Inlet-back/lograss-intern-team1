package com.example.accounting.apiController

import com.example.accounting.domain.pl.PL
import com.example.accounting.usecase.GetPLUseCase
import com.example.accounting.usecase.GetPLWithMonthUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController("apiPLController")
@RequestMapping("/api")
class PLController(
    private val getPLUseCase: GetPLUseCase,
    private val getPLWithMonthUseCase: GetPLWithMonthUseCase,
) {

    @GetMapping("/pl")
    fun get(): ResponseEntity<PL?> {
        val pl = getPLUseCase.execute()
        return ResponseEntity.ok(pl)
    }

    @GetMapping("/pl/monthly")
    fun getMonthly(
        @RequestParam year: Int,
        @RequestParam month: Int
    ): ResponseEntity<PL> =
        ResponseEntity.ok(getPLWithMonthUseCase.execute(year, month))
}