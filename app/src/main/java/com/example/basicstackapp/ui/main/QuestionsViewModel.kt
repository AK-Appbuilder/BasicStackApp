package com.example.basicstackapp.ui.main

import androidx.lifecycle.*
import com.example.basicstackapp.api.Question
import com.example.basicstackapp.api.Result
import com.example.basicstackapp.common.Event
import com.example.basicstackapp.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(val questionRepository: QuestionRepository) : ViewModel() {

    private val _questionsData = MutableLiveData<Event<List<Question>>>()
     val questionsData: LiveData<Event<List<Question>>> = _questionsData

    private val _questionDetail = MutableLiveData<Question>()
    val question: LiveData<Question> = _questionDetail

    private val _loading = MutableLiveData<Boolean>()
     val loading: LiveData<Boolean> = _loading

    private val _loadingMore = MutableLiveData<Boolean>()
    val loadingMore: LiveData<Boolean> = _loadingMore

    private var pageCount = 1

    fun loadQuestions() {
        viewModelScope.launch {
            val result = questionRepository.loadQuestion(
                pageCount,
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
                    _loadingMore.value = false
                    _questionsData.value = Event(result.data.items)
                }
            }
        }
    }

    fun selectedQuestion(question: Question) {
        _questionDetail.value = question
    }

    fun loadMoreQuestions() {
        pageCount++
        _loadingMore.value = true
        loadQuestions()
    }

    companion object {
        const val QUESTION_FILTER = "!9Z(-wwYGT"
        const val API_KEY = "xEJDRSvuLw*OoFv5LlJAKA(("
    }

}