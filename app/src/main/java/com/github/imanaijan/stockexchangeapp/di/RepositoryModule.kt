package com.github.imanaijan.stockexchangeapp.di

import com.github.imanaijan.stockexchangeapp.data.csv.CSVParser
import com.github.imanaijan.stockexchangeapp.data.csv.CompanyListingsParser
import com.github.imanaijan.stockexchangeapp.data.repository.StockRepositoryImpl
import com.github.imanaijan.stockexchangeapp.domain.model.CompanyListing
import com.github.imanaijan.stockexchangeapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CompanyListingsParser
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}