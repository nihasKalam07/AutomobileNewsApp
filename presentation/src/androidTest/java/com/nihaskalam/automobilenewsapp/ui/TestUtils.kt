package com.nihaskalam.automobilenewsapp.ui

import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import java.io.IOException
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit

/**
 * Method to set up success response for mock webserver
 *
 * @param mockWebServer MockWebServer
 */
fun configureSuccessResponse(mockWebServer: MockWebServer) {
    mockWebServer.dispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse()
                .setResponseCode(200)
                .setBody(readStringFromFile("success_response.json"))
        }
    }
}

/**
 * Method to set up failed response for mock webserver by
 * throttles the response by five seconds and given request timeout three seconds in OkHttpClient
 *
 * @param mockWebServer MockWebServer
 */
fun configureFailedResponse(mockWebServer: MockWebServer) {
    mockWebServer.dispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse()
                .setResponseCode(200)
                .setBody(readStringFromFile("success_response.json"))
                .throttleBody(1024, 5, TimeUnit.SECONDS)
        }
    }
}

/**
 * Function to read files from asset
 *
 * @param fileName File name
 * @return file as String
 */
fun readStringFromFile(fileName: String): String {
    try {
        val inputStream = (InstrumentationRegistry.getInstrumentation().targetContext
            .applicationContext).assets.open(fileName)
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, "UTF-8")
        reader.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    } catch (e: IOException) {
        throw e
    }
}