package com.example.basicstructuresample

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basicstructuresample.databinding.ActivityMainBinding

class SampleView(binding: ActivityMainBinding) {

  private val recyclerView: RecyclerView = binding.recyclerView
  private val adapter = SampleAdapter()

  init {
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    recyclerView.adapter = adapter
  }

  fun render(state: State) {
    when (state) {
      is State.Loading -> {
        recyclerView.visibility = View.GONE
      }
      is State.Success -> {
        recyclerView.visibility = View.VISIBLE
        adapter.submitList(state.messageList)
        // TODO: add header
      }
      is State.Error -> {
        recyclerView.visibility = View.GONE
        // TODO: show error message
      }
    }
  }

  sealed class State {
    data object Loading : State()

    data class Success(val header: String, val messageList: List<Message>) : State()

    data class Error(val message: String) : State()
  }
}
