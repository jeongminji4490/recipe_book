package com.example.recipebook.di

import com.example.recipebook.repository.impl.RepositoryImpl
import com.example.recipebook.repository.RepositoryService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepositoryService(
        serviceImpl: RepositoryImpl
    ): RepositoryService
}