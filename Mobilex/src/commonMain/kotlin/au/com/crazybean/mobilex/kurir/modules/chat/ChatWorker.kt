package au.com.crazybean.mobilex.kurir.modules.chat

import au.com.crazybean.mobilex.foundation.logger.Logger
import au.com.crazybean.mobilex.foundation.native.currentMillis
import au.com.crazybean.mobilex.foundation.saw.Worker
import au.com.crazybean.mobilex.foundation.saw.awareness.Emitter
import au.com.crazybean.mobilex.foundation.userdata.UserData
import au.com.crazybean.mobilex.kurir.data.kEmail
import au.com.crazybean.mobilex.kurir.data.model.Message
import au.com.crazybean.mobilex.kurir.repository.messages.MessagesRepository

class ChatWorker(private val userData: UserData?,
                 private val repository: MessagesRepository) : Worker() {

    private val liveData by lazy {
        Emitter<List<Message>?>().also { result ->
            val from = userData?.getString(kEmail, "")?: ""
            repository.getMessages(from, completion = { messages ->
                result.value = messages
            }, observation = { messages ->
                result.value = messages
            })
        }
    }

    val myEmail: String
        get() = userData?.getString(kEmail, "")?: ""

    val messages: Emitter<List<Message>?>
        get() = liveData

    fun sendMessage(content: String, toEmail: String) {
        val from = userData?.getString(kEmail, "")?: ""
        val message = Message(from, toEmail, content, 0, currentMillis)
        repository.sendMessage(message) {
            Logger.d("Message sent: ${message.content}")
        }
    }
}