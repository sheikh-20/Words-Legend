package com.application.wordslegend.di

import com.application.wordslegend.data.repository.AccountSetupRepository
import com.application.wordslegend.data.repository.AccountSetupRepositoryImpl
import com.application.wordslegend.data.repository.AuthRepository
import com.application.wordslegend.data.repository.CountryRepository
import com.application.wordslegend.data.repository.CountryRepositoryImpl
import com.application.wordslegend.data.repository.GoogleRepositoryImpl
import com.application.wordslegend.data.repository.SignInEmailRepositoryImpl
import com.application.wordslegend.data.repository.SignUpEmailRepositoryImpl
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

    @Binds
    @Named("SignUpEmailRepo")
    abstract fun providesSignUpEmailRepoImpl(signUpEmailRepoImpl: SignUpEmailRepositoryImpl): AuthRepository

    @Binds
    abstract fun providesCountryRepoImpl(countryRepositoryImpl: CountryRepositoryImpl): CountryRepository

    @Binds
    abstract fun providesAccountSetupRepositoryImpl(accountSetupRepositoryImpl: AccountSetupRepositoryImpl): AccountSetupRepository
}