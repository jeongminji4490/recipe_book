package com.example.sharerecipy.di

import com.example.sharerecipy.api.request.AccountService
import com.example.sharerecipy.api.request.RecipeService
import com.example.sharerecipy.api.request.impl.AccountServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class AccountServiceModule {
    @Binds
    abstract fun provideAccountService(
        serviceImpl: AccountServiceImpl
    ): AccountService
}

@Module
@InstallIn(SingletonComponent::class)
object RecipeServiceModule {
    @Singleton
    @Provides
    fun provideRecipeService(retrofit: Retrofit) : RecipeService {
        return retrofit.create(RecipeService::class.java)
    }
}