package com.example.basicstackapp.di

import com.example.basicstackapp.repository.QuestionRepository
import com.example.basicstackapp.repository.QuestionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule {

    @Binds abstract fun bindQuestionRepository(
        questionRepository: QuestionRepositoryImpl
    ): QuestionRepository
}