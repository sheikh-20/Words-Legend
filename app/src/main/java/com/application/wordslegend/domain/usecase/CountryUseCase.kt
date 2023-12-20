package com.application.wordslegend.domain.usecase

import com.application.wordslegend.data.common.Resource
import com.application.wordslegend.data.repository.CountryRepository
import com.application.wordslegend.domain.model.Country
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CountryUseCase {
    operator fun invoke(): Flow<Resource<Country>>
}

class GetCountryInteractor @Inject constructor(private val repository: CountryRepository): CountryUseCase {
    override fun invoke(): Flow<Resource<Country>> = repository()
}