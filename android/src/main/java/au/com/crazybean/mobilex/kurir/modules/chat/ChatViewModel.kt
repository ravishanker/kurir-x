package au.com.crazybean.mobilex.kurir.modules.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import au.com.crazybean.foundation.mvvm.ViewModel
import au.com.crazybean.mobilex.foundation.logger.Logger
import au.com.crazybean.mobilex.foundation.native.currentMillis
import au.com.crazybean.mobilex.foundation.userdata.UserData
import au.com.crazybean.mobilex.kurir.data.kEmail
import au.com.crazybean.mobilex.kurir.data.model.Message
import au.com.crazybean.mobilex.kurir.repository.messages.MessagesRepository

class ChatViewModel(private val userData: UserData?,
                    private val repository: MessagesRepository) : ViewModel() {

    private val liveData by lazy {
        MutableLiveData<List<Message>?>().also { liveData ->
            val from = userData?.getString(kEmail, "")?: ""
            repository.getMessages(from, completion = { messages ->
                liveData.value = messages
            }, observation = { messages ->
                liveData.value = messages
            })
        }
    }

    val myEmail: String
        get() = userData?.getString(kEmail, "")?: ""

    val messages: LiveData<List<Message>?>
        get() = liveData

    fun sendMessage(content: String, toEmail: String) {
        val from = userData?.getString(kEmail, "")?: ""
        val message = Message(from, toEmail, content, 0, currentMillis)
        repository.sendMessage(message) {
            Logger.d("Message sent: ${message.content}")
        }
    }
}