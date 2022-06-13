package com.nihaskalam.automobilenewsapp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nihaskalam.automobilenewsapp.data.TestData
import com.nihaskalam.automobilenewsapp.data.network.RetrofitNewsDataSource
import com.nihaskalam.automobilenewsapp.domain.model.NetworkResult
import com.nihaskalam.automobilenewsapp.domain.model.NewsFeed
import com.nihaskalam.automobilenewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class NewsRepositoryTest {

    @Mock
    private lateinit var retrofitDataSource: RetrofitNewsDataSource

    private lateinit var repository: NewsRepository

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = NewsRepositoryImpl(retrofitDataSource)
    }

    @Test
    fun `Check api success of NewsRepository`() = runBlocking {
        //given
        val givenData = NetworkResult.ApiSuccess(TestData.testData)
        Mockito.`when`(retrofitDataSource.getNews()).thenReturn(givenData)

        //when
        repository.getNews().collect {
            when(it){
                is NetworkResult.ApiSuccess -> assert(it.newsFeed.data.size == 1)
            }
        }
    }

    @Test
    fun `Check api error of NewsRepository`() = runBlocking {
        //given
        val errorCode = 403
        val givenData = NetworkResult.ApiError<NewsFeed>(errorCode, "Error")
        Mockito.`when`(retrofitDataSource.getNews()).thenReturn(givenData)

        //when
        repository.getNews().collect {
            when(it){
                is NetworkResult.ApiError -> assert(it.code == errorCode)
            }
        }
    }

    @Test
    fun `Check api exception of NewsRepository`() = runBlocking {
        //given
        val msg = "Error"
        val e = Throwable(message = msg)
        val givenData = NetworkResult.ApiException<NewsFeed>(e)
        Mockito.`when`(retrofitDataSource.getNews()).thenReturn(givenData)

        //when
        repository.getNews().collect {
            when(it){
                is NetworkResult.ApiException -> assert(it.e.message == msg)
            }
        }
    }

    @Test
    fun `Check api loading of NewsRepository`() = runBlocking {
        //given
        val givenData = NetworkResult.ApiLoading<NewsFeed>()
        Mockito.`when`(retrofitDataSource.getNews()).thenReturn(givenData)

        //when
        repository.getNews().collect {
            assert(it is NetworkResult.ApiLoading)
        }
    }
}
