package com.github.imanaijan.stockexchangeapp.domain.model

import java.time.LocalDateTime

data class IntradayInfo (
    val date: LocalDateTime,
    val close: Double
)