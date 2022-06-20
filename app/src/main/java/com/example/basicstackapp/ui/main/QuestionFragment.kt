package com.example.basicstackapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basicstackapp.R
import com.example.basicstackapp.common.EventObserver
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
        viewModel.loadQuestions()
    }

    private fun initObservers() {
     viewModel.questionsData.observe(viewLifecycleOwner, EventObserver{
         adapter?.submitList(it)
     })

        viewModel.loading.observe(viewLifecycleOwner){
            binding.loader.visibleIfTrue(it)
        }

        viewModel.loadingMore.observe(viewLifecycleOwner){
            binding.nextLoadingPage.visibleIfTrue(it)
        }

    }

    private fun setupAdapter() {
        adapter = QuestionsAdapter(){ question ->
            question?.let {

                viewModel.selectedQuestion(question)

                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, QuestionDetailFragment.newInstance(it.questionId))
                    .commitNow()
            }
        }

        binding.listview.adapter = adapter

        binding.listview.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            var loading = true

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = binding.listview.layoutManager as LinearLayoutManager

                if (dy > 0) { //check for scroll down
                    val visibleItemCount = layoutManager.childCount;
                    val totalItemCount = layoutManager.itemCount;
                    val pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            viewModel.loadMoreQuestions()

                            loading = true;
                        }
                    }
                }
            }

        })
    }

    companion object {
        fun newInstance() = QuestionFragment()
    }

}