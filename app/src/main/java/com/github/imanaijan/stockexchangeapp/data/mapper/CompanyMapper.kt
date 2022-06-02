package com.github.imanaijan.stockexchangeapp.data.mapper

import com.github.imanaijan.stockexchangeapp.data.local.CompanyListingEntity
import com.github.imanaijan.stockexchangeapp.data.remote.dto.CompanyInfoDto
import com.github.imanaijan.stockexchangeapp.domain.model.CompanyInfo
import com.github.imanaijan.stockexchangeapp.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}