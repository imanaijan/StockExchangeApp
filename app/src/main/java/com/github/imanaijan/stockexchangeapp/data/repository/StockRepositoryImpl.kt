package com.github.imanaijan.stockexchangeapp.data.repository

import com.github.imanaijan.stockexchangeapp.data.csv.CSVParser
import com.github.imanaijan.stockexchangeapp.data.local.StockDatabase
import com.github.imanaijan.stockexchangeapp.data.mapper.toCompanyListing
import com.github.imanaijan.stockexchangeapp.data.mapper.toCompanyListingEntity
import com.github.imanaijan.stockexchangeapp.data.remote.dto.StockApi
import com.github.imanaijan.stockexchangeapp.domain.model.CompanyListing
import com.github.imanaijan.stockexchangeapp.domain.repository.StockRepository
import com.github.imanaijan.stockexchangeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    val api: StockApi,
    val db: StockDatabase,
    val companyListingsParser: CSVParser<CompanyListing>
): StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(
                data = localListings.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if(shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try {
                val response = api.getListings()
                companyListingsParser.parse(response.byteStream())
            } catch(e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data: maybe something with parsing goes wrong"))
                null
            } catch(e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data: maybe invalid response"))
                null
            }

            remoteListings?.let { listings ->
                dao.clearCompanyListing()
                dao.insertCompanyListing(
                    listings.map { it.toCompanyListingEntity() }
                )
                emit(Resource.Success(
                    data = dao.searchCompanyListing("").map { it.toCompanyListing() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }
}