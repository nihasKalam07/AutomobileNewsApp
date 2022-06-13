package com.nihaskalam.automobilenewsapp.data.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nihaskalam.automobilenewsapp.data.ApiService
import com.nihaskalam.automobilenewsapp.data.TestData
import com.nihaskalam.automobilenewsapp.domain.model.NetworkResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

/**
 * Test class for the Retrofit data source
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RetrofitNewsDataSourceTest {

    @Mock
    lateinit var apiService: ApiService

    private lateinit var dataSource: RetrofitNewsDataSource

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        dataSource = RetrofitNewsDataSource(apiService)
    }

    @Test
    fun `Check api success of RetrofitNewsDataSource`() = runBlocking {
        //given
        val givenData = TestData.testData
        Mockito.`when`(apiService.getNews()).thenReturn(Response.success(givenData))
        //when
        val result = dataSource.getNews() as NetworkResult.ApiSuccess
        //then
        assert(result.newsFeed.data.size == givenData.data.size)
    }

    @Test
    fun `Check api error of RetrofitNewsDataSource`() = runBlocking {
        //given
        val errorCode = 403
        Mockito.`when`(apiService.getNews())
            .thenReturn(Response.error(errorCode, "Error".toResponseBody()))
        //when
        val result = dataSource.getNews() as NetworkResult.ApiError
        //then
        assert(result.code == errorCode)
    }

    @Test
    fun `Check api exception of RetrofitNewsDataSource`() = runBlocking {
        //given
        val mockitoException = MockitoException("Network Error")
        Mockito.`when`(apiService.getNews()).thenThrow(mockitoException)
        //when
        val result = dataSource.getNews() as NetworkResult.ApiException
        //then
        assert(result.e.message == "Network Error")
    }
}
