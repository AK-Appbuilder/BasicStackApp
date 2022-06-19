package com.example.basicstackapp.ui.main

import android.util.Log
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

    fun getQuestions() {
        viewModelScope.launch {
            val result = questionRepository.loadQuestion(
                1,
                50,
                "desc",
                "",
                "",
                "",
                QUESTION_FILTER, API_KEY
            )


            when (result) {
                Result.Empty -> {}
                is Result.Error -> {
                }
                Result.Loading -> {}
                is Result.Success -> {
                    _questionsData.value = result.data.items
                }
            }
        }
    }

    companion object {
        const val QUESTION_FILTER = "!9Z(-wwYGT"
        const val API_KEY = "xEJDRSvuLw*OoFv5LlJAKA(("
    }

}