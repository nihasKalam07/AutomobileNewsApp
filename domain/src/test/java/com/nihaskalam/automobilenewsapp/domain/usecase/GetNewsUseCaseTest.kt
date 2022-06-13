package com.nihaskalam.automobilenewsapp.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nihaskalam.automobilenewsapp.domain.model.NetworkResult
import com.nihaskalam.automobilenewsapp.domain.model.NewsFeed
import com.nihaskalam.automobilenewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetNewsUseCaseTest {

    @Mock
    private lateinit var repository: NewsRepository

    private lateinit var getNewsUseCase: GetNewsUseCase

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        getNewsUseCase = GetNewsUseCase(repository)
    }

    @Test
    fun `Check GetNewsUseCase invoke getNews Of Repository`() = runBlocking {
        //given
        val flow = flowOf(NetworkResult.ApiSuccess(testData))
        Mockito.`when`(repository.getNews()).thenReturn(flow)
        //when
        getNewsUseCase().collect {
            val result = it as NetworkResult.ApiSuccess
            result.newsFeed
            //then
            assert(result.newsFeed.data.isEmpty())
        }
    }

    companion object {
        val testData = NewsFeed(category = "Auto", data = listOf(), success = true)
    }
}
