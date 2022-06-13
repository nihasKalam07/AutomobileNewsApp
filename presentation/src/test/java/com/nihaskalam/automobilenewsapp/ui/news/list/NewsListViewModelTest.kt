package com.nihaskalam.automobilenewsapp.ui.news.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.nihaskalam.automobilenewsapp.domain.model.NetworkResult
import com.nihaskalam.automobilenewsapp.domain.model.NewsFeed
import com.nihaskalam.automobilenewsapp.domain.usecase.GetNewsUseCase
import com.nihaskalam.automobilenewsapp.ui.MainCoroutineRule
import com.nihaskalam.automobilenewsapp.ui.TestData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnitRunner

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

    @ExperimentalCoroutinesApi
    fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit): Unit =
        testCoroutineRule.dispatcher.runBlockingTest(block)

    @Before
    fun setUp() {
        viewmodel = NewsListViewModel(useCase)
        newsFeed = viewmodel.newsObj
    }


    //verify onChanged() events
    @Test
    fun `Verify livedata values changes on event`() =
        runBlockingTest {
            assertNotNull(viewmodel.getNewsFeeds())
        }

    @Test
    fun `Given Characters when fetchCharacters should return Success`() = runBlockingTest {
        //given
        val newFlow = flowOf(NetworkResult.ApiSuccess(TestData.testData))
        //when
        doReturn(newFlow).`when`(useCase).invoke()
        viewmodel.getNewsFeeds()
        //then
        viewmodel.getNewsFeeds()
        val result = viewmodel.newsObj.value as NetworkResult.ApiSuccess
        assert(result.newsFeed.data.size == 1)
    }
}

