package au.com.crazybean.mobilex.kurir.repository.messages

import au.com.crazybean.mobilex.foundation.repository.Repository
import au.com.crazybean.mobilex.kurir.data.model.Message

class MessagesRepository(private val messagesSource: MessagesSource) : Repository() {
    fun getMessages(toEmail: String, callback: (List<Message>?)-> Unit) {
        messagesSource.getMessages(toEmail, callback)
    }

    fun sendMessage(message: Message, callback: (Boolean) -> Unit) {
        messagesSource.sendMessage(message) {
            callback(it)
        }
    }
}