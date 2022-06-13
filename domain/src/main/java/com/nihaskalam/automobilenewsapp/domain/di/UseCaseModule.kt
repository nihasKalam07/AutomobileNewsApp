package com.nihaskalam.automobilenewsapp.domain.di

import com.nihaskalam.automobilenewsapp.domain.repository.NewsRepository
import com.nihaskalam.automobilenewsapp.domain.usecase.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Use case module to provide use case dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetNewsUseCase(newsRepository: NewsRepository) = GetNewsUseCase(newsRepository)
}