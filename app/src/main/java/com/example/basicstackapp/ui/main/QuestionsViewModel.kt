package com.example.basicstackapp.ui.main

import androidx.lifecycle.*
import com.example.basicstackapp.api.Question
import com.example.basicstackapp.api.Result
import com.example.basicstackapp.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(val questionRepository: QuestionRepository) : ViewModel() {

    private val _questionsData = MutableLiveData<List<Question>>()
     val questionsData: LiveData<List<Question>> = _questionsData

    private val _questionDetail = MutableLiveData<Question>()
    val question: LiveData<Question> = _questionDetail

    private val _loading = MutableLiveData<Boolean>()
     val loading: LiveData<Boolean> = _loading

    fun getQuestions() {
        viewModelScope.launch {
            val result = questionRepository.loadQuestion(
                1,
                50,
                "desc",
                "",
                "stackoverflow",
                "",
                QUESTION_FILTER, API_KEY
            )


            when (result) {
                Result.Empty -> {}
                is Result.Error -> {
                }
                Result.Loading -> {
                    _loading.value = true
                }
                is Result.Success -> {
                    _loading.value = false
                    _questionsData.value = result.data.items
                }
            }
        }
    }

    fun loadQuestionById(questionId: Int) {
        _questionDetail.value = _questionsData.value?.find { it.questionId == questionId }
    }

    companion object {
        const val QUESTION_FILTER = "!9Z(-wwYGT"
        const val API_KEY = "xEJDRSvuLw*OoFv5LlJAKA(("
    }

}