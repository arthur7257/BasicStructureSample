package com.example.basicstructuresample

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SampleViewModel(private val repository: SampleRepository) :
    ViewModel(), LifecycleEventObserver {

  private val _viewState: MutableLiveData<SampleView.State> = MutableLiveData()

  val viewState: LiveData<SampleView.State> = _viewState

  override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
    when (event) {
      Lifecycle.Event.ON_START -> {
        viewModelScope.launch {
          repository
              .fetchSampleData()
              .onSuccess { response ->
                _viewState.value =
                    SampleView.State.Success(
                        header = response.header,
                        messageList = response.messages.map { Message(it) })
              }
              .onFailure { error ->
                _viewState.value = SampleView.State.Error(error.message ?: "Unknown error")
              }
        }
      }
      else -> {}
    }
  }
}
