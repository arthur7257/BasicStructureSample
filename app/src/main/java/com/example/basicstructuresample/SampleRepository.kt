package com.example.basicstructuresample

class SampleRepository {

  suspend fun fetchSampleData(): Result<MessageResponse> {
    Thread.sleep(2000)
    return Result.success(
        MessageResponse(
            header = "Header", messages = listOf("Message 1", "Message 2", "Message 3")))
  }

  data class MessageResponse(val header: String, val messages: List<String>)
}
