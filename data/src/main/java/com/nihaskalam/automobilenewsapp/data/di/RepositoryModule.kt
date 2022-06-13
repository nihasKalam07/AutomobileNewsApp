package com.nihaskalam.automobilenewsapp.data.di


import com.nihaskalam.automobilenewsapp.data.repository.NewsRepositoryImpl
import com.nihaskalam.automobilenewsapp.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    internal abstract fun bindCharsRepository(repository: NewsRepositoryImpl): NewsRepository
}