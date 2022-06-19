package com.example.basicstackapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import com.example.basicstackapp.R
import com.example.basicstackapp.common.visibleIfTrue
import com.example.basicstackapp.databinding.FragmentQuestionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionFragment : Fragment() {

    private val viewModel: QuestionsViewModel by activityViewModels()

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
        adapter = QuestionsAdapter(){ questionId ->
            questionId?.let {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, QuestionDetailFragment.newInstance(it))
                    .commitNow()
            }
        }
        binding.listview.adapter = adapter
    }

    companion object {
        fun newInstance() = QuestionFragment()
    }

}