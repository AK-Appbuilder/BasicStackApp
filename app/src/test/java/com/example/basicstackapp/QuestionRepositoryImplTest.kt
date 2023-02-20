package com.example.basicstackapp

import com.example.basicstackapp.api.Question
import com.example.basicstackapp.api.QuestionsResponse
import com.example.basicstackapp.api.StackApiService
import com.example.basicstackapp.api.data
import com.example.basicstackapp.repository.QuestionRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Rule
import org.junit.Test

import retrofit2.HttpException
import retrofit2.http.Query
import java.io.IOException
import kotlin.math.exp

class QuestionRepositoryImplTest {

    private val apiService = mockk<StackApiService>()
    private val repository = QuestionRepositoryImpl(apiService)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val testScope = TestCoroutineScope()

    @Test
    fun `loadQuestion should call apiService with correct parameters and return expected result`() =
        testScope.runBlockingTest {
            val expectedPage = 1
            val expectedPageSize = 10
            val expectedOrder = "desc"
            val expectedSortCondition = "votes"
            val expectedSite = "stackoverflow"
            val expectedFilter = "withbody"
            val expectedSiteKey = "abc123"

            // Mock the API response
            val expectedResponse = QuestionsResponse(
                hasMore = true,
                items = listOf(
                    Question(
                        questionId = 123,
                        title = "Sample question",
                        body = "This is a sample question",
                        score = 10
                    )
                ),
                quotaMax = 100,
                quotaRemaining = 50
            )
            coEvery {
                apiService.getQuestionsForAll(
                    expectedPage,
                    expectedPageSize,
                    expectedOrder,
                    expectedSortCondition,
                    expectedSite,
                    expectedFilter,
                    expectedSiteKey,
                )
            } returns expectedResponse

            // Call the repository function
            val result = repository.loadQuestion(
                expectedPage,
                expectedPageSize,
                expectedOrder,
                expectedSortCondition,
                expectedSite,
                expectedFilter,
                expectedSiteKey
            )

            // Verify that the API was called with the correct parameters
            coVerify {
                apiService.getQuestionsForAll(
                    expectedPage,
                    expectedPageSize,
                    expectedOrder,
                    expectedSortCondition,
                    expectedSite,
                    expectedFilter,
                    expectedSiteKey
                )
            }

            // Verify that the result is the expected QuestionsResponse
            assertEquals(expectedResponse, result.data)
        }



    fun `loadQuestion should throw IOException when network call fails`() = testScope.runBlockingTest {
        val expectedPage = 1
        val expectedPageSize = 10
        val expectedOrder = "desc"
        val expectedSortCondition = "votes"
        val expectedSite = "stackoverflow"
        val expectedTagged = "kotlin"
        val expectedFilter = "withbody"
        val expectedSiteKey = "abc123"

        // Mock the API response
        coEvery {
            apiService.getQuestionsForAll(
                expectedPage,
                expectedPageSize,
                expectedOrder,
                expectedSortCondition,
                expectedSite,
                expectedFilter,
                expectedSiteKey
            )
        } throws IOException("Network error")

//        assertThrows()
        // Call the repository function
        repository.loadQuestion(
            expectedPage,
            expectedPageSize,
            expectedOrder,
            expectedSortCondition,
            expectedSite,
            expectedFilter,
            expectedSiteKey
        )
    }


    fun `loadQuestion should throw HttpException when API call returns an error response`() =
        testScope.runBlockingTest {
            val expectedPage = 1
            val expectedPageSize = 10
            val expectedOrder = "desc"
            val expectedSortCondition = "votes"
            val expectedSite = "stackoverflow"
            val expectedFilter = "withbody"
            val expectedSiteKey = "abc123"

            // Mock the API response
            coEvery {
                apiService.getQuestionsForAll(
                    expectedPage,
                    expectedPageSize,
                    expectedOrder,
                    expectedSortCondition,
                    expectedSite,
                    expectedFilter,
                    expectedSiteKey
                )
            } throws HttpException(mockk(relaxed = true))

            // Call the repository function
            repository.loadQuestion(
                expectedPage,
                expectedPageSize,
                expectedOrder,
                expectedSortCondition,
                expectedSite,
                expectedFilter,
                expectedSiteKey
            )
        }


    @After
    fun cleanUp() {
        try {
            testScope.cleanupTestCoroutines()
        } catch (e: Exception) {
            //Do something here
        }
    }


}
