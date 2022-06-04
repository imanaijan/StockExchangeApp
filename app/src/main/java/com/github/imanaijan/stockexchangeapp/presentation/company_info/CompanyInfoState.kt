package com.github.imanaijan.stockexchangeapp.presentation.company_info

import com.github.imanaijan.stockexchangeapp.domain.model.CompanyInfo
import com.github.imanaijan.stockexchangeapp.domain.model.IntradayInfo

data class CompanyInfoState (
    val stockInfos: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)