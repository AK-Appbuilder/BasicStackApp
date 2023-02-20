package com.example.basicstackapp.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.basicstackapp.R
import com.example.basicstackapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionDetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val viewModel: QuestionsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
    }

    override fun onResume() {
        super.onResume()
        setToolbarTitle(getString(R.string.title_question_detail))
    }

    private fun setToolbarTitle(title: String?) {
        activity?.title = title
    }

    private fun initObservers() {
        viewModel.question.observe(viewLifecycleOwner) {
            binding.tvQuestionDetail.text = it.title
            binding.webview.loadData(it.body!!, "text/html", "UTF-8")
        }
    }

    companion object {

        private const val ARG_QUESTION_ID = "question_id"

        fun newInstance(questionId: Int): QuestionDetailFragment {
            val questionFragment = QuestionDetailFragment().apply {
//                val args = Bundle()
//                args.putInt(ARG_QUESTION_ID, questionId)
//                arguments = args
            }
            return questionFragment
        }
    }
}
