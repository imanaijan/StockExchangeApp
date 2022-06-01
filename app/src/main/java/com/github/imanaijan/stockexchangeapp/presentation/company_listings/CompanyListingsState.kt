package com.github.imanaijan.stockexchangeapp.presentation.company_listings

import com.github.imanaijan.stockexchangeapp.domain.model.CompanyListing

data class CompanyListingsState(
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
