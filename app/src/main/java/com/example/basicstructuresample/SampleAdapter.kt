package com.example.basicstructuresample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.basicstructuresample.databinding.ItemLayoutBinding

class SampleAdapter : ListAdapter<Message, SampleAdapter.SampleViewHolder>(SampleDiffCallback()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder =
      SampleViewHolder(
          ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))

  override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  class SampleViewHolder(private val itemLayoutBinding: ItemLayoutBinding) :
      ViewHolder(itemLayoutBinding.root) {

    fun bind(message: Message) {
      itemLayoutBinding.messageText.text = message.text
    }
  }
}

private class SampleDiffCallback : DiffUtil.ItemCallback<Message>() {
  override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
    return oldItem == newItem
  }
}

data class Message(val text: String)
