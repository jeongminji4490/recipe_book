package com.example.sharerecipy.di

import com.example.sharerecipy.api.request.RecipeService
import com.example.sharerecipy.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideRepository(recipeService: RecipeService) = Repository(recipeService)
}