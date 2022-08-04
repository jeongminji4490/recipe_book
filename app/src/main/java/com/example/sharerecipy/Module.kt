package com.example.sharerecipy

import com.example.sharerecipy.model.service.AccountService
import com.example.sharerecipy.model.service.impl.AccountServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
internal object RepoModule {
    @Provides
    @ViewModelScoped
    fun provideRepo() : Repository = Repository()
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(
        serviceImpl: AccountServiceImpl
    ): AccountService
}