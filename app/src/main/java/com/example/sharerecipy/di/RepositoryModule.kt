package com.example.sharerecipy.di

import com.example.sharerecipy.repository.impl.RepositoryImpl
import com.example.sharerecipy.repository.RepositoryService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

//@Module
//@InstallIn(ViewModelComponent::class)
//object RepositoryModule {
//    @ViewModelScoped
//    @Provides
//    fun provideRepository(recipeService: RecipeService) = Repository(recipeService)
//}

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepositoryService(
        serviceImpl: RepositoryImpl
    ): RepositoryService
}