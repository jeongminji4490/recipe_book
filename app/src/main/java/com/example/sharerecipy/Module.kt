package com.example.sharerecipy

import android.content.Context
import com.example.sharerecipy.model.service.AccountService
import com.example.sharerecipy.model.service.impl.AccountServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
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

//    @Binds
//    abstract fun provideRecipeService(
//        service: RecipeServiceImpl
//    ): RecipeService
}

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context) = DataStore(context)
}