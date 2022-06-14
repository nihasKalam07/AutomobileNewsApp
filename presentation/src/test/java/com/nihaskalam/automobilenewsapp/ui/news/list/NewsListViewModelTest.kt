package com.nihaskalam.automobilenewsapp.ui.news.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.nihaskalam.automobilenewsapp.domain.model.NetworkResult
import com.nihaskalam.automobilenewsapp.domain.model.NewsFeed
import com.nihaskalam.automobilenewsapp.domain.usecase.GetNewsUseCase
import com.nihaskalam.automobilenewsapp.ui.MainCoroutineRule
import com.nihaskalam.automobilenewsapp.ui.TestData
import com.nihaskalam.automobilenewsapp.ui.runBlockingTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnitRunner

/**
 * View model test class
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsListViewModelTest {

    lateinit var viewmodel: NewsListViewModel

    @Mock
    lateinit var newsFeed: LiveData<NetworkResult<NewsFeed>>

    @Mock
    private lateinit var useCase: GetNewsUseCase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        viewmodel = NewsListViewModel(useCase)
        newsFeed = viewmodel.newsObj
    }

    @Test
    fun `Check success case of fetching newsfeeds in NewsList view model`() =
        testCoroutineRule.runBlockingTest {
            //given
            val newFlow = flowOf(NetworkResult.ApiSuccess(TestData.testData))
            //when
            doReturn(newFlow).`when`(useCase).invoke()
            viewmodel.getNewsFeeds()
            //then
            val result = viewmodel.newsObj.value as NetworkResult.ApiSuccess
            assert(result.newsFeed.data.size == 1)
        }

    @Test
    fun `Check error case of fetching newsfeeds in NewsList view model`() =
        testCoroutineRule.runBlockingTest {
            //given
            val errorCode = 403
            val newFlow = flowOf(NetworkResult.ApiError<NewsFeed>(errorCode, "Error"))
            //when
            doReturn(newFlow).`when`(useCase).invoke()
            viewmodel.getNewsFeeds()
            //then
            val result = viewmodel.newsObj.value as NetworkResult.ApiError
            assert(result.code == errorCode)
        }

    @Test
    fun `Check exception case of fetching newsfeeds in NewsList view model`() =
        testCoroutineRule.runBlockingTest {
            val exception = Throwable(message = "Error")
            val newFlow = flowOf(NetworkResult.ApiException<NewsFeed>(exception))
            //when
            doReturn(newFlow).`when`(useCase).invoke()
            viewmodel.getNewsFeeds()
            //then
            val result = viewmodel.newsObj.value as NetworkResult.ApiException
            assert(result.e.message == "Error")

        }

    @Test
    fun `Check loading case of fetching newsfeeds in NewsList view model`() =
        testCoroutineRule.runBlockingTest {
            val newFlow = flowOf(NetworkResult.ApiLoading<NewsFeed>())
            //when
            doReturn(newFlow).`when`(useCase).invoke()
            viewmodel.getNewsFeeds()
            //then
            assert(viewmodel.newsObj.value is NetworkResult.ApiLoading<NewsFeed>)

        }
}

