package com.github.imanaijan.stockexchangeapp.domain.repository

import com.github.imanaijan.stockexchangeapp.domain.model.CompanyListing
import com.github.imanaijan.stockexchangeapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>
}