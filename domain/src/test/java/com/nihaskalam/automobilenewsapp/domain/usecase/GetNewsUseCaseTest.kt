package com.nihaskalam.automobilenewsapp.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nihaskalam.automobilenewsapp.domain.model.Data
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

/**
 * Test class for all the possible scenarios of getNews use case
 */
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
    fun `Check api success of GetNewsUseCase`() = runBlocking {
        //given
        val flow = flowOf(NetworkResult.ApiSuccess(testData))
        Mockito.`when`(repository.getNews()).thenReturn(flow)
        //when
        getNewsUseCase().collect {
            val result = it as NetworkResult.ApiSuccess
            result.newsFeed
            //then
            assert(result.newsFeed.data.size == 1)
        }
    }

    @Test
    fun `Check api Error of GetNewsUseCase`() = runBlocking {
        //given
        val flow = flowOf(NetworkResult.ApiError<NewsFeed>(403, "Error"))
        Mockito.`when`(repository.getNews()).thenReturn(flow)
        //when
        getNewsUseCase().collect {
            val result = it as NetworkResult.ApiError

            //then
            assert(result.code == 403)
        }
    }

    @Test
    fun `Check api Exception of GetNewsUseCase`() = runBlocking {
        //given
        val flow = flowOf(NetworkResult.ApiException<NewsFeed>(Throwable(message = "Error")))
        Mockito.`when`(repository.getNews()).thenReturn(flow)
        //when
        getNewsUseCase().collect {
            val result = it as NetworkResult.ApiException
            //then
            assert(result.e.message == "Error")
        }
    }

    @Test
    fun `Check api loading of GetNewsUseCase`() = runBlocking {
        //given
        val flow = flowOf(NetworkResult.ApiLoading<NewsFeed>())
        Mockito.`when`(repository.getNews()).thenReturn(flow)
        //when
        getNewsUseCase().collect {
            //then
            assert(it is NetworkResult.ApiLoading)
        }
    }

    companion object {
        private val data = Data(
            author = "",
            content = "",
            date = "",
            id = "",
            imageUrl = "",
            readMoreUrl = "",
            time = "",
            title = "",
            url = ""
        )
        val testData = NewsFeed(category = "Auto", data = listOf(data), success = true)
    }
}
