package com.example.basicstackapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.basicstackapp.api.Question
import com.example.basicstackapp.api.QuestionsResponse
import com.example.basicstackapp.api.Result
import com.example.basicstackapp.repository.QuestionRepository
import com.example.basicstackapp.ui.main.QuestionsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class QuestionsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var questionRepository: QuestionRepository
    private lateinit var questionsViewModel: QuestionsViewModel

    @Before
    fun setup() {
        questionRepository = mockk(relaxed = true)
        questionsViewModel = QuestionsViewModel(questionRepository)
    }

    @Test
    fun `test loadQuestions should update questionsData when API call is successful`() = runBlockingTest {
        val questions = listOf(
            Question(1, "Question 1"),
            Question(2, "Question 2")
        )

        coEvery {
            questionRepository.loadQuestion(
                1,
                50,
                "desc",
                "",
                "stackoverflow",
                "",
                QuestionsViewModel.QUESTION_FILTER,
                QuestionsViewModel.API_KEY
            )
        } returns Result.Success(QuestionsResponse( items = questions)) as Result<QuestionsResponse>

        questionsViewModel.loadQuestions()

        assertNotNull(questionsViewModel.questionsData.value)
        assertEquals(questions, questionsViewModel.questionsData.value?.peekContent())
    }

    @Test
    fun `test selectedQuestion should update question with selected question`() {
        val question = Question(1, "Question 1")

        questionsViewModel.selectedQuestion(question)

        assertEquals(question, questionsViewModel.question.value)
    }

    @Test
    fun `test loadMoreQuestions should update questionsData when API call is successful`() = runBlockingTest {
        val questions = listOf(
            Question(1, "Question 1"),
            Question(2, "Question 2")
        )

        coEvery {
            questionRepository.loadQuestion(
                2,
                50,
                "desc",
                "",
                "stackoverflow",
                "",
                QuestionsViewModel.QUESTION_FILTER,
                QuestionsViewModel.API_KEY
            )
        } returns Result.Success(QuestionsResponse(items=questions))

        questionsViewModel.loadMoreQuestions()

        assertNotNull(questionsViewModel.questionsData.value)
        assertEquals(questions, questionsViewModel.questionsData.value?.peekContent())
    }
}
