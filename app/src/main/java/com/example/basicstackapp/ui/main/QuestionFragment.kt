package com.example.basicstackapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.basicstackapp.common.visibleIfTrue
import com.example.basicstackapp.databinding.FragmentQuestionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionFragment : Fragment() {

    private val viewModel: QuestionsViewModel by viewModels()

    private lateinit var binding: FragmentQuestionsBinding
    private var adapter: QuestionsAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentQuestionsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        initObservers()
        viewModel.getQuestions()
    }

    private fun initObservers() {
     viewModel.questionsData.observe(viewLifecycleOwner){
         adapter?.submitList(it)
     }

        viewModel.loading.observe(viewLifecycleOwner){
            binding.loader.visibleIfTrue(it)
        }

    }

    private fun setupAdapter() {
        adapter = QuestionsAdapter()
        binding.listview.adapter = adapter
    }

    companion object {
        fun newInstance() = QuestionFragment()
    }

}