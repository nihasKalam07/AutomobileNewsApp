package com.nihaskalam.automobilenewsapp.ui.news

import android.content.Context
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.nihaskalam.automobilenewsapp.data.ApiService
import com.nihaskalam.automobilenewsapp.data.BuildConfig
import com.nihaskalam.automobilenewsapp.data.di.NetworkModule
import com.nihaskalam.automobilenewsapp.data.network.RetrofitNewsDataSource
import com.nihaskalam.automobilenewsapp.ui.R
import com.nihaskalam.automobilenewsapp.ui.configureFailedResponse
import com.nihaskalam.automobilenewsapp.ui.configureSuccessResponse
import com.nihaskalam.automobilenewsapp.ui.news.list.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Instrumentation tests for MainActivity
 */
@HiltAndroidTest
@UninstallModules(NetworkModule::class)
class MainActivityTest {

    @Inject
    lateinit var mockWebServer: MockWebServer

    @Inject
    lateinit var okHttp3IdlingResource: OkHttp3IdlingResource

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltAndroidRule.inject()
        IdlingRegistry.getInstance().register(okHttp3IdlingResource)
        mockWebServer.start(PORT)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
        IdlingRegistry.getInstance().unregister(okHttp3IdlingResource)
    }

    private fun getResourceString(id: Int): String {
        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        return targetContext.resources.getString(id)
    }

    @Test
    fun checkAppLaunchesSuccessfully() {
        val scenario = launchActivity<MainActivity>()
        scenario.close()
    }

    @Test(expected = PerformException::class)
    fun checkNewsItemWithATextDoesNotExistInTheList() {
        configureSuccessResponse(mockWebServer)
        val scenario = launchActivity<MainActivity>()
        onView(withId(R.id.newsRv))
            .perform(
                RecyclerViewActions.scrollTo<NewsAdapter.NewsViewHolder>(
                    hasDescendant(withText("some random text"))
                )
            )
        scenario.close()
    }

    @Test
    fun clickListItemAndCheckItsAvailabilityInDetailsScreen() {
        configureSuccessResponse(mockWebServer)
        val scenario = launchActivity<MainActivity>()
        //given
        val itemPosition = 7
        val itemElementText = "title 7"
        //when
        onView(withId(R.id.newsRv))
            .perform(
                // scrollTo will fail the test if no item matches.
                RecyclerViewActions.scrollToPosition<NewsAdapter.NewsViewHolder>(
                    itemPosition
                )
            )
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<NewsAdapter.NewsViewHolder>(
                    itemPosition,
                    click()
                )
            )
        //then
        onView(withText(itemElementText)).check(matches(isDisplayed()))
        scenario.close()
    }

    @Test
    fun testApiSuccessResponse() {
        configureSuccessResponse(mockWebServer)
        val scenario = launchActivity<MainActivity>()
        //given
        val titleText = "title 5"
        val contentText = "content 5"
        val authorText = "author 5"
        val itemPosition = 5
        //when
        onView(withId(R.id.newsRv))
            .perform(
                RecyclerViewActions.scrollToPosition<NewsAdapter.NewsViewHolder>(
                    itemPosition
                )
            )
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<NewsAdapter.NewsViewHolder>(
                    itemPosition,
                    click()
                )
            )
        //then
        onView(withId(R.id.titleTV)).check(matches(withText(titleText)))
        onView(withId(R.id.descriptionTV)).check(matches(withText(contentText)))
        onView(withId(R.id.authorTV)).check(matches(withSubstring(authorText)))
        scenario.close()
    }

    @Test(expected = PerformException::class)
    fun testApiFailedResponse() {
        configureFailedResponse(mockWebServer)
        val scenario = launchActivity<MainActivity>()
        onView(withId(R.id.newsRv))
            .perform(
                RecyclerViewActions.scrollTo<NewsAdapter.NewsViewHolder>(
                    hasDescendant(withText("title 1"))
                )
            )
        scenario.close()
    }

    @Test
    fun testDetailsScreenUpButton() {
        configureSuccessResponse(mockWebServer)
        val scenario = launchActivity<MainActivity>()
        val itemPosition = 0
        //when
        onView(withId(R.id.newsRv))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<NewsAdapter.NewsViewHolder>(
                    itemPosition,
                    click()
                )
            )
        //then
        onView(withId(R.id.upButton)).perform(click())
        onView(withId(R.id.headerTV)).check(matches(withSubstring(getResourceString(R.string.ui_label_automobile_news))))
        scenario.close()
    }

    /**
     * Network module to provide dependencies for testing
     */
    @Module
    @InstallIn(SingletonComponent::class)
    object TestNetworkModule {

        @Provides
        @Singleton
        fun provideMockWebServer() = MockWebServer()

        @Provides
        fun provideBaseUrl() = "http://localhost:$PORT"

        @Provides
        @Singleton
        fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            return interceptor
        }

        @Provides
        @Singleton
        fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
            if (BuildConfig.DEBUG) {
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .build()
            } else {
                OkHttpClient
                    .Builder()
                    .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .build()
            }

        @Provides
        @Singleton
        fun provideOkHttp3IdlingResource(okHttpClient: OkHttpClient) =
            OkHttp3IdlingResource.create(
                "okhttp",
                okHttpClient
            )


        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()

        @Provides
        @Singleton
        fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

        @Provides
        @Singleton
        fun provideRetrofitNewsDataSource(apiService: ApiService) =
            RetrofitNewsDataSource(apiService)
    }

    companion object {
        private const val REQUEST_TIMEOUT = 3L
        private const val PORT = 8080
    }
}

