package com.application.wordslegend.di

import com.application.wordslegend.data.repository.AuthRepository
import com.application.wordslegend.data.repository.GoogleRepositoryImpl
import com.application.wordslegend.data.repository.SignInEmailRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    //Google
    @Binds
    @Named("GoogleRepo")
    abstract fun providesGoogleRepoImpl(googleRepoImpl: GoogleRepositoryImpl): AuthRepository

    //Email
    @Binds
    @Named("SignInEmailRepo")
    abstract fun providesSignInEmailRepoImpl(signInEmailRepoImpl: SignInEmailRepositoryImpl): AuthRepository
}