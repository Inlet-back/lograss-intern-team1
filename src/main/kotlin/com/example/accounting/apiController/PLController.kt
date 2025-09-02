package com.example.accounting.apiController

import com.example.accounting.domain.pl.PL
import com.example.accounting.usecase.GetPLUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController("apiPLController")
@RequestMapping("/api")
class PLController(
    private val getPLUseCase: GetPLUseCase,
) {

    @GetMapping("/pl")
    fun get(): ResponseEntity<PL?> {
        val pl = getPLUseCase.execute()
        return ResponseEntity.ok(pl)
    }
}