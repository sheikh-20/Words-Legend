package com.application.wordslegend.domain.usecase

import com.application.wordslegend.data.common.Resource
import com.application.wordslegend.data.repository.AuthRepository
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SignUpEmailUseCase {
    operator fun invoke(email: String?, password: String?): Flow<Resource<AuthResult>>
}

class SignUpEmailInteractor @Inject constructor(private val repo: AuthRepository): SignUpEmailUseCase {
    override fun invoke(email: String?, password: String?): Flow<Resource<AuthResult>> {
        return repo.signIn(email = email, password = password)
    }
}