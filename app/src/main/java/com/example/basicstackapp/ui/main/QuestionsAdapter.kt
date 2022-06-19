package com.example.basicstackapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.example.basicstackapp.api.Question
import com.example.basicstackapp.databinding.QuestionItemBinding

class QuestionsAdapter (val onClick: (id: Int?)-> Unit): ListAdapter<Question, QuestionsAdapter.ViewHolder>(VoucherDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = QuestionItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(
        val binding: QuestionItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Question,
        ) {
            binding.question = item
            binding.root.setOnClickListener {
                onClick.invoke(item.questionId)
            }
            binding.executePendingBindings()
        }
    }

}

class VoucherDiffCallback : DiffUtil.ItemCallback<Question>() {
    override fun areItemsTheSame(oldItem: Question, newItem:Question): Boolean {
        return oldItem.questionId == newItem.questionId
    }

    override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
        return oldItem == newItem
    }
}