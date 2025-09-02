package com.example.accounting.domain.pl


class PLAccountAmount private constructor(val value: Int) {
    companion object {
        // 0 を許容。負も許容したいなら require(value >= 0) を削除
        fun of(value: Int): PLAccountAmount {
            //require(value >= 0) { "PLAccountAmount は0以上である必要があります" }
            return PLAccountAmount(value)
        }
    }
}