package com.application.wordslegend.domain.usecase

import android.net.Uri
import com.application.wordslegend.data.common.Resource
import com.application.wordslegend.data.repository.AccountSetupRepository
import com.application.wordslegend.domain.model.Member
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AccountSetupUseCase {

    suspend fun updateInfo(userId: String, member: Member)

    fun uploadProfilePhoto(userId: String, uri: Uri): Flow<Resource<UploadTask.TaskSnapshot>>

    fun getPhoto(userId: String): Flow<Resource<Uri>>

    fun getUserDetail(userId: String): Flow<Resource<Member>>
}


class GetAccountSetupInteractor @Inject constructor(private val repository: AccountSetupRepository): AccountSetupUseCase {

    private companion object {
        const val TAG = "GetAccountSetupInteractor"
    }

    override suspend fun updateInfo(userId: String, member: Member) = repository.uploadUserData(userId, member)

    override fun uploadProfilePhoto(userId: String, uri: Uri): Flow<Resource<UploadTask.TaskSnapshot>> = repository.uploadPhoto(userId, uri)

    override fun getPhoto(userId: String): Flow<Resource<Uri>> = repository.getPhoto(userId)

    override fun getUserDetail(userId: String): Flow<Resource<Member>> = repository.getUserDetail(userId)
}