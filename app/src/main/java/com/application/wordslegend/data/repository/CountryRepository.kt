package com.application.wordslegend.data.repository

import com.application.wordslegend.data.common.Resource
import com.application.wordslegend.domain.model.Country
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject


interface CountryRepository {
    operator fun invoke(): Flow<Resource<Country>>
}

class CountryRepositoryImpl @Inject constructor(private val database: FirebaseDatabase): CountryRepository {

    private companion object {
        const val TAG = "CountryRepositoryImpl"
    }

    override fun invoke(): Flow<Resource<Country>> = flow {
        emit(Resource.Loading)

        try {
            val result = database.getReference("country").get().await()

            emit(Resource.Success(Country(result.value as List<String>) ?: return@flow))
        } catch (exception: Exception) {
            throw Throwable(exception)
        }

    }.catch {
        Timber.tag(TAG).e(it)
        emit(Resource.Failure(it))
    }
}