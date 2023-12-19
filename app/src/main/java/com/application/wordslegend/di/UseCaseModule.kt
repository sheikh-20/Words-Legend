package com.application.wordslegend.di

import com.application.wordslegend.data.repository.AuthRepository
import com.application.wordslegend.domain.usecase.SignInEmailInteractor
import com.application.wordslegend.domain.usecase.SignInEmailUseCase
import com.application.wordslegend.domain.usecase.SignInGoogleInteractor
import com.application.wordslegend.domain.usecase.SignInGoogleUseCase
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun providesSignInGoogleUseCase(@Named("GoogleRepo") authRepository: AuthRepository, oneTapClient: SignInClient): SignInGoogleUseCase {
        return SignInGoogleInteractor(authRepository, oneTapClient)
    }

    @Provides
    @Singleton
    fun providesSignInEmailUseCase(@Named("SignInEmailRepo") authRepository: AuthRepository): SignInEmailUseCase {
        return SignInEmailInteractor(authRepository)
    }

}