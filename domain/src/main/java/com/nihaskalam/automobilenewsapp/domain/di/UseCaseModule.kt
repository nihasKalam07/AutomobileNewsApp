package com.nihaskalam.automobilenewsapp.domain.di

import com.nihaskalam.automobilenewsapp.domain.repository.NewsRepository
import com.nihaskalam.automobilenewsapp.domain.usecase.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetNewsUseCase(newsRepository: NewsRepository) = GetNewsUseCase(newsRepository)
}